package org.example._6week;

public class FibonacciTopDown {
    private static long[] dp = new long[101];

    public static void main(String[] args) {
        int n = 100;
        dp[1] = 1;
        dp[2] = 1;

        final long answer = fibonacci(n);
        System.out.println(answer);
    }

    private static long fibonacci(int n) {
        // already caching data
        if (dp[n] != 0) {
            return dp[n];
        }

        dp[n] = fibonacci(n - 1) + fibonacci(n - 2);
        return dp[n];
    }
}
