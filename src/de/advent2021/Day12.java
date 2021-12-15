package de.advent2021;

import java.util.*;
import java.util.function.BiFunction;

public class Day12 {

    public static void main(String[] args) {
        solvePart1();
        solvePart2();
    }

    private static void solvePart1() {
        var caves = Day12.parseInput(ReadFile.readFile("input_day12.txt"));
        var paths = Day12.findPaths(caves.get("start"), Day12::canVisitOnce);
        System.out.println("Solution part 1: " + paths.size());
    }

    private static void solvePart2() {
        var caves = Day12.parseInput(ReadFile.readFile("input_day12.txt"));
        var paths = Day12.findPaths(caves.get("start"), Day12::canVisitTwice);
        System.out.println("Solution part 2: " + paths.size());
    }

    static Map<String, Cave> parseInput(List<String> input) {
        var result = new HashMap<String, Cave>();
        for(var line : input) {
            var split = line.split("-");
            if (result.containsKey(split[0]) == false) {
                result.put(split[0], new Cave(split[0]));
            }
            if (result.containsKey(split[1]) == false) {
                result.put(split[1], new Cave(split[1]));
            }
            result.get(split[0]).connections.add(result.get(split[1]));
            result.get(split[1]).connections.add(result.get(split[0]));
        }
        return result;
    }

    static void findPaths(Cave currentCave, String currentPath, List<String> correctPaths, BiFunction<String, String, Boolean> canVisit) {
        var newPath = new StringBuilder(currentPath);
        if (currentCave.name.equals("start") == false) {
            newPath.append(",");
        }
        newPath.append(currentCave.name);
        if (currentCave.name.equals("end")) {
            correctPaths.add(newPath.toString());
            return;
        }

        for(var next : currentCave.connections) {
            if (canVisit.apply(newPath.toString(), next.name)) {
                findPaths(next, newPath.toString(), correctPaths, canVisit);
            }
        }
    }

    static boolean canVisitOnce(String path, String cave) {
        if (Character.isUpperCase(cave.charAt(0))) return true;
        var split = Arrays.asList(path.split(","));
        if (split.contains(cave)) return false;
        return true;
    }

    static boolean canVisitTwice(String path, String cave) {
        if (Character.isUpperCase(cave.charAt(0))) return true;
        var split = Arrays.asList(path.split(","));
        if (cave.equals("start") && split.contains("start")) return false;
        if (cave.equals("end") && split.contains("end")) return false;
        var numberOfVisits = new HashMap<String, Integer>();
        for(var c : split) {
            if (Character.isLowerCase(c.charAt(0))) {
                numberOfVisits.put(c, numberOfVisits.getOrDefault(c, 0) + 1);
            }
        }
        var number = numberOfVisits.getOrDefault(cave, 0);
        if (number == 0) return true;
        else if ((number == 1)) {
            if (numberOfVisits.values().contains(2)) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    static List<String> findPaths(Cave start, BiFunction<String, String, Boolean> canVisit) {
        var result = new LinkedList<String>();
        findPaths(start, "", result, canVisit);
        return result;
    }

}

class Cave {
    public final String name;
    public final List<Cave> connections;
    public Cave(String name) {
        this.name = name;
        connections = new LinkedList<>();
    }
}