package de.advent2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Day11Test {

    int[][] input;

    @BeforeEach
    void setup() {
        input = ReadFile.readAsIntArray("input_day11_test.txt");
    }

    @Test
    void testStepSmall() {
        var inputSmall = ReadFile.readAsIntArray("input_day11_flashtest0.txt");
        var sum = 0;
        var flashes = new int[]{9, 0};
        for (int i = 0; i < 1; i++) {
            sum = Day11.step(inputSmall);
            var testset1 = ReadFile.readAsIntArray("input_day11_flashtest" + (i+1) + ".txt");
            System.out.println("\nArray after " + (i+1) + " steps: ");
            ReadFile.printIntArray(inputSmall);
            Assertions.assertEquals(flashes[i], sum);
            Assertions.assertTrue(Arrays.deepEquals(inputSmall, testset1));
        }
    }

    @Test
    void testStep() {
        var sum = 0;
        var flashes = new int[]{0, 35};
        for (int i = 0; i < 2; i++) {
            sum = Day11.step(input);
            var testset1 = ReadFile.readAsIntArray("input_day11_test" + (i+1) + ".txt");
            System.out.println("\nArray after " + (i+1) + " steps: ");
            ReadFile.printIntArray(input);
            Assertions.assertEquals(flashes[i], sum);
            Assertions.assertTrue(Arrays.deepEquals(input, testset1));
        }

    }

}
