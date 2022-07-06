package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.FileLetterCounter;
import ru.digitalhabbits.homework2.FileReader;
import ru.digitalhabbits.homework2.task.CountLetterTask;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolFileLetterCounter implements FileLetterCounter {

    private static FileReader fileReader;

    public ForkJoinPoolFileLetterCounter() {
        fileReader = new StreamFileReader();
    }

    @Override
    public Map<Character, Long> count(File input) {
        String[] stringsFromFile = fileReader.readLines(input)
                .toArray(String[]::new);

        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new CountLetterTask(0, stringsFromFile.length, stringsFromFile));
    }
}
