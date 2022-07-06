package ru.digitalhabbits.homework2.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.LetterCountMerger;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;


@DisplayName("Тесты сервиса LetterCountMerger")
class LetterCountMergerTest {

    private final LetterCountMerger letterCountMerger;

    LetterCountMergerTest() {
        letterCountMerger = new LetterCountMergerImpl();
    }

    @Nested
    @DisplayName("Тесты метода merge")
    class Merge {

        @Test
        @DisplayName("Тест слияния двух мап")
        void mergeTestTwoMap() {
            Map<Character, Long> map1 = Map.of('a', 4L, 'b', 4L);
            Map<Character, Long> map2 = Map.of('a', 4L, 'b', 4L);

            Map<Character, Long> resultMap = letterCountMerger.merge(map1, map2);
            assertThat(resultMap).containsOnly(
                    entry('a', 8L),
                    entry('b', 8L)
            );
        }

        @Test
        @DisplayName("Тест слияния двух мап, если первая null")
        void mergeTestTwoMapIfFirstNull() {
            Map<Character, Long> map2 = Map.of('a', 4L, 'b', 4L);

            Map<Character, Long> resultMap = letterCountMerger.merge(null, map2);
            assertThat(resultMap).containsOnly(
                    entry('a', 4L),
                    entry('b', 4L)
            );
        }
    }

}
