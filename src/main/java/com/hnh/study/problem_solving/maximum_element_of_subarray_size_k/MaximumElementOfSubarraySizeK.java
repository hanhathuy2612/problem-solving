package com.hnh.study.problem_solving.maximum_element_of_subarray_size_k;

import java.util.Arrays;

public class MaximumElementOfSubarraySizeK {
    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 4;
        System.out.println("Result: " + Arrays.toString(findMaximumElementOfSubarraySizeK2(k, arr)));
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

    static int[] findMaximumElementOfSubarraySizeK2(int k, int[] nums) {
        if (nums == null) throw new IllegalArgumentException("nums is null");
        int n = nums.length;
        if (k <= 0 || k > n) throw new IllegalArgumentException("Invalid k: " + k);
        if (k == 1) return Arrays.copyOf(nums, n);

        int[] ans = new int[n - k + 1];
        int trackMax = 0;

        for (int i = 0; i < n; i++) {
            trackMax = Math.max(trackMax, nums[i]);
            System.out.println("i=" + i + ", trackMax=" + trackMax);
            // Record result when we have a full window
            if (i >= k - 1) {
                int left = i - k + 1;
                ans[left] = trackMax;
                trackMax = nums[left + 1];
                System.out.println("Captured: ans[" + i + "] = " + ans[left] + ", trackMax=" + trackMax);
            }
        }
        return ans;
    }
}
