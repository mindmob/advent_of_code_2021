package de.advent2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Day12Test {

    List<String> input;

    @BeforeEach
    void setup() {
        input = ReadFile.readFile("input_day12_test0.txt");
    }

    @Test
    void testParseInput() {
        var caves = Day12.parseInput(input);
        Assertions.assertEquals(6, caves.size());
        Assertions.assertEquals(2, caves.get("start").connections.size());
        Assertions.assertEquals(4, caves.get("A").connections.size());
    }

    @Test
    void testCanVisitOnce() {
        var path1 = "start,A,c";
        Assertions.assertTrue(Day12.canVisitOnce(path1, "A"));
        Assertions.assertTrue(Day12.canVisitOnce(path1, "b"));
        Assertions.assertTrue(Day12.canVisitOnce(path1, "end"));
        Assertions.assertFalse(Day12.canVisitOnce(path1, "c"));
    }

    @Test
    void testCanVisitTwice() {
        var path1 = "start,A,c";
        Assertions.assertTrue(Day12.canVisitTwice(path1, "A"));
        Assertions.assertTrue(Day12.canVisitTwice(path1, "b"));
        Assertions.assertTrue(Day12.canVisitTwice(path1, "end"));
        Assertions.assertTrue(Day12.canVisitTwice(path1, "c"));
        var path2 = "start,A,c,c,b";
        Assertions.assertFalse(Day12.canVisitTwice(path2, "c"));
        Assertions.assertFalse(Day12.canVisitTwice(path2, "b"));
        Assertions.assertTrue(Day12.canVisitTwice(path2, "f"));
    }

    @Test
    void testFindPaths() {
        var numberOfPaths = List.of(10, 19, 226);
        for (int i = 0; i < 3; i++) {
            var caves = Day12.parseInput(ReadFile.readFile("input_day12_test" + i + ".txt"));
            var paths = Day12.findPaths(caves.get("start"), Day12::canVisitOnce);
            System.out.println("Paths " + i);
            for(var p : paths) {
                // System.out.println(p);
            }
            Assertions.assertEquals(numberOfPaths.get(i), paths.size());
        }
    }

    @Test
    void testFindPaths2() {
        var numberOfPaths = List.of(36, 103, 3509);
        for (int i = 0; i < 3; i++) {
            var caves = Day12.parseInput(ReadFile.readFile("input_day12_test" + i + ".txt"));
            var paths = Day12.findPaths(caves.get("start"), Day12::canVisitTwice);
            System.out.println("Paths " + i);
            for(var p : paths) {
                // System.out.println(p);
            }
            Assertions.assertEquals(numberOfPaths.get(i), paths.size());
        }
    }

}
