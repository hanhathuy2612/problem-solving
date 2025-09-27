package com.hnh.study.problem_solving.roman_to_integer;

import java.util.Map;

public class RomanToInteger {
    public static int romanToInt(String s) {
        Map<Character, Integer> roman = Map.of(
                'I', 1, 'V', 5, 'X', 10, 'L', 50,
                'C', 100, 'D', 500, 'M', 1000
        );
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            int value = roman.get(s.charAt(i));
            if (i + 1 < s.length() && value < roman.get(s.charAt(i + 1))) {
                result -= value;
            } else {
                result += value;
            }
        }
        return result;
    }

    public static boolean isValidRoman(String s) {
        return s.matches("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
    }

    public static void main(String[] args) {
        String romanNumeral = "MCMXCIV";
        System.out.println("Roman numeral: " + romanNumeral);
        if (!isValidRoman(romanNumeral)) {
            System.out.println("Invalid Roman numeral");
            return;
        }
        System.out.println("Value: " + romanToInt(romanNumeral));
    }
}
