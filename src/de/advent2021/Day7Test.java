package de.advent2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class Day7Test {

    static List<Integer> crabPositions;

    @BeforeAll
    static void setup() {
        crabPositions = ReadFile.readSingleLineAsIntegers("input_day7_test.txt").toList();
    }

    @Test
    void testMaxPosition() {
        var max = Day7.maxPosition(crabPositions);
        Assertions.assertEquals(16, max);
    }

    @Test
    void testFuelCostForPosition() {
        var cost = Day7.fuelCostForPosition(crabPositions, 2);
        Assertions.assertEquals(37, cost);
    }

    @Test
    void testFuelCosts() {
        var costs = Day7.fuelCosts(crabPositions);
        Assertions.assertEquals(37, costs[2]);
        System.out.println("Fuel costs: " + Arrays.toString(costs));
        var minimum = Arrays.stream(costs).reduce(Integer::min).getAsInt();
        Assertions.assertEquals(37, minimum);
        System.out.println("Minimum: " + minimum);
    }

}
