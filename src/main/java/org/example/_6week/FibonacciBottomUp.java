package org.example._6week;

import java.util.Scanner;

public class FibonacciBottomUp {

    private static long[] dp = new long[101];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        long answer = fibonacci(n);

        System.out.println(answer);
    }

    private static long fibonacci(int n) {
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}
