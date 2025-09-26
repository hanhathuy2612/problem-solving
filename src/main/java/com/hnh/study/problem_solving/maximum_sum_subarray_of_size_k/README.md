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