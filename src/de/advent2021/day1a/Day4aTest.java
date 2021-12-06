package de.advent2021.day1a;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Day4aTest {

    static List<String> input;

    @BeforeAll
    static void setup() {
        input = ReadFile.readFile("input_day4a_test.txt");
    }

    @Test
    void testParseInput() {
        var numbers = Day4a.parseNumbers(input.get(0));
        Assertions.assertEquals(27, numbers.size());
        Assertions.assertEquals(7, numbers.get(0));
    }

    @Test
    void testSizeOfBingos() {
        var sizeOfBingos = Day4a.getSizeOfBingos(input.get(2));
        Assertions.assertEquals(5, sizeOfBingos);
    }

    @Test
    void testNumberOfBingos() {
        var numberOPfBingos = Day4a.numberOfBingos(input, Day4a.getSizeOfBingos(input.get(2)));
        Assertions.assertEquals(3, numberOPfBingos);
    }

    @Test
    void testBingoFromList() {
        List<String> testinput = List.of(
                "22 13 17 11  0",
                " 8  2 23  4 24",
                "21  9 14 16  7",
                " 6 10  3 18  5",
                " 1 12 20 15 19"
        );
        var bingo = Bingo.fromList(testinput);
        Assertions.assertEquals(5, bingo.size);
        Assertions.assertEquals(4, bingo.get(1, 3));
    }

    @Test
    void testBingoSetFromList() {
        var bingoSet = BingoSet.fromList(input);
        Assertions.assertEquals(27, bingoSet.numbers.size());
        Assertions.assertEquals(3, bingoSet.bingos.size());
        Assertions.assertEquals(19, bingoSet.bingos.get(1).get(2, 0));
    }

    @Test
    void testBingoHasBingo() {
        var bingoSet = BingoSet.fromList(input);
        Assertions.assertFalse(bingoSet.bingos.get(0).hasBingo());
        for (int i = 0; i < bingoSet.bingos.get(0).size; i++) {
            bingoSet.bingos.get(0).mark(21);
            bingoSet.bingos.get(0).mark(9);
            bingoSet.bingos.get(0).mark(14);
            bingoSet.bingos.get(0).mark(16);
            bingoSet.bingos.get(0).mark(7);
        }
        Assertions.assertTrue(bingoSet.bingos.get(0).hasBingo());
    }

    @Test
    void testLastBingoScore() {
        var bingoSet = BingoSet.fromList(input);
        bingoSet.playTillLastWinner();
        Assertions.assertEquals(1924, bingoSet.lastScore);
    }

}
