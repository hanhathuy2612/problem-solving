package com.hnh.study.problem_solving.longest_substring_without_repeating;

public class LongestSubstringWithoutRepeating {
    public static void main(String[] args) {
        String str = "abcabcbb";
        System.out.println("Result: " + longestSubstring(str));
    }

    static String longestSubstring(String str) {
        int[] track = new int[256];
        int left = 0, best = 0;
        for (int right = 0; right < str.length(); right++) {
            char ch = str.charAt(right);
            if (track[ch] >= left) {
                left = track[ch] + 1;
            }
            track[ch] = right;
            best = Math.max(best, right - left + 1);
        }
        return str.substring(left, left + best);
    }
}
