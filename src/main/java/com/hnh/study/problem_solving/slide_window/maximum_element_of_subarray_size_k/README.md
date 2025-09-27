Awesome pick — this is a classic! Here’s everything you need about **“Maximum of all subarrays of size K”** (aka LeetCode 239).

# 1) Problem statement (clear & precise)

* **Input:** An integer array `nums` (length `n`) and an integer `k` (`1 ≤ k ≤ n`).
* **Task:** For **every contiguous subarray** of length `k`, return the **maximum** element of that window.
* **Output:** An array of length `n - k + 1`, where `ans[i]` = max of `nums[i .. i + k - 1]`.

**Example**

```
nums = [1, 3, -1, -3, 5, 3, 6, 7], k = 3
Windows: [1,3,-1], [3,-1,-3], [-1,-3,5], [-3,5,3], [5,3,6], [3,6,7]
Maxes:      3          3           5          5          6          7
Output: [3, 3, 5, 5, 6, 7]
```

# 2) Constraints & edge cases to consider

* `k = 1` ⇒ output is the original array.
* `k = n` ⇒ output has one element: `max(nums)`.
* Negative numbers, duplicates, strictly increasing/decreasing arrays.
* Large `n` (e.g., up to 1e5 or more) demands **O(n)** or **O(n log k)**.

# 3) Approaches (from brute force to optimal)

### A. Brute force – O(n·k)

For each window, scan the `k` elements to find the max.

* Simple but too slow for large inputs.

### B. Max-Heap (PriorityQueue) – O(n log k)

Keep a heap of candidates with their indices; pop while out-of-window.

* Faster than brute force; still not optimal.

### C. **Monotonic Deque (Optimal) – O(n), O(k) space**

Maintain a deque of indices with **values in decreasing order**:

* **Push step:** While deque tail is smaller than current value, pop it (they’ll never be max again).
* **Slide step:** If deque head index is out of the current window (`i - k + 1`), pop it.
* The **head** always holds the index of the current window’s max.

This is the standard interview solution.

# 4) Walkthrough of the deque idea (tiny demo)

`nums = [1,3,-1,-3,5,3,6,7], k = 3`

* i=0,1,2 build first window; after each step, deque keeps indices of a decreasing sequence by value.
* At i=2, head points to `3` ⇒ output[0]=3.
* Move to i=3: pop out-of-window indices; maintain decreasing order; record max; and so on.

# 5) Correct, clean Java solutions

### ✅ Optimal: Monotonic Deque (O(n))

```java
import java.util.*;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (k <= 0 || k > n) throw new IllegalArgumentException("Invalid k");
        int[] ans = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>(); // stores indices

        for (int i = 0; i < n; i++) {
            // 1) Remove indices out of the current window
            int left = i - k + 1;
            if (!dq.isEmpty() && dq.peekFirst() < left) {
                dq.pollFirst();
            }
            // 2) Maintain decreasing deque (by values)
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }
            // 3) Add current index
            dq.offerLast(i);
            // 4) Record answer when the first window completes
            if (i >= k - 1) {
                ans[left] = nums[dq.peekFirst()];
            }
        }
        return ans;
    }
}
```

### Alternative: Max-Heap (O(n log k))

```java
import java.util.*;

public class SlidingWindowMaximumHeap {
    static class Pair {
        int val, idx;
        Pair(int v, int i) { val = v; idx = i; }
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (k <= 0 || k > n) throw new IllegalArgumentException("Invalid k");
        int[] ans = new int[n - k + 1];

        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> b.val - a.val); // max-heap

        for (int i = 0; i < n; i++) {
            pq.offer(new Pair(nums[i], i));
            // Remove out-of-window elements
            int left = i - k + 1;
            if (i >= k - 1) {
                while (pq.peek().idx < left) pq.poll();
                ans[left] = pq.peek().val;
            }
        }
        return ans;
    }
}
```

# 6) Complexity

* **Deque solution:** Time **O(n)** (each index pushed/popped at most once), Space **O(k)**.
* **Heap solution:** Time **O(n log k)**, Space **O(k)**.

# 7) Common pitfalls (avoid these!)

* Forgetting to pop indices that are **outside** the current window.
* Using values instead of **indices** in the deque/heap (indices are needed to check window bounds).
* Not handling `k = 1` (should just return `nums`).
* Off-by-one when writing `ans[left]`.

---

If you want, I can:

* Give you a **unit test** scaffold for these functions, or
* Show a **step-by-step trace** of the deque state on a sample, or
* Provide a ** Kotlin / C++ ** version.
