package ru.digitalhabbits.homework2.task;

import ru.digitalhabbits.homework2.LetterCountMerger;
import ru.digitalhabbits.homework2.LetterCounter;
import ru.digitalhabbits.homework2.impl.LetterCountMergerImpl;
import ru.digitalhabbits.homework2.impl.LetterCounterImpl;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

public class CountLetterTask extends RecursiveTask<Map<Character, Long>> {
    private final LetterCounter letterCounter;
    private final LetterCountMerger letterCountMerger;
    private final int start;
    private final int end;
    private final String[] arr;

    public CountLetterTask(int start, int end, String[] arr) {
        letterCountMerger = new LetterCountMergerImpl();
        letterCounter = new LetterCounterImpl();
        this.start = start;
        this.end = end;
        this.arr = arr;
    }

    @Override
    protected Map<Character, Long> compute() {
        if (start > end) {
            return Collections.emptyMap();
        }

        int mid = (start + end) / 2;

        if (end - start == 1) {
            return letterCounter.count(arr[mid]);
        } else {
            CountLetterTask action1 = new CountLetterTask(start, mid, arr);
            CountLetterTask action2 = new CountLetterTask(mid, end, arr);

            return letterCountMerger.merge(action1.fork().join(), action2.fork().join());
        }
    }
}
