package de.advent2021;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Day8 {

//      0:      1:      2:      3:      4:
//     aaaa    ....    aaaa    aaaa    ....
//    b    c  .    c  .    c  .    c  b    c
//    b    c  .    c  .    c  .    c  b    c
//     ....    ....    dddd    dddd    dddd
//    e    f  .    f  e    .  .    f  .    f
//    e    f  .    f  e    .  .    f  .    f
//     gggg    ....    gggg    gggg    ....
//
//      5:      6:      7:      8:      9:
//     aaaa    aaaa    aaaa    aaaa    aaaa
//    b    .  b    .  .    c  b    c  b    c
//    b    .  b    .  .    c  b    c  b    c
//     dddd    dddd    ....    dddd    dddd
//    .    f  e    f  .    f  e    f  .    f
//    .    f  e    f  .    f  e    f  .    f
//     gggg    gggg    ....    gggg    gggg
    static final String[] correctDigits = new String[] {
        "abcefg",
        "cf",
        "acdeg",
        "acdfg",
        "bcdf",
        "abdfg",
        "abdefg",
        "acf",
        "abcdefg",
        "abcdfg"
    };

    static int countOneFourSevenEight(List<String> input) {
        return input.stream().map(Display::fromString).map(Display::countOneFourSevenEight).reduce(0, Integer::sum);
    }

    public static void main(String[] args) {
        var input = ReadFile.readFileAsStream("input_day8.txt").toList();
        var sum = countOneFourSevenEight(input);
        System.out.println("Result part 1: " + sum);
        var displays = input.stream().map(Display::fromString).toList();
        var sumPart2 = calculateSumOfDigits(displays);
        System.out.println("Result part 2: " + sumPart2);
    }

    static int calculateSumOfDigits(List<Display> displays) {
        var permutations = permutation("abcdefg");
        var sumPart2 = 0;
        for(var d : displays) {
            transformDisplay(d, permutations);
            sumPart2 += d.outputAsNumbers;
        }
        return sumPart2;
    }

    static void transformDisplay(Display display, List<String> permutations) {
        for(var p : permutations) {
            if (transformDisplayIfPermutationIsCorrect(display, p)) {
                return;
            }
        }
    }

    static boolean transformDisplayIfPermutationIsCorrect(Display display, String permutation) {
        var output = new String[10];
        for (int number = 0; number < 10; number++) {
            String transformed = transformDigit(display.input[number], permutation);
            if (Arrays.asList(correctDigits).contains(transformed) == false) {
                return false;
            }
            output[number] = transformed;
        }
        // the permutation is correct
        display.permutation = permutation;
        display.transformedInput = output;
        display.transformedOutput = new String[4];
        var outputAsNumbers = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            display.transformedOutput[i] = transformDigit(display.output[i], permutation);
            outputAsNumbers.append(Arrays.asList(correctDigits).indexOf(display.transformedOutput[i]));
        }
        display.outputAsNumbers = Integer.parseInt(outputAsNumbers.toString());
        return true;
    }

    static String transformDigit(String s, String permutation) {
        var original = "abcdefg";
        var transformed = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            var index = permutation.indexOf(s.charAt(i));
            var transformedChar = original.charAt(index);
            transformed.append(transformedChar);
        }
        var unsorted = transformed.toString().toCharArray();
        Arrays.sort(unsorted);
        return new String(unsorted);
    }

    static List<String> permutation(String str) {
        var permutations = new LinkedList<String>();
        permutation("", str, permutations);
        return permutations;
    }

    static void permutation(String prefix, String str, List<String> permutations) {
        int n = str.length();
        if (n == 0) permutations.add(prefix);
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n), permutations);
        }
    }

}

class Display {
    public String[] input;
    public String[] output;
    public String[] transformedInput;
    public String[] transformedOutput;
    public String permutation;
    public int outputAsNumbers;
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
}