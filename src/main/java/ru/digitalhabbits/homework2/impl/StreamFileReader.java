package ru.digitalhabbits.homework2.impl;

import org.slf4j.Logger;
import ru.digitalhabbits.homework2.FileReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

import static org.slf4j.LoggerFactory.getLogger;

public class StreamFileReader implements FileReader {

    private static final Logger log = getLogger(StreamFileReader.class);

    @Override
    public Stream<String> readLines(File file) {
        try {
            log.info("Reading from file '{}'", file.getAbsolutePath());
            return Files.lines(file.getAbsoluteFile().toPath());
        } catch (IOException e) {
            log.error("File by path '{}' not found", file.getAbsolutePath());
            throw new RuntimeException(e);
        }
    }
}
