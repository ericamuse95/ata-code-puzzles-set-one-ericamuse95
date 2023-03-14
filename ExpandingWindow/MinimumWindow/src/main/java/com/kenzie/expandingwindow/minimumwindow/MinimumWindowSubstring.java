package com.kenzie.expandingwindow.minimumwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains a problem that can be solved using the Expanding Window Technique.
 */
public class MinimumWindowSubstring {

    /**
     * Given two strings s and t, return the minimum window substring of s such that every character in
     * t (including duplicates) is included in the window. If there is no such substring, return the
     * empty string "".
     *
     * Example:
     *   s = "ADOBECODEBANC"
     *   t = "ABC"
     *
     *   result = "BANC"
     *
     * @param s - the string from which to identify the shortest substring where each character in t appears.
     * @param t - the string containing all the characters that must appear in the substring from s.
     * @return the shortest substring of s in which each character in t appears.
     */
    public static String minimumWindowSubstring(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        // Count the frequency of each character in t.
        Map<Character, Integer> targetFreqs = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetFreqs.put(c, targetFreqs.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int minLeft = 0, minRight = Integer.MAX_VALUE;
        int count = 0;
        Map<Character, Integer> windowFreqs = new HashMap<>();

        while (right < s.length()) {
            char c = s.charAt(right);
            windowFreqs.put(c, windowFreqs.getOrDefault(c, 0) + 1);

            if (targetFreqs.containsKey(c) && windowFreqs.get(c).intValue() == targetFreqs.get(c).intValue()) {
                //found another instance of a character that we need to include in the substring.
                count++;
            }

            while (count == targetFreqs.size()) {
                // found a substring containing all the characters we need.
                if (right - left < minRight - minLeft) {
                    minLeft = left;
                    minRight = right;
                }

                char leftChar = s.charAt(left);
                windowFreqs.put(leftChar, windowFreqs.get(leftChar) - 1);

                if (targetFreqs.containsKey(leftChar) && windowFreqs.get(leftChar) < targetFreqs.get(leftChar)) {
                    // removed a character from the substring that we needed to include.
                    count--;
                }

                left++;
            }

            right++;
        }

        return minRight == Integer.MAX_VALUE ? "" : s.substring(minLeft, minRight + 1);
    }
}
