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
        var cost = Day7.fuelCostForPosition(crabPositions, 2, Day7::mapLinear);
        Assertions.assertEquals(37, cost);
    }

    @Test
    void testFuelCosts() {
        var costs = Day7.fuelCosts(crabPositions, Day7::mapLinear);
        Assertions.assertEquals(37, costs[2]);
        System.out.println("Fuel costs: " + Arrays.toString(costs));
        var minimum = Arrays.stream(costs).reduce(Integer::min).getAsInt();
        Assertions.assertEquals(37, minimum);
        System.out.println("Minimum: " + minimum);
    }

    @Test
    void testFuelCostForPositionIncremental() {
        Assertions.assertEquals(206, Day7.fuelCostForPosition(crabPositions, 2, Day7::mapIncremental));
        Assertions.assertEquals(168, Day7.fuelCostForPosition(crabPositions, 5, Day7::mapIncremental));
    }

    @Test
    void testFuelCostsIncremental() {
        var costs = Day7.fuelCosts(crabPositions, Day7::mapIncremental);
        Assertions.assertEquals(206, costs[2]);
        System.out.println("Fuel costs: " + Arrays.toString(costs));
        var minimum = Arrays.stream(costs).reduce(Integer::min).getAsInt();
        Assertions.assertEquals(168, minimum);
        System.out.println("Minimum: " + minimum);
    }


}
