package de.advent2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Day3bTest {

    @Test
    public void testFilterCommon() {
        var testInput = List.of(
                "1000", "0000"
        );
        var result = Day3b.filterCommon(testInput, 0);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals('1', result.get(0).charAt(0));

        testInput = ReadFile.readFile("input_day3a_test.txt");
        result = Day3b.filterCommon(testInput, 0);
        Assertions.assertEquals(7, result.size());
        for(var i : result) {
            Assertions.assertEquals('1', i.charAt(0));
        }

    }

    @Test
    public void testFilterUncommon() {
        var testInput = List.of(
                "1000", "0000"
        );
        var result = Day3b.filterUncommon(testInput, 0);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals('0', result.get(0).charAt(0));

    }

    @Test
    public void testSplitByIndex() {
        var testInput = ReadFile.readFile("input_day3a_test.txt");
        var resultOne = Day3b.splitByIndex(testInput, 0).get('1');
        Assertions.assertEquals(7, resultOne.size());
        for(var i : resultOne) {
            Assertions.assertEquals('1', i.charAt(0));
        }
        var resultZero = Day3b.splitByIndex(testInput, 0).get('0');
        Assertions.assertEquals(5, resultZero.size());
        for(var i : resultZero) {
            Assertions.assertEquals('0', i.charAt(0));
        }

    }


}
