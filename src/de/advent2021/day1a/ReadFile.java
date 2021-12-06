package de.advent2021.day1a;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ReadFile {

    public static List<String> readFile(String filename) {
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
