package com.hnh.study.problem_solving.longest_substring_without_repeating;

public class LongestSubstringWithoutRepeating {
    public static void main(String[] args) {
        String str = "abcdefghtiupLoppn,phy";
        System.out.println("Result: " + longestSubstringWithoutRepeating(str));
    }

    static String longestSubstringWithoutRepeating(String str) {
        int[] track = new int[128];
        int left = 0, best = 0;
        int start = 0;
        for (int right = 0; right < str.length(); right++) {
            char ch = str.charAt(right);
            if (track[ch] >= left) {
                left = track[ch] + 1;
            }
            track[ch] = right;
            if (right - left + 1 > best) {
                best = right - left + 1;
                start = left - 1;
            }
        }
        System.out.println("left: " + start + ", best: " + best);
        return str.substring(start, start + best + 1);
    }
}
