package de.advent2021;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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

        var input = readFile();
        var inputInt = input.stream().map(a -> Integer.parseInt(a.trim())).collect(Collectors.toList());
        var slidingWindows = calculateWindows(inputInt);
        int numberOfIncreases = calculateNumberOfIncreases(slidingWindows);
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

    private static int calculateNumberOfIncreases(List<Integer> input) {
        int lastMeasurement = 0;
        int numberOfIncreases = 0;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i) > lastMeasurement && i > 0) {
                numberOfIncreases++;
            }
            lastMeasurement = input.get(i);
        }
        return numberOfIncreases;
    }

    private static List<String> readFile() {
        try{
            Path path = Paths.get(Day1b.class.getClassLoader()
                    .getResource(filename).toURI());
            return Files.lines(path).collect(Collectors.toList());
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
