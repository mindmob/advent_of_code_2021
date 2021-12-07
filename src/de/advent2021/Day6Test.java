package de.advent2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class Day6Test {

    static Stream<Integer> fishInput;

    @BeforeAll
    static void setup() {
        fishInput = ReadFile.readSingleLineAsIntegers("input_day6_test.txt");
    }

    @Test
    void testLanterfishStep() {
        var fish = new Lanternfish(3);
        Assertions.assertFalse(fish.step());
        Assertions.assertFalse(fish.step());
        Assertions.assertFalse(fish.step());
        Assertions.assertTrue(fish.step());
        Assertions.assertEquals(6, fish.getTimer());
    }

    @Test
    void testStep() {
        var fishes = fishInput.map(x -> new Lanternfish(x)).toList();
        Assertions.assertEquals(5, fishes.size());
        for (int i = 0; i < 18; i++) {
            fishes = Day6.step(fishes);
        }
        Assertions.assertEquals(26, fishes.size());
        Assertions.assertEquals(6, fishes.get(0).getTimer());
    }

}
