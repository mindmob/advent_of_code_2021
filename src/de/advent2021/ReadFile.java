package de.advent2021;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFile {

    public static List<String> readFile(String filename) {
        try{
            Path path = Paths.get(ReadFile.class.getClassLoader()
                    .getResource(filename).toURI());
            return Files.lines(path).collect(Collectors.toList());
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Stream<String> readFileAsStream(String filename) {
        try{
            Path path = Paths.get(ReadFile.class.getClassLoader()
                    .getResource(filename).toURI());
            return Files.lines(path);
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Stream<Integer> readSingleLineAsIntegers(String filename, String splitToken) {
        var input = readFileAsStream(filename);
        return Arrays.stream(input.findFirst().get().trim().split(splitToken)).map(x -> Integer.parseInt(x.trim()));
    }

    public static Stream<Integer> readSingleLineAsIntegers(String filename) {
        return readSingleLineAsIntegers(filename, ",");
    }

    public static Stream<Integer> readLinesAsIntegers(String filename) {
        return readFileAsStream(filename).map(Integer::parseInt);
    }

    public static int[][] readAsIntArray(String filename) {
        var input = readFile(filename);
        var result = new int[input.size()][input.get(0).length()];
        for (int row = 0; row < input.size(); row++) {
            for (int col = 0; col < input.get(0).length(); col++) {
                result[row][col] = Integer.parseInt(input.get(row).substring(col, col+1));
            }
        }
        return result;
    }

    public static void printIntArray(int[][] input) {
        for (int row = 0; row < input.length; row++) {
            for (int col = 0; col < input[0].length; col++) {
                System.out.print(input[row][col]);
            }
            System.out.print('\n');
        }
    }

}
