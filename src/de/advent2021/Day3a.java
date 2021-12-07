package de.advent2021;

import java.util.Arrays;
import java.util.List;

public class Day3a {



    public static void main(String[] args) {
        var input = ReadFile.readFile("input_day3a.txt");

        var lengthOfInput = input.get(0).length();
        var numberOfInput = input.size();

        int[] numberOfOnes = getNumberOfOnes(input, lengthOfInput);
        System.out.println("number of ones: " + Arrays.toString(numberOfOnes));

        var commonBits = new StringBuffer();
        var uncommonBits = new StringBuffer();
        for (int i = 0; i < lengthOfInput; i++) {
            if (numberOfOnes[i] > numberOfInput / 2) {
                commonBits.append('1');
                uncommonBits.append('0');
            } else {
                commonBits.append('0');
                uncommonBits.append('1');
            }
        }
        System.out.println("common bits: " + commonBits);
        var gammaRate = Integer.parseInt(commonBits.toString(),2);
        var epsilonRate = Integer.parseInt(uncommonBits.toString(),2);
        var result = gammaRate * epsilonRate;
        System.out.println("result: " + result);

    }

    private static int[] getNumberOfOnes(List<String> input, int lengthOfInput) {
        var numberOfOnes = new int[lengthOfInput];
        for (var i : input) {
            for (int character = 0; character < i.length(); character++) {
                if (i.charAt(character) == '1') {
                    numberOfOnes[character] += 1;
                }
            }
        }
        return numberOfOnes;
    }

}
