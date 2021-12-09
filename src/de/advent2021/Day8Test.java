package de.advent2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class Day8Test {

    static Stream<String> input;

    @BeforeAll
    static void setup() {
        input = ReadFile.readFileAsStream("input_day8_test.txt");
    }

    @Test
    void testDisplayFromString() {
        var displays = input.map(Display::fromString).toList();
        Assertions.assertEquals(10, displays.size());
        Assertions.assertEquals("dgebacf", displays.get(1).output[2]);
    }

    @Test
    void testCountOneFourSevenEight() {
        var sum = Day8.countOneFourSevenEight(input.toList());
        Assertions.assertEquals(26, sum);
    }

    @Test
    void testPermutations() {
        var p = Day8.permutation("abc");
        Assertions.assertEquals(6, p.size());
        Assertions.assertTrue(p.contains("abc"));
        Assertions.assertTrue(p.contains("bac"));
        Assertions.assertTrue(p.contains("bca"));
    }

    @Test
    void testTransformDigit() {
        var permutation = "deafgbc";
        Assertions.assertEquals("abcdefg", Day8.transformDigit("acedgfb", permutation));
        Assertions.assertEquals("abdfg", Day8.transformDigit("cdfbe", permutation));
        Assertions.assertEquals("acdeg", Day8.transformDigit("gcdfa", permutation));
        Assertions.assertEquals("acdfg", Day8.transformDigit("fbcad", permutation));
    }

    @Test
    void testIsPermutationCorrect() {
        var display = Display.fromString("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf");
        var display2 = Display.fromString("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf");
        var permutation = "deafgbc";
        var falsePermutation = "edafgbc";
        Assertions.assertTrue(Day8.transformDisplayIfPermutationIsCorrect(display, permutation));
        Assertions.assertFalse(Day8.transformDisplayIfPermutationIsCorrect(display2, falsePermutation));
        Assertions.assertEquals(5353, display.outputAsNumbers);
    }

    @Test
    void testCalculateSumOfDigits() {
        var displays = input.map(Display::fromString).toList();
        var sum = Day8.calculateSumOfDigits(displays);
        Assertions.assertEquals(61229, sum);
    }

}
