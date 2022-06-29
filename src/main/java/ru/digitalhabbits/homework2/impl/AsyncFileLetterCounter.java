package ru.digitalhabbits.homework2.impl;

import java.io.File;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import ru.digitalhabbits.homework2.FileLetterCounter;
import ru.digitalhabbits.homework2.FileReader;
import ru.digitalhabbits.homework2.LetterCountMerger;
import ru.digitalhabbits.homework2.LetterCounter;

public class AsyncFileLetterCounter implements FileLetterCounter {

    private final FileReader fileReader;
    private final LetterCounter letterCounter;
    private final LetterCountMerger letterCountMerger;

    public AsyncFileLetterCounter() {
        this.fileReader = new StreamFileReader();
        this.letterCounter = new LetterCounterImpl();
        this.letterCountMerger = new LetterCountMergerImpl();
    }


    @Override
    public Map<Character, Long> count(File input) {

        return fileReader.readLines(input)
                .map(s -> CompletableFuture.supplyAsync(() -> letterCounter.count(s)))
                .map(CompletableFuture::join)
                .reduce(letterCountMerger::merge)
                .orElse(Collections.emptyMap());
    }
}
