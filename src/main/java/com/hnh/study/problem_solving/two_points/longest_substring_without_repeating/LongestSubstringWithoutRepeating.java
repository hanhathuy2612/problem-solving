package com.hnh.study.problem_solving.two_points.longest_substring_without_repeating;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestSubstringWithoutRepeating {
    public static void main(String[] args) {
        String str = "khianhquathunglunglabongdemghibanchan";
        System.out.println("Best Result: " + longestSubstringWithoutRepeating(str));
    }

    static Result longestSubstringWithoutRepeating(String str) {
        int[] track = new int[128];
        Arrays.fill(track, -1);
        List<Result> listResults = new ArrayList<>();

        int left = 0, best = 0;
        int start = 0;
        for (int right = 0; right < str.length(); right++) {
            char ch = str.charAt(right);
            if (track[ch] >= left) {
                left = track[ch] + 1;
            }
            track[ch] = right;
            int len = right - left + 1;
            if (len > best) {
                start = left;
                best = len;
                listResults.add(new Result(
                        str.substring(start, start + best),
                        best
                ));
            }
        }
        for (Result item : listResults) {
            System.out.println("sub: " + item);
        }
        System.out.println("left: " + start + ", best: " + best);
        return new Result(
                str.substring(start, start + best),
                best
        );
    }

    public static class Result {
        String substring;
        int length;

        public Result(String substring, int length) {
            this.substring = substring;
            this.length = length;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "substring='" + substring + '\'' +
                    ", length=" + length +
                    '}';
        }
    }
}
