package de.advent2021;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.IntBinaryOperator;

public class Day7 {

    public static void main(String[] args) {
        var crabPositions = ReadFile.readSingleLineAsIntegers("input_day7.txt").toList();
        var costs = fuelCosts(crabPositions, Day7::mapLinear);
        var minimum = Arrays.stream(costs).reduce(Integer::min).getAsInt();
        System.out.println("(Part 1) Minimum: " + minimum);
        var costs2 = fuelCosts(crabPositions, Day7::mapIncremental);
        var minimum2 = Arrays.stream(costs2).reduce(Integer::min).getAsInt();
        System.out.println("(Part 2) Minimum: " + minimum2);
    }

    public static int[] fuelCosts(List<Integer> crabPositions, IntBinaryOperator mappingFunction) {
        var maxPosition = maxPosition(crabPositions);
        var fuelCosts = new int[maxPosition];
        for (int i = 0; i < maxPosition; i++) {
            fuelCosts[i] = fuelCostForPosition(crabPositions, i, mappingFunction);
        }
        return fuelCosts;
    }

    public static int fuelCostForPosition(List<Integer> crabPositions, int targetPosition, IntBinaryOperator mappingFunction) {
        return crabPositions.stream().map(crab -> mappingFunction.applyAsInt(crab, targetPosition)).reduce(Integer::sum).get();
    }

    public static int maxPosition(List<Integer> crabPositions) {
        return crabPositions.stream().max(Comparator.naturalOrder()).get();
    }

    public static Integer mapLinear(Integer crab, Integer targetPosition) {
        return Math.abs(targetPosition - crab);
    }

    public static Integer mapIncremental(Integer crab, Integer targetPosition) {
        var fulecost=  Math.abs(targetPosition - crab);
        return fulecost + (fulecost*(fulecost-1)/2);
    }

}
