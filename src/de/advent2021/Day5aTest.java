package de.advent2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.geom.Line2D;
import java.util.List;

public class Day5aTest {

    static List<String> input;

    @BeforeAll
    static void setup() {
        input = ReadFile.readFile("input_day5a_test.txt");
    }

    @Test
    void testParseLine() {
        var line = Day5a.parseLine(input.get(0));
        Assertions.assertEquals(0f, line.getX1());
        Assertions.assertEquals(9f, line.getY1());
        Assertions.assertEquals(5f, line.getX2());
        Assertions.assertEquals(9f, line.getY2());
    }

    @Test
    void testIsVerticalOrHorizontal() {
        var line1 = new Line2D.Float(0, 0, 1, 0);
        var line2 = new Line2D.Float(0, 0, 1, 1);
        Assertions.assertTrue(Day5a.isVerticalOrHorizontal(line1));
        Assertions.assertFalse(Day5a.isVerticalOrHorizontal(line2));
    }

    @Test
    void testFilterNonHorizontalOrVertical() {
        var lines = Day5a.parseInput(input).filter(line -> Day5a.isVerticalOrHorizontal(line)).toList();
        Assertions.assertEquals(6, lines.size());
    }

    @Test
    void testMaxBoundsOfTwoLines() {
        var lines = Day5a.parseInput(input).toList();
        var max = Day5a.maxBoundsOfTwoLines(lines.get(1), lines.get(2));
        Assertions.assertEquals(9f, max.getX2());
        Assertions.assertEquals(8f, max.getY2());
    }

    @Test
    void testMaxBoundsOfAll() {
        var lines = Day5a.parseInput(input);
        var max = Day5a.maxBoundsOfAll(lines);
        Assertions.assertEquals(9f, max.getX2());
        Assertions.assertEquals(9f, max.getY2());
    }

    @Test
    void testAddLineToField() {
        var line = Day5a.parseInput(input).toList().get(0);
        var field = new Field(10, 10);
        field.addLine(line);
        field.print();
        Assertions.assertEquals(1, field.get(0, 9));
        Assertions.assertEquals(1, field.get(1, 9));
        Assertions.assertEquals(1, field.get(5, 9));
        Assertions.assertEquals(0, field.get(0, 0));
        Assertions.assertEquals(0, field.get(6, 9));
    }

    @Test
    void testNumberOfIntersections() {
        var lines = Day5a.parseInput(input).filter(line -> Day5a.isVerticalOrHorizontal(line));
        var field = Day5a.createField(lines);
        var number = field.numberOfIntersections();
        Assertions.assertEquals(5, number);
    }

}
