package de.advent2021;

public class Day11 {

    public static void main(String[] args) {
        solvePart1();
        solvePart2();
    }

    private static void solvePart1() {
        var input = ReadFile.readAsIntArray("input_day11.txt");
        var sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += step(input);
        }
        System.out.println("Number of flashes: " + sum);
    }

    private static void solvePart2() {
        var input = ReadFile.readAsIntArray("input_day11.txt");
        var flashes = 0;
        var step = 0;
        while(flashes != input.length * input[0].length) {
            flashes = step(input);
            step++;
        }
        System.out.println("First step where all octupusses flash: " + step);
    }

    static int step(int[][] input) {
        var flashed = new boolean[input.length][input[0].length];
        for (int row = 0; row < input.length; row++) {
            for (int col = 0; col < input[0].length; col++) {
                input[row][col] += 1;
            }
        }
        for (int row = 0; row < input.length; row++) {
            for (int col = 0; col < input[0].length; col++) {
                if (input[row][col] > 9) flash(input, row, col, flashed);
            }
        }
        var flashedSum = 0;
        for (int row = 0; row < input.length; row++) {
            for (int col = 0; col < input[0].length; col++) {
                if (flashed[row][col]) {
                    flashedSum++;
                    input[row][col] = 0;
                }
            }
        }
        return flashedSum;
    }

    static void flash(int[][] input, int row, int col, boolean[][] flashed) {
        if (flashed[row][col]) return;
        flashed[row][col] = true;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (isInsideArray(input, row + i, col + j)) {
                    input[row + i][col + j] += 1;
                    if (input[row + i][col + j] > 9 && flashed[row + i][col + j] == false) {
                        flash(input, row + i, col + j, flashed);
                    }
                }
            }
        }
    }

    static boolean isInsideArray(int[][] input, int row, int col) {
        return ((row >= 0) && (row < input.length) && (col >= 0) && (col < input[0].length));
    }

}
