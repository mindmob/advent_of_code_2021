package de.advent2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Day9 {

    public static void main(String[] args) {
        var field = Day9.parseInput(ReadFile.readFile("input_day9.txt"));
        var riskLevel = Day9.riskLevel(field);
        System.out.println("Risk level: " + riskLevel);

        var sizeOfThreeBiggestBasins = sizeOfTopThreeBasins(field);
        System.out.println("Size of top three basins: " + sizeOfThreeBiggestBasins);
    }

    static int[][] parseInput(List<String> input) {
        var result = new int[input.size()][input.get(0).length()];
        for (int row = 0; row < input.size(); row++) {
            for (int col = 0; col < input.get(0).length(); col++) {
                result[row][col] = Integer.parseInt(input.get(row).substring(col, col+1));
            }
        }
        return result;
    }

    static boolean isLowPoint(int[][] input, int row, int col) {
        try { if (input[row - 1][col] <= input[row][col]) return false; } catch (IndexOutOfBoundsException e) {}
        try { if (input[row + 1][col] <= input[row][col]) return false; } catch (IndexOutOfBoundsException e) {}
        try { if (input[row][col - 1] <= input[row][col]) return false; } catch (IndexOutOfBoundsException e) {}
        try { if (input[row][col + 1] <= input[row][col]) return false; } catch (IndexOutOfBoundsException e) {}

        return true;
    }

    static int riskLevel(int[][] input) {
        var riskLevel = 0;
        for (int row = 0; row < input.length; row++) {
            for (int col = 0; col < input[0].length; col++) {
                if (isLowPoint(input, row, col)) riskLevel += input[row][col] + 1;
            }
        }
        return riskLevel;
    }

    static List<Point> lowPoints(int[][] field) {
        var points = new LinkedList<Point>();
        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field[0].length; col++) {
                if (isLowPoint(field, row, col)) points.add(new Point(row, col));
            }
        }
        return points;
    }

    static List<Point> findBasin(int[][] field, Point lowPoint) {
        var basin = new LinkedList<Point>();
        addToBasin(basin, field, lowPoint);
        return basin;
    }

    static void addToBasin(List<Point> basin, int[][] field, Point p) {
        if (basin.contains(p)) return;
        if (p.col() < 0 || p.row() < 0 || p.col() >= field[0].length || p.row() >= field.length) return;
        if (field[p.row()][p.col()] == 9) return;
        basin.add(p);
        addToBasin(basin, field, new Point(p.row() - 1, p.col()));
        addToBasin(basin, field, new Point(p.row() + 1, p.col()));
        addToBasin(basin, field, new Point(p.row(), p.col() - 1));
        addToBasin(basin, field, new Point(p.row(), p.col() + 1));
    }

    static int sizeOfTopThreeBasins(int[][] field) {
        var lowPoints = lowPoints(field);
        var basins = new ArrayList<List<Point>>();
        for(var p : lowPoints) {
            var basin = findBasin(field, p);
            basins.add(basin);
        }
        Collections.sort(basins, (o1, o2) -> o2.size() - o1.size());
        int sizeProduct = 1;
        for (int i = 0; i < 3; i++) {
            sizeProduct *= basins.get(i).size();
        }
        return  sizeProduct;
    }

}

record Point(int row, int col) {
}
