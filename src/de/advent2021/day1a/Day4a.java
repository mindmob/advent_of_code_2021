package de.advent2021.day1a;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Day4a {

    public static void main(String[] args) {
        var input = ReadFile.readFile("input_day4a.txt");
        var bingoSet = BingoSet.fromList(input);
        bingoSet.playTillLastWinner();
        System.out.println("Score: " + bingoSet.lastScore);
    }

    static List<Integer> parseNumbers(String input) {
        return Arrays.stream(input.trim().split(",")).map(Integer::parseInt).toList();
    }

    static int getSizeOfBingos(String input) {
        return input.trim().split("( )+").length;
    }

    static int numberOfBingos(List<String> input, int sizeOfBingos) {
        return (input.size() - 1) / (sizeOfBingos + 1);
    }

}

class Bingo {
    private int[][] bingo;
    private boolean[][] marked;
    public int size;
    public static Bingo fromList(List<String> input) {
        var bingo = new Bingo();
        bingo.size = Day4a.getSizeOfBingos(input.get(0));
        bingo.bingo = new int[bingo.size][bingo.size];
        bingo.marked = new boolean[bingo.size][bingo.size];
        for (int row = 0; row < bingo.size; row++) {
            var rowSplit = input.get(row).trim().split("( )+");
            for (int col = 0; col < bingo.size; col++) {
                try {
                    bingo.bingo[row][col] = Integer.parseInt(rowSplit[col]);
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            }
        }
        return bingo;
    }
    public int get(int row, int col) {
        return bingo[row][col];
    }
    public boolean hasBingo() {
        for (int i = 0; i < size; i++) {
            boolean rowBingo = marked[i][0];
            boolean colBingo = marked[0][i];
            for (int j = 0; j < size; j++) {
                rowBingo = rowBingo && marked[i][j];
                colBingo = colBingo && marked[j][i];
            }
            if (rowBingo || colBingo) return true;
        }
        return false;
    }
    public void mark(int number) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (get(row, col) == number) marked[row][col] = true;
            }
        }
    }
    public int sumOfUnmarkedNumbers() {
        var number = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (marked[row][col] == false) {
                    number += bingo[row][col];
                }
            }
        }
        return number;
    }
}

class BingoSet {
    public List<Bingo> bingos;
    public List<Integer> numbers;
    public int currentNumberIndex = 0;
    public int lastPlayedNumber = -1;
    public List<Bingo> winners = new LinkedList<>();
    public int lastScore = 0;
    public static BingoSet fromList(List<String> input) {
        var bingoSet = new BingoSet();
        bingoSet.numbers = Day4a.parseNumbers(input.get(0));
        var sizeOfBingos = Day4a.getSizeOfBingos(input.get(2));
        var numberOfBingos = Day4a.numberOfBingos(input, sizeOfBingos);
        bingoSet.bingos = new LinkedList<>();
        for (int i = 0; i < numberOfBingos; i++) {
            var startIndex = 2 + (i * (sizeOfBingos + 1));
            var endIndex = 2 + (i * (sizeOfBingos + 1) + sizeOfBingos);
            var bingo = Bingo.fromList(input.subList(startIndex, endIndex));
            bingoSet.bingos.add(bingo);
        }
        return bingoSet;
    }
    public List<Bingo> playTillLastWinner() {
        for( var number : numbers) {
            for(var bingo : bingos) {
                bingo.mark(number);
                if (bingo.hasBingo() && (winners.contains(bingo) == false)) {
                    System.out.println("We have a winner: " + bingos.indexOf(bingo));
                    this.winners.add(bingo);
                    this.lastScore = bingo.sumOfUnmarkedNumbers() * number;
                    if (this.winners.size() == bingos.size()) {
                        return winners;
                    }
                }
            }
            lastPlayedNumber = number;
            currentNumberIndex++;
        }
        return winners;
    }
}