package de.advent2021.day1a;

import java.util.List;

public class Day2a {

    private static List<String> testInput = List.of(
            "forward 5",
            "down 5",
            "forward 8",
            "up 3",
            "down 8",
            "forward 2");

    public static void main(String[] args) {
        var input = testInput; //ReadFile.readFile("input_day2a.txt");
        var result = calculatePosition(input);
        System.out.println("Result: " + result);
    }

    private static int calculatePosition(List<String> input) {
        var horizontal = 0;
        var depth = 0;
        for(String s : input) {
            var tokens = s.split(" ");
            switch (tokens[0]) {
                case "forward":
                    horizontal += Integer.parseInt(tokens[1]);
                    break;
                case "down":
                    depth += Integer.parseInt(tokens[1]);
                    break;
                case "up":
                    depth -= Integer.parseInt(tokens[1]);
                    break;
            }
        }
        System.out.println("Horizontal: " + horizontal);
        System.out.println("Depth: " + depth);
        return horizontal * depth;
    }

}
