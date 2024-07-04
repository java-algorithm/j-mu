package org.example._22week;

import java.util.Scanner;

public class GoodNumberArray {

    private static int[] answer;
    private static boolean found;

    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();

        for (int i = 1; i <= 3; i++) {
            int[] numberArray = new int[N];
            numberArray[0] = i;
            dfs(N, 1, numberArray);

            if (found) {
                break;
            }
        }
    }

    public static void dfs(int digits, int depth, int[] numberArray) {
        if (found) {
            return;
        }

        if (depth == digits) {
            found = true;
            answer = numberArray.clone();
            for (final int digit : answer) {
                System.out.print(digit);
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= 3; i++) {
            numberArray[depth] = i;
            if (isInvalidArray(numberArray, i, depth)) {
                continue;
            }

            dfs(digits, depth + 1, numberArray);
        }
    }

    private static boolean isInvalidArray(final int[] numberArray, final int targetValue, final int depth) {
        if (numberArray[depth] == numberArray[depth - 1]) {
            return true;
        }

        for (int j = 1; j < depth; j++) {
            if (j == depth) {
                continue;
            }

            if (numberArray[j] != targetValue) {
                continue;
            }

            boolean notMatch = false;
            for (int i = 1; i < depth - j; i++) {
                if (j - i < 0) {
                    notMatch = true;
                    break;
                }

                if (numberArray[j - i] != numberArray[depth - i]) {
                    notMatch = true;
                    break;
                }
            }
            if (notMatch) {
                continue;
            }

            return true;
        }

        return false;
    }
}
