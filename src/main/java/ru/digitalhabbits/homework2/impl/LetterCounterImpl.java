package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.LetterCounter;

import java.util.HashMap;
import java.util.Map;

public class LetterCounterImpl implements LetterCounter {

    @Override
    public Map<Character, Long> count(String input) {
        Map<Character, Long> map = new HashMap<>();
        input.chars()
                .mapToObj(i -> (char) i)
                .forEach(c -> map.put(c, map.getOrDefault(c, 0L) + 1));

        return map;
    }
}
