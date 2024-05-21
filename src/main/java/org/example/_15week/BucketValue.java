package org.example._15week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BucketValue {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        final String request = br.readLine();
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < request.length(); i++) {
            final String ch = String.valueOf(request.charAt(i));
            if (stack.isEmpty() && isCloseBucket(ch)) {
                System.out.println(0);
                return;
            }

            if (isOpenBucket(ch)) {
                stack.add(ch);
                continue;
            }

            // 여기부턴 항상 ch는 close bucket
            // prevValue는 항상 숫자 아니면 open bucket
            final String prevValue = stack.pop();
            if (isNonNumeric(prevValue)) {
                if (isNonMatch(prevValue, ch)) {
                    System.out.println(0);
                    return;
                }

                if (prevValue.equals("(")) {
                    stack.add(String.valueOf(2));
                } else {
                    stack.add(String.valueOf(3));
                }

                continue;
            }

            // 여기부터 prevValue는 항상 숫자.
            int value = Integer.parseInt(prevValue);
            while (!stack.isEmpty()) {
                final String prev = stack.peek();
                if (isNumeric(prev)) {
                    value += Integer.parseInt(stack.pop());
                } else {
                    break;
                }
            }

            if (stack.isEmpty()) {
                System.out.println(0);
                return;
            }

            // prev는 항상 open bucket
            // ch는 항상 close bucket
            final String prev = stack.pop();
            if (isNonMatch(prev, ch)) {
                System.out.println(0);
                return;
            }

            if (ch.equals("]")) {
                value *= 3;
            } else {
                value *= 2;
            }
            stack.add(String.valueOf(value));
        }

        int answer = 0;
        while (!stack.isEmpty()) {
            final String pop = stack.pop();
            if (isNonNumeric(pop)) {
                System.out.println(0);
                return;
            }

            final int value = Integer.parseInt(pop);
            answer+=value;
        }

        System.out.println(answer);
    }

    private static boolean isOpenBucket(final String ch) {
        return !isCloseBucket(ch);
    }

    private static boolean isCloseBucket(final String ch) {
        return ch.equals(")") || ch.equals("]");
    }

    private static boolean isNonNumeric(final String prevValue) {
        return prevValue.equals("(") || prevValue.equals(")") || prevValue.equals("[") || prevValue.equals("]");
    }

    private static boolean isNumeric(final String prevValue) {
        return !isNonNumeric(prevValue);
    }

    private static boolean isNonMatch(final String prev, final String prevValue) {
        return !isMatch(prev, prevValue);
    }

    private static boolean isMatch(final String prev, final String ch) {
        if (prev.equals("(") && ch.equals(")")) {
            return true;
        }

        if (prev.equals("[") && ch.equals("]")) {
            return true;
        }

        return false;
    }
}
