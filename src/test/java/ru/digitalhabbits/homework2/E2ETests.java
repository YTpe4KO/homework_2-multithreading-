package ru.digitalhabbits.homework2;

import static com.google.common.io.Resources.getResource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Map;

import ru.digitalhabbits.homework2.impl.AsyncFileLetterCounter;
import ru.digitalhabbits.homework2.impl.ForkJoinPoolFileLetterCounter;

public class E2ETests {

    @Test
    @DisplayName("E2ETest для completableFutureImpl")
    void async_file_letter_counting_should_return_predicted_count() {
        var file = getFile("test.txt");
        var counter = new AsyncFileLetterCounter();

        Map<Character, Long> count = counter.count(file);

        assertThat(count).containsOnly(
                entry('a', 2697L),
                entry('b', 2683L),
                entry('c', 2647L),
                entry('d', 2613L),
                entry('e', 2731L),
                entry('f', 2629L)
        );
        System.out.println("Finished 1");
    }


    @Test
    @DisplayName("E2ETest для forkJoinPoolImpl")
    void fork_join_letter_counting_should_return_predicted_count() {
        var file = getFile("test.txt");
        var counter = new ForkJoinPoolFileLetterCounter();

        Map<Character, Long> count = counter.count(file);

        assertThat(count).containsOnly(
                entry('a', 2697L),
                entry('b', 2683L),
                entry('c', 2647L),
                entry('d', 2613L),
                entry('e', 2731L),
                entry('f', 2629L)
        );
        System.out.println("Finshed 2");
    }
    private File getFile(String name) {
        return new File(getResource(name).getPath());
    }
}
