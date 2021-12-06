package de.advent2021.day1a;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Day1a {

    private static int[] depths = {
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

        var input = readFile();
        var inputInt = input.stream().map(a -> Integer.parseInt(a.trim())).collect(Collectors.toList());
        int numberOfIncreases = calculateNumberOfIncreases(inputInt);
        System.out.println("Number of depth increases: " + numberOfIncreases);
    }

    private static int calculateNumberOfIncreases(List<Integer> depths) {
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

    private static List<String> readFile() {
        try{
            Path path = Paths.get(Day1a.class.getClassLoader()
                    .getResource(filename).toURI());
            return Files.lines(path).collect(Collectors.toList());
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
