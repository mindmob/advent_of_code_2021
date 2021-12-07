package de.advent2021;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Day3b {



    public static void main(String[] args) {
        var input = ReadFile.readFile("input_day3a.txt");

        var oxygenList = input;
        var co2List = input;

        var sizeOfOxygen = oxygenList.size();
        var oxygenIndex = 0;
        while (sizeOfOxygen > 1) {
            oxygenList = filterCommon(oxygenList, oxygenIndex);
            sizeOfOxygen = oxygenList.size();
            oxygenIndex++;
        }
        System.out.println("Oxygen rating: " + oxygenList.get(0));

        var sizeOfCo2 = co2List.size();
        var co2Index = 0;
        while (sizeOfCo2 > 1) {
            co2List = filterUncommon(co2List, co2Index);
            sizeOfCo2 = co2List.size();
            co2Index++;
        }
        System.out.println("CO2 rating: " + co2List.get(0));

        var oxygenInt = Integer.parseInt(oxygenList.get(0),2);
        var co2Int = Integer.parseInt(co2List.get(0),2);
        var result = oxygenInt * co2Int;

        System.out.println("result: " + result);

    }

    public static int[] getNumberOfOnes(List<String> input) {
        var numberOfOnes = new int[input.get(0).length()];
        for (var i : input) {
            for (int character = 0; character < i.length(); character++) {
                if (i.charAt(character) == '1') {
                    numberOfOnes[character] += 1;
                }
            }
        }
        return numberOfOnes;
    }

    public static List<String> filterCommon(List<String> input, int index) {
        var result = new LinkedList<String>();
        var numberOfOnes = getNumberOfOnes(input);
        var numberOfZeroes = input.size() - numberOfOnes[index];
        var commonBit = numberOfOnes[index] >= numberOfZeroes ? 1 : 0;

        for(var i : input) {
            if (i.charAt(index) == Character.forDigit(commonBit, 10)) {
                result.add(i);
            }
        }
        return result;
    }

    public static List<String> filterUncommon(List<String> input, int index) {
        var result = new LinkedList<String>();
        var numberOfOnes = getNumberOfOnes(input);
        var numberOfZeroes = input.size() - numberOfOnes[index];
        var uncommonBit = numberOfZeroes <= numberOfOnes[index] ? 0 : 1;

        for(var i : input) {
            if (i.charAt(index) == Character.forDigit(uncommonBit, 10)) {
                result.add(i);
            }
        }
        return result;
    }

    public static Map<Character, List<String>> splitByIndex(List<String> input, int index) {
        var result = new HashMap<Character, List<String>>();
        result.put('0', new LinkedList<>());
        result.put('1', new LinkedList<>());
        for(var i : input) {
            if (i.charAt(index) == '0') {
                result.get('0').add(i);
            } else {
                result.get('1').add(i);
            }
        }
        return result;
    }

}
