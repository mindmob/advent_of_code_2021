package de.advent2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReadFileTest {

    @Test
    void testReadSingleLineAsIntegers() {
        var input = ReadFile.readSingleLineAsIntegers("input_day7_test.txt").toList();
        Assertions.assertEquals(10, input.size());
        Assertions.assertEquals(16, input.get(0));
    }

}
