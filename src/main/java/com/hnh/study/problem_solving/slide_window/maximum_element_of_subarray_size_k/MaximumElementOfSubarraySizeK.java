package com.hnh.study.problem_solving.slide_window.maximum_element_of_subarray_size_k;

import java.util.Arrays;

public class MaximumElementOfSubarraySizeK {
    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 4;
        System.out.println("Result: " + Arrays.toString(findMaximumElementOfSubarraySizeK(k, arr)));
    }

    static int[] findMaximumElementOfSubarraySizeK(int k, int[] arr) {
        if (arr == null) throw new IllegalArgumentException("arr is null");
        int n = arr.length;
        if (k <= 0 || k > n) throw new IllegalArgumentException("Invalid k: " + k);

        int[] res = new int[n - k + 1];
        for (int left = 0; left + k <= n; left++) {
            int max = arr[left];
            for (int i = left + 1; i < left + k; i++) {
                if (arr[i] > max) max = arr[i];
            }
            res[left] = max;
        }
        return res;
    }
}
