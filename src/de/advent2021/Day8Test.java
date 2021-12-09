package de.advent2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class Day8Test {

    static Stream<String> input;

    @BeforeAll
    static void setup() {
        input = ReadFile.readFileAsStream("input_day8_test.txt");
    }

    @Test
    void testDisplayFromString() {
        var displays = input.map(Display::fromString).toList();
        Assertions.assertEquals(10, displays.size());
        Assertions.assertEquals("dgebacf", displays.get(1).output[2]);
    }

    @Test
    void testCountOneFourSevenEight() {
        var sum = Day8.countOneFourSevenEight(input);
        Assertions.assertEquals(26, sum);
    }

}
