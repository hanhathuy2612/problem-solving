# Problem statement

Given an integer array `arr` and an integer `k` (`k > 0`), find the **maximum sum** among all **contiguous** subarrays of length exactly `k`.

* **Input:** `arr = [2, 1, 5, 1, 3, 2]`, `k = 3`
* **Output:** `9` (from subarray `[5, 1, 3]`)

# Constraints & edge cases

* `1 ≤ k ≤ arr.length` (if not, return error/edge handling).
* Works with **negative numbers** too (still fixed length).
* If values may be large, consider using `long` for sum to avoid overflow.

# Approaches

## 1) Brute force — O(n·k)

Compute each window’s sum from scratch. Simple but slow.

## 2) Sliding window — O(n), O(1) extra space

* Compute sum of the **first k** elements.
* Slide window one step at a time:

    * Subtract the element that leaves the window.
    * Add the new element that enters.
* Track `maxSum`.

# Dry run (arr = [2,1,5,1,3,2], k=3)

* Init: sum = 2+1+5 = 8, max=8
* Slide: remove 2, add 1 → sum=7, max=8
* Slide: remove 1, add 3 → sum=9, **max=9**
* Slide: remove 5, add 2 → sum=6, max=9
  Answer: 9
# Implementation

```java
package com.hnh.study.problem_solving.maximum_sum_subarray_of_size_k;

import java.util.Arrays;

public class MaximumSumSubarrayOfSizeK {
    static class Result {
        int[] subArray;
        int sum;

        public Result(int[] subArray, int sum) {
            this.subArray = subArray;
            this.sum = sum;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "subArray=" + Arrays.toString(subArray) +
                    ", sum=" + sum +
                    '}';
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 2;
        System.out.println("Result: " + findMaxSumSubarray(k, arr));
        System.out.println("List of windows");
        for (int[] window : getAllWindows(k, arr)) {
            System.out.println(Arrays.toString(window));
        }
    }

    static Result findMaxSumSubarray(int k, int[] arr) {
        if (k == 0 || arr.length == 0 || k > arr.length) {
            System.err.println("Invalid input");
            return null;
        }
        int windowStart = 0;
        int windowSum = sumWindow(0, k - 1, arr);
        int maxSum = windowSum;
        for (int right = k; right < arr.length; right++) {
            int left = right - k + 1;
            windowSum += arr[right];
            windowSum -= arr[left - 1];

            if (windowSum > maxSum) {
                maxSum = windowSum;
                windowStart = left;
            }
        }

        return new Result(copySubArray(windowStart, windowStart + k - 1, arr), maxSum);
    }

    static int[][] getAllWindows(int k, int[] arr) {
        int[][] windows = new int[arr.length - k + 1][k];
        for (int i = 0; i < arr.length - k + 1; i++) {
            int end = i + k - 1;
            windows[i] = copySubArray(i, end, arr);
        }
        return windows;
    }

    static int[] copySubArray(int start, int end, int[] arr) {
        int[] dest = new int[end - start + 1];
        for (int i = start; i <= end; i++) {
            dest[i - start] = arr[i];
        }
        return dest;
    }

    static int sumWindow(int start, int end, int[] arr) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += arr[i];
        }
        return sum;
    }
}
```