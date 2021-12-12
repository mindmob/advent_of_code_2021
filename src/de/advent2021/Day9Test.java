package de.advent2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class Day9Test {

    static Stream<String> input;

    @BeforeAll
    static void setup() {
        input = ReadFile.readFileAsStream("input_day9_test.txt");
    }

    @Test
    void testParseInput() {
        var inputList = input.toList();
        var result = Day9.parseInput(inputList);
        Assertions.assertEquals(9, result[1][5]);
        Assertions.assertEquals(2, result[2][9]);
    }

    @Test
    void testIsLowPoint() {
        var field = Day9.parseInput(input.toList());
        Assertions.assertTrue(Day9.isLowPoint(field, 0, 1));
        Assertions.assertTrue(Day9.isLowPoint(field, 2, 2));
        Assertions.assertFalse(Day9.isLowPoint(field, 0, 0));
        Assertions.assertFalse(Day9.isLowPoint(field, 4, 9));
    }

    @Test
    void testRiskLevel() {
        var field = Day9.parseInput(input.toList());
        var riskLevel = Day9.riskLevel(field);
        Assertions.assertEquals(15, riskLevel);
    }

    @Test
    void testLowPoints() {
        var field = Day9.parseInput(input.toList());
        var points = Day9.lowPoints(field);
        Assertions.assertEquals(4, points.size());
    }

    @Test
    void testFindBasin() {
        var field = Day9.parseInput(input.toList());
        var p = new Point(0, 1);
        var basin = Day9.findBasin(field, p);
        Assertions.assertEquals(3, basin.size());
    }

    @Test
    void testSizeOfTopThreeBasins() {
        var field = Day9.parseInput(input.toList());
        var size = Day9.sizeOfTopThreeBasins(field);
        Assertions.assertEquals(1134, size);
    }

}
