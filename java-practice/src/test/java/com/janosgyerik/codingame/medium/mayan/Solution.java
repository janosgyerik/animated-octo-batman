package com.janosgyerik.codingame.medium.mayan;

import java.lang.StringBuilder;
import java.util.*;

class MayanNumeral {
    private final List<String> lines;

    public MayanNumeral(List<String> lines) {
        this.lines = lines;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MayanNumeral && lines.equals(((MayanNumeral) obj).lines);
    }

    @Override
    public int hashCode() {
        return lines.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String line : lines) {
            builder.append(line).append("\n");
        }
        return builder.toString();
    }
}

class MayanCalculator {

    private final Map<MayanNumeral, Integer> numeralToInt;
    private final Map<Integer, MayanNumeral> intToNumeral;

    private MayanCalculator(Map<MayanNumeral, Integer> numeralToInt) {
        this.numeralToInt = numeralToInt;
        this.intToNumeral = new HashMap<>();
        for (Map.Entry<MayanNumeral, Integer> entry : numeralToInt.entrySet()) {
            MayanNumeral numeral = entry.getKey();
            int value = entry.getValue();
            intToNumeral.put(value, numeral);
        }
    }

    public static MayanCalculator fromAlphabet(List<String> alphabet, int widthOfNumerals) {
        Map<MayanNumeral, Integer> numerals = new HashMap<>();
        int numeralCount = alphabet.get(0).length() / widthOfNumerals;
        for (int i = 0; i < numeralCount; ++i) {
            MayanNumeral numeral = extractNumeral(alphabet, widthOfNumerals, i);
            numerals.put(numeral, i);
        }
        return new MayanCalculator(numerals);
    }

    public static MayanNumeral extractNumeral(List<String> alphabet, int widthOfNumerals, int num) {
        List<String> lines = new ArrayList<>();
        for (String line : alphabet) {
            int start = widthOfNumerals * num;
            lines.add(line.substring(start, start + widthOfNumerals));
        }
        return new MayanNumeral(lines);
    }

    public int intValue(MayanNumeral numeral) {
        return numeralToInt.get(numeral);
    }

    public MayanNumeral numeralValue(int num) {
        return intToNumeral.get(num);
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        List<String> alphabet = new ArrayList<>();
        int L = in.nextInt();
        int H = in.nextInt();
        for (int i = 0; i < H; i++) {
            String numeral = in.next();
            alphabet.add(numeral);
        }

        List<String> lines1 = new ArrayList<>();
        int S1 = in.nextInt();
        for (int i = 0; i < S1; i++) {
            String num1Line = in.next();
            lines1.add(num1Line);
        }
        MayanNumeral num1 = new MayanNumeral(lines1);

        List<String> lines2 = new ArrayList<>();
        int S2 = in.nextInt();
        for (int i = 0; i < S2; i++) {
            String num2Line = in.next();
            lines2.add(num2Line);
        }
        MayanNumeral num2 = new MayanNumeral(lines2);

        String operation = in.next();

        MayanCalculator calculator = MayanCalculator.fromAlphabet(alphabet, L);
        int int1 = calculator.intValue(num1);
        int int2 = calculator.intValue(num2);

        MayanNumeral result = calculator.numeralValue(int1 + int2);

        System.out.print(result);
    }
}