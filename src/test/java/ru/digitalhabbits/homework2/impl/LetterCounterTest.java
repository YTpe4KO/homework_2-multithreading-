package ru.digitalhabbits.homework2.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.LetterCounter;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;


@DisplayName("Тесты сервиса LetterCounter")
class LetterCounterTest {

    private final LetterCounter letterCounter;

    public LetterCounterTest() {
        this.letterCounter = new LetterCounterImpl();
    }

    @Nested
    @DisplayName("Тесты метода count")
    class Count {

        @Test
        @DisplayName("Передана обычная строка")
        void countTestInputString() {
            String testString = "aaa_bbb_ccc_ddd";
            assertThat(letterCounter.count(testString)).containsOnly(
                    entry('a', 3L),
                    entry('b', 3L),
                    entry('c', 3L),
                    entry('d', 3L),
                    entry('_', 3L)
            );
        }


        @Test
        @DisplayName("Если строка null")
        void countTestIfInPutStringNull() {
            Map<Character, Long> count = letterCounter.count(null);
            assertEquals(count.size(), 0);
        }
    }

}
