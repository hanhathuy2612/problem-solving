**Problem: Longest Substring Without Repeating Characters**

Given a string `s`, determine the length of the **longest substring** that contains **no repeated characters**. A substring is a contiguous sequence of characters within the string.

**Input**

* A single string `s`.
* Characters may be ASCII or Unicode.

**Output**

* An integer: the maximum length of a substring of `s` with all distinct characters.

**Constraints**

* `0 ≤ |s| ≤ 100,000`
* `s` may include letters, digits, symbols, and spaces.

**Examples**

* `s = "abcabcbb"` → `3`  (longest: `"abc"`)
* `s = "bbbbb"` → `1`     (longest: `"b"`)
* `s = "pwwkew"` → `3`    (longest: `"wke"`)
* `s = ""` → `0`
* `s = "dvdf"` → `3`      (longest: `"vdf"`)
