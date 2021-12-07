package de.advent2021;

import java.util.List;

public class Day1a {

    private static final int[] depths = {
            199,
            200,
            208,
            210,
            200,
            207,
            240,
            269,
            260,
            263};
    
    private static String filename = "input_day1a.txt";

    public static void main(String[] args) {
        var inputInt = ReadFile.readLinesAsIntegers(filename).toList();
        int numberOfIncreases = calculateNumberOfIncreases(inputInt);
        System.out.println("Number of depth increases: " + numberOfIncreases);
    }

    public static int calculateNumberOfIncreases(List<Integer> depths) {
        int lastMeasurement = 0;
        int numberOfIncreases = 0;
        for (int i = 0; i < depths.size(); i++) {
            if (depths.get(i) > lastMeasurement && i > 0) {
                numberOfIncreases++;
            }
            lastMeasurement = depths.get(i);
        }
        return numberOfIncreases;
    }

}
