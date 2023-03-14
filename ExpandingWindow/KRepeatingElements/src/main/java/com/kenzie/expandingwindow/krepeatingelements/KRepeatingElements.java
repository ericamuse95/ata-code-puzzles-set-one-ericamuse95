package com.kenzie.expandingwindow.krepeatingelements;

/**
 * Contains a problem that can be solved using the Expanding Window Technique.
 */
public class KRepeatingElements {

    /**
     * Given a string s and an integer k, return the length of the longest substring of s such that the
     * frequency of each character in this substring is greater than or equal to k.
     *
     * Example:
     *   s = aaabb
     *   k = 3
     *
     *   result = aaa
     *
     * @param s - the string from which to identify the longest substring where each character appears
     *          at least k times. s will contain only lowercase letters.
     * @param k - the minimum frequency which each character should appear in the substring. k will be
     *          a postive number.
     * @return the length of the longest substring of s where each character appears at least k times.
     */
    public static int kRepeatingElements(String s, int k) {
        int n = s.length();
        int res = 0;
        for (int i = 1; i <= 26; i++) {
            int left = 0, right = 0, unique = 0;
            int[] count = new int[26];
            while (right < n) {
                boolean valid = true;
                char c = s.charAt(right);
                count[c - 'a']++;
                if (count[c - 'a'] == 1) {
                    unique++;
                }
                while (unique > i) {
                    char d = s.charAt(left);
                    count[d - 'a']--;
                    if (count[d - 'a'] == 0) {
                        unique--;
                    }
                    left++;
                }
                for (int j = 0; j < 26; j++) {
                    if (count[j] > 0 && count[j] < k) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    res = Math.max(res, right - left + 1);
                }
                right++;
            }
        }
        return res;
    }
}

