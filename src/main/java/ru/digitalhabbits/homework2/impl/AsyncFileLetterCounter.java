package ru.digitalhabbits.homework2.impl;

import lombok.SneakyThrows;
import ru.digitalhabbits.homework2.FileLetterCounter;
import ru.digitalhabbits.homework2.FileReader;
import ru.digitalhabbits.homework2.LetterCountMerger;
import ru.digitalhabbits.homework2.LetterCounter;

import java.io.File;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class AsyncFileLetterCounter implements FileLetterCounter {

    private final FileReader fileReader;
    private final LetterCounter letterCounter;
    private final LetterCountMerger letterCountMerger;
    private final Map<Character, Long> resultMap;

    public AsyncFileLetterCounter() {
        this.fileReader = new StreamFileReader();
        this.letterCounter = new LetterCounterImpl();
        this.letterCountMerger = new LetterCountMergerImpl();
        resultMap = new ConcurrentHashMap<>();
    }


    @Override
    public Map<Character, Long> count(File input) {
        fileReader.readLines(input)
                .forEach(s -> {
                    getLetterCounter(s).thenApplyAsync(this::mergeTwoMap)
                            .thenAcceptAsync(resultMap::putAll)
                            .join();
                });
        return resultMap;

    }

    CompletableFuture<Map<Character, Long>> getLetterCounter(String string) {
        return CompletableFuture.supplyAsync(() -> letterCounter.count(string));
    }

    @SneakyThrows
    Map<Character, Long> mergeTwoMap(Map<Character, Long> map) {
        return letterCountMerger.merge(map, resultMap);
    }
}
