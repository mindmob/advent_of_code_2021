package de.advent2021;

import java.util.LinkedList;
import java.util.List;

public class Day1b {

    private static List<Integer> depths = List.of(
            199,
            200,
            208,
            210,
            200,
            207,
            240,
            269,
            260,
            263);
    
    private static String filename = "input_day1a.txt";

    public static void main(String[] args) {
        var inputInt = ReadFile.readLinesAsIntegers(filename).toList();
        var slidingWindows = calculateWindows(inputInt);
        int numberOfIncreases = Day1a.calculateNumberOfIncreases(slidingWindows);
        System.out.println("Number of depth increases: " + numberOfIncreases);
    }

    private static List<Integer> calculateWindows(List<Integer> input) {
        var windows = new LinkedList<Integer>();
        for (int offset = 0; offset < input.size() - 2; offset++) {
            var sum = 0;
            for (int j = 0; j < 3; j++) {
                sum += input.get(offset + j);
            }
            windows.add(sum);
            System.out.println(sum);
        }
        return windows;
    }

}
