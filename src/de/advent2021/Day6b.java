package de.advent2021;

import java.util.Arrays;
import java.util.stream.Stream;

public class Day6b {

    public static void main(String[] args) {
        var fishes = Day6b.createFishArray(ReadFile.readSingleLineAsIntegers("input_day6.txt"));
        for (int i = 0; i < 256; i++) {
            Day6b.step(fishes);
        }
        System.out.println("Number of fish: " + Arrays.stream(fishes).sum());
    }

    public static void step(long[] fishes) {
        var newFishes = fishes[0];
        for (int i = 1; i < fishes.length; i++) {
            fishes[i-1] = fishes[i];
        }
        fishes[6] += newFishes;
        fishes[8] = newFishes;
    }

    public static long[] createFishArray(Stream<Integer> list) {
        var result = new long[9];
        list.forEach(f -> result[f]++);
        return result;
    }

}
