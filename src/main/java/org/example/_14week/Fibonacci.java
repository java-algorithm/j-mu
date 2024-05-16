package org.example._14week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fibonacci {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        final int testCaseCount = Integer.parseInt(br.readLine());

        int[][] dp = new int[41][2];

        dp[0][0] = 1;
        dp[0][1] = 0;
        dp[1][0] = 0;
        dp[1][1] = 1;

        for (int i = 2; i <= 40; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
            dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
        }

        for (int i = 0; i < testCaseCount; i++) {
            final int N = Integer.parseInt(br.readLine());
            System.out.println(dp[N][0] + " " + dp[N][1]);
        }
    }
}
