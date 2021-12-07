package de.advent2021;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Day7 {

    public static void main(String[] args) {
        var crabPositions = ReadFile.readSingleLineAsIntegers("input_day7.txt").toList();
        var costs = fuelCosts(crabPositions);
        var minimum = Arrays.stream(costs).reduce(Integer::min).getAsInt();
        System.out.println("(Part 1) Minimum: " + minimum);
        var costs2 = fuelCostsIncremental(crabPositions);
        var minimum2 = Arrays.stream(costs2).reduce(Integer::min).getAsInt();
        System.out.println("(Part 2) Minimum: " + minimum2);
    }

    public static int[] fuelCosts(List<Integer> crabPositions) {
        var maxPosition = maxPosition(crabPositions);
        var fuelCosts = new int[maxPosition];
        for (int i = 0; i < maxPosition; i++) {
            fuelCosts[i] = fuelCostForPosition(crabPositions, i);
        }
        return fuelCosts;
    }

    // todo reduce duplicate code
    public static int[] fuelCostsIncremental(List<Integer> crabPositions) {
        var maxPosition = maxPosition(crabPositions);
        var fuelCosts = new int[maxPosition];
        for (int i = 0; i < maxPosition; i++) {
            fuelCosts[i] = fuelCostForPositionIncremental(crabPositions, i);
        }
        return fuelCosts;
    }

    public static int fuelCostForPosition(List<Integer> crabPositions, int targetPosition) {
        return crabPositions.stream().map(crab -> Math.abs(targetPosition - crab)).reduce(Integer::sum).get();
    }

    public static int fuelCostForPositionIncremental(List<Integer> crabPositions, int targetPosition) {
        return crabPositions.stream().map(crab ->
        {
            var fulecost=  Math.abs(targetPosition - crab);
            return fulecost + (fulecost*(fulecost-1)/2);
        }).reduce(Integer::sum).get();
    }

    public static int maxPosition(List<Integer> crabPositions) {
        return crabPositions.stream().max(Comparator.naturalOrder()).get();
    }


}
