package com.kenzie.gettingstarted.balance;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Drills to apply the problem-solving framework on variations of the Balanced Parentheses problem.
 */
public class BalancedParens {

    /**
     * Determine whether a string composed entirely of opening and closing parentheses is balanced.
     * If a closing paren appears without an opening paren, the string is not balanced.
     * If an opening paren is not closed by the end of the string, it is not balanced.
     * <p>
     * BalancedParensTest includes some sample test cases.*
     *
     * @param parens A String of opening and closing parentheses
     * @return true if every opening and closing paren has a partner
     */
    public boolean areParensBalanced(String parens) {
        int count = 0;
        for (char c : parens.toCharArray()) {
            if (c == '(') {
                count++;
            }
            if (c == ')') {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    /**
     * Placeholder for the we-do classroom activity for Problem-Solving Framework.
     * <p>
     * BalancedParensTest includes some sample test cases.
     *
     * @param text A text String, described in the classroom.
     * @return True as described in the classroom, false otherwise.
     */
    public static boolean isBalanced(String text) {
        Stack<Character> stack = new Stack<Character>();
        //loop over each character
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            // if it's an open grouping character, push it on the stack
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                //if it's a closing grouping character, pop the stack
                char top = stack.pop();
                if ((ch == ')' && top != '(') || (ch == ']' && top != '[') || (ch == '}' && top != '{')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * Placeholder for the you-do classroom activity for Problem-Solving Framework.
     * <p>
     * BalancedParensTest includes some sample test cases.
     *
     * @param text A text String, described in the classroom.
     * @return True as described in the classroom, false otherwise.
     */
    public boolean areSmileysBalanced(String text) {
            int openCount = 0;
            Matcher matcher = Pattern.compile("[:;][-~]?[)D(\\[{\\\\/]").matcher(text);
            while (matcher.find()) {
                String smiley = matcher.group();
                if (smiley.charAt(0) == ':' && smiley.charAt(smiley.length() - 1) == ')' && (openCount == 0 || text.charAt(matcher.start() - 1) == '(' || text.charAt(matcher.start() - 1) == '[' || text.charAt(matcher.start() - 1) == '{')) {
                    // This is a valid smiley
                    openCount--;
                } else if (smiley.charAt(0) == ')' && smiley.charAt(smiley.length() - 1) == '(' && (openCount == 0 || text.charAt(matcher.start() - 1) == ')' || text.charAt(matcher.start() - 1) == ']' || text.charAt(matcher.start() - 1) == '}')) {
                    // This is a valid "reverse" smiley
                    openCount--;
                }
            }
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (c == '(' || c == '[' || c == '{') {
                    openCount++;
                } else if (c == ')' || c == ']' || c == '}') {
                    openCount--;
                    if (openCount < 0) {
                        // Too many closing characters
                        return false;
                    }
                }
            }
            return openCount == 0;
        }
}
