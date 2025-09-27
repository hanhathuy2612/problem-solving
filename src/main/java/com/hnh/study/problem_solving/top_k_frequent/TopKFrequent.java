package com.hnh.study.problem_solving.top_k_frequent;

import java.util.*;

public class TopKFrequent {
    public static int[] topKFrequent(int[] nums, int k) {
        if (nums.length == 0 || k == 0 || k > nums.length) {
            return new int[0];
        }

        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                Comparator.comparingInt(a -> a[1])
        );

        for (var entry : freqMap.entrySet()) {
            pq.offer(new int[]{entry.getKey(), entry.getValue()});
            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = Objects.requireNonNull(pq.poll())[0];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7};
        int k = 2;
        System.out.println("Top " + k + " frequent numbers: " + Arrays.toString(topKFrequent(nums, k)));
    }
}
