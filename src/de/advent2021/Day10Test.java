package de.advent2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static de.advent2021.Day10.checkCorruption;

public class Day10Test {

    Stream<String> input;

    @BeforeEach
    void setup() {
        input = ReadFile.readFileAsStream("input_day10_test.txt");
    }

    @Test
    void testCheckCorruption() {
        String input1 = "{([(<{}[<>[]}>{[]{[(<()>";
        var result1 = checkCorruption(input1);
        Assertions.assertEquals('}', result1);
        String input2 = "[[<[([]))<([[{}[[()]]]";
        var result2 = checkCorruption(input2);
        Assertions.assertEquals(')', result2);
        String input3 = "[{[{({}]{}}([{[{{{}}([]";
        var result3 = checkCorruption(input3);
        Assertions.assertEquals(']', result3);
    }

    @Test
    void testErrorScore() {
        var score = Day10.errorScore(input.toList());
        Assertions.assertEquals(26397, score);
    }

    @Test
    void testCompletionScore() {
        Assertions.assertEquals(2, Day10.completionScore(']'));
    }

    @Test
    void testMissingClosingChars() {
        String line = "[({(<(())[]>[[{[]{<()<>>";
        var missing = Day10.missingClosingChars(line);
        Assertions.assertEquals("}}]])})]", missing);
    }

    @Test
    void testCompletionScoreString() {
        Assertions.assertEquals(288957, Day10.completionScoreString("}}]])})]"));
        Assertions.assertEquals(1480781, Day10.completionScoreString("}}>}>))))"));
    }

    @Test
    void testSolvePart2() {
        var winner = Day10.solvePart2(input);
        Assertions.assertEquals(288957, winner);
    }

}
