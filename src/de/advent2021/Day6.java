package de.advent2021;

import java.util.LinkedList;
import java.util.List;

public class Day6 {

    public static void main(String[] args) {
        var fishes = ReadFile.readSingleLineAsIntegers("input_day6.txt").map(Lanternfish::new).toList();
        for (int i = 0; i < 80; i++) {
            fishes = Day6.step(fishes);
        }
        System.out.println("Result part 1: " + fishes.size());
    }

    public static List<Lanternfish> step(List<Lanternfish> fishes) {
        var newFishes = new LinkedList<Lanternfish>();
        for(var fish : fishes) {
            var newFish = fish.step();
            newFishes.add(fish);
            if (newFish) {
                newFishes.add(new Lanternfish());
            }
        }
        return newFishes;
    }

}

class Lanternfish {
    private int timer;
    public Lanternfish() {
        timer = 8;
    }
    public Lanternfish(int timer) {
        this.timer = timer;
    }
    public boolean step() {
        if (timer == 0) {
            timer = 6;
            return true;
        }
        timer -= 1;
        return false;
    }
    public int getTimer() {
        return timer;
    }
}