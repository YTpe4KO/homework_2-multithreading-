package ru.digitalhabbits.homework2.impl;

import org.apache.commons.lang3.StringUtils;
import ru.digitalhabbits.homework2.LetterCounter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LetterCounterImpl implements LetterCounter {

    @Override
    public Map<Character, Long> count(String input) {
        if (StringUtils.isBlank(input)) {
            return Collections.emptyMap();
        }
        Map<Character, Long> map = new HashMap<>();
        input.chars()
                .mapToObj(i -> (char) i)
                .forEach(c -> map.put(c, map.getOrDefault(c, 0L) + 1));

        return map;
    }
}
