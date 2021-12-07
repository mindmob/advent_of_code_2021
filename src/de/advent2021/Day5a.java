package de.advent2021;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.stream.Stream;

public class Day5a {

    public static void main(String[] args) {
        solvePart1();
        solvePart2();
    }

    private static void solvePart1() {
        var input = ReadFile.readFile("input_day5a.txt");
        var lines = parseInput(input);
        var filtered = lines.filter(Day5a::isVerticalOrHorizontal);
        var field = createField(filtered);
        System.out.println("Result part 1: " + field.numberOfIntersections());
    }

    private static void solvePart2() {
        var input = ReadFile.readFile("input_day5a.txt");
        var lines = parseInput(input);
        var filtered = lines.filter(line -> Day5a.isVerticalOrHorizontal(line) || Day5a.isDiagonal(line));
        var field = createField(filtered);
        System.out.println("Result part 2: " + field.numberOfIntersections());
    }

    static Stream<Line2D> parseInput(List<String> input) {
        return input.stream().map(Day5a::parseLine);
    }

    static Line2D parseLine(String input) {
        var split0 = input.split(" -> ");
        var split1 =  split0[0].split(",");
        var split2 = split0[1].split(",");
        var x1 = Integer.parseInt(split1[0]);
        var y1 = Integer.parseInt(split1[1]);
        var x2 = Integer.parseInt(split2[0]);
        var y2 = Integer.parseInt(split2[1]);
        return new Line2D.Float(x1, y1, x2, y2);
    }

    static boolean isVerticalOrHorizontal(Line2D line) {
        return (line.getX1() == line.getX2()) || (line.getY1() == line.getY2());
    }

    static boolean isDiagonal(Line2D line) {
        return Math.abs(line.getX1() - line.getX2()) == Math.abs(line.getY1() - line.getY2());
    }

    static Line2D maxBoundsOfTwoLines(Line2D line1, Line2D line2) {
            var bounds1 = line1.getBounds2D();
            var bounds2 = line2.getBounds2D();
            var maxX = Math.max(bounds1.getMaxX(), bounds2.getMaxX());
            var maxY = Math.max(bounds1.getMaxY(), bounds2.getMaxY());
        return new Line2D.Float(0, 0, (float)maxX, (float)maxY);
    }

    static Line2D maxBoundsOfAll(Stream<Line2D> lines) {
        return lines.reduce(new Line2D.Float(0f, 0f, 0f, 0f), Day5a::maxBoundsOfTwoLines);
    }

    static Field createField(Stream<Line2D> lines) {
        // dirty workaround to find the max bounds without consuming the stream.
        // a better way would be to make the field array dynamic and expand it when needed without having to know the bounds beforehand.
        var tmpLines = lines.toList();
        var bounds = maxBoundsOfAll(tmpLines.stream());
        var field = new Field((int)Math.round(bounds.getX2()) + 1, (int)Math.round(bounds.getY2()) + 1);
        field.addAll(tmpLines.stream());
        return field;
    }



}

// an easier way to find all intersections would be to calculate them directly from the lines.
// however, this way we get a nice graphical output ;-)
class Field {
    private int[][] field;
    public Field(int sizeX, int sizeY) {
        field = new int[sizeY][sizeX];
    }
    public void print() {
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[0].length; x++) {
                System.out.print(field[y][x] + " ");
            }
            System.out.print("\n");
        }
    }
    public void addLine(Line2D line) {
        var lineBounds = line.getBounds2D();
        var boundsX1 = (int)Math.round(lineBounds.getMinX());
        var boundsX2 = (int)Math.round(lineBounds.getMaxX());
        var boundsY1 = (int)Math.round(lineBounds.getMinY());
        var boundsY2 = (int)Math.round(lineBounds.getMaxY());
        for (int x = boundsX1; x <= boundsX2; x++) {
            for (int y = boundsY1; y <= boundsY2; y++) {
                if (line.ptLineDist(new Point2D.Float(x, y)) < 0.01) {
                    field[y][x]++;
                }
            }
        }
    }
    public void addAll(Stream<Line2D> lines) {
        lines.forEach(this::addLine);
    }
    public int get(int x, int y) {
        return field[y][x];
    }
    public int numberOfIntersections() {
        int number = 0;
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[0].length; x++) {
                if (field[y][x] > 1) number++;
            }
        }
        return number;
    }
}
