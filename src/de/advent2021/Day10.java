package de.advent2021;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Day10 {

    static List<Character> opening = List.of('(', '[', '{', '<');
    static List<Character> closing = List.of(')', ']', '}', '>');
    static Map<Character, Integer> errorScores = Map.of(')', 3, ']', 57, '}', 1197, '>', 25137);

    public static void main(String[] args) {
        var input = ReadFile.readFile("input_day10.txt");
        var score = errorScore(input);
        System.out.println("Error score: " + score);

        var inputPart2 = ReadFile.readFileAsStream("input_day10.txt");
        var winner = solvePart2(inputPart2);
        System.out.println("Completions score: " + winner);
    }

    static long solvePart2(Stream<String> input) {
        var noCorrupted = input.filter(line -> checkCorruption(line) == null);
        var missingClosingChars = noCorrupted.map(Day10::missingClosingChars);
        var scores = missingClosingChars.map(Day10::completionScoreString).sorted().toList();
        return winner(scores);
    }

    static Character checkCorruption(String input) {
        var stack = new LinkedList<Character>();
        for (var c : input.toCharArray()) {
            if (opening.contains(c)) {
                stack.add(c);
            } else {
                var openIndex = opening.indexOf(stack.getLast());
                var closeIndex = closing.indexOf(c);
                if (openIndex == closeIndex) {
                    stack.removeLast();
                } else {
                    return c;
                }
            }
        }
        return null;
    }

    static int errorScore(List<String> input) {
        var score = 0;
        for (var line : input) {
            var error = checkCorruption(line);
            if (error != null) {
                score += errorScores.get(error);
            }
        }
        return score;
    }

    static int completionScore(char c) {
        return closing.indexOf(c) + 1;
    }

    static String missingClosingChars(String line) {
        var stack = new LinkedList<Character>();
        for (var c : line.toCharArray()) {
            if (opening.contains(c)) {
                stack.add(c);
            } else {
                stack.removeLast();
            }
        }
        var missing = new StringBuilder();
        Collections.reverse(stack);
        for (var c : stack) {
            missing.append(closing.get(opening.indexOf(c)));
        }
        return  missing.toString();
    }

    static long completionScoreString(String s) {
        var score = 0L;
        for(var c : s.toCharArray()) {
            score *= 5;
            score += completionScore(c);
        }
        return score;
    }

    static long winner(List<Long> scores) {
        return scores.get((scores.size() / 2));
    }

}
