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
        return Arrays.stream(input.findFirst().get().split(splitToken)).map(x -> Integer.parseInt(x));
    }

    public static Stream<Integer> readSingleLineAsIntegers(String filename) {
        return readSingleLineAsIntegers(filename, ",");
    }

}
