package de.advent2021;

import java.util.stream.Stream;

public class Day8 {

    public static int countOneFourSevenEight(Stream<String> input) {
        return input.map(Display::fromString).map(x -> x.countOneFourSevenEight()).reduce(0, Integer::sum);
    }

    public static void main(String[] args) {
        var input = ReadFile.readFileAsStream("input_day8.txt");
        var sum = countOneFourSevenEight(input);
        System.out.println("Result part 1: " + sum);
    }

}

class Display {
    public String[] input;
    public String[] output;
    public String[] mapping;
    public static Display fromString(String s) {
        var display = new Display();
        var split = s.trim().split("\\|");
        display.input = split[0].trim().split(" ");
        display.output = split[1].trim().split(" ");

        return display;
    }
    public int countOneFourSevenEight() {
        var number = 0;
        for (int i = 0; i < output.length; i++) {
            var l = output[i].length();
            if (l == 2 || l == 4 || l == 3 || l == 7) number++;
        }
        return number;
    }
    private String[] mappings() {
        var result = new String[10];
        for (int i = 0; i < 10; i++) {
            var digit = input[i];
            if (digit.length() == 2) result[1] = digit;
            if (digit.length() == 3) result[7] = digit;
            if (digit.length() == 4) result[4] = digit;
            if (digit.length() == 7) result[8] = digit;
        }

        return result;
    }
}