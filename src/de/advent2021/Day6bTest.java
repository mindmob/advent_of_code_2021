package de.advent2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day6bTest {

    static Stream<Integer> fishInput;

    @BeforeAll
    static void setup() {
        fishInput = ReadFile.readSingleLineAsIntegers("input_day6_test.txt");
    }

    @Test
    void testCreateFishArray() {
        var fishes = List.of(2, 4, 8, 2, 0).stream();
        var fishArray = Day6b.createFishArray(fishes);
        Assertions.assertEquals(2, fishArray[2]);
        Assertions.assertEquals(0, fishArray[1]);
        Assertions.assertEquals(1, fishArray[8]);
    }

    @Test
    void testStep() {
        var fishes = Day6b.createFishArray(fishInput);
        Assertions.assertEquals(5, Arrays.stream(fishes).sum());
        for (int i = 0; i < 18; i++) {
            Day6b.step(fishes);
        }
        Assertions.assertEquals(26, Arrays.stream(fishes).sum());
        for (int i = 18; i < 80; i++) {
            Day6b.step(fishes);
        }
        Assertions.assertEquals(5934, Arrays.stream(fishes).sum());
    }

}
