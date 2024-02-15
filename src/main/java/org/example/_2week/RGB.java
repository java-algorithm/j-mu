package org.example._2week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RGB {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;
    private static int[][] costs;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        costs = new int[N][3];
        dp = new int[N][3];

        for (int i = 0; i < N; i++) {
            int[] cost = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            costs[i] = cost;
        }

        // first initialize
        dp[0][RED] = costs[0][RED];
        dp[0][GREEN] = costs[0][GREEN];
        dp[0][BLUE] = costs[0][BLUE];

        int minCost = Math.min(Math.min(calcluate(N - 1, RED), calcluate(N - 1, GREEN)), calcluate(N - 1, BLUE));
    }

    private static int calcluate(int i, int color) {
        if (dp[i][color] == 0) {
            if (color == RED) {
                dp[i][RED] = Math.min(calcluate(i - 1, GREEN), calcluate(i - 1, BLUE)) + costs[i][RED];
            } else if (color == GREEN) {
                dp[i][GREEN] = Math.min(calcluate(i - 1, RED), calcluate(i - 1, BLUE)) + costs[i][GREEN];
            } else {
                dp[i][BLUE] = Math.min(calcluate(i - 1, RED), calcluate(i - 1, GREEN)) + costs[i][BLUE];
            }

        }

        return dp[i][color];
    }
}
