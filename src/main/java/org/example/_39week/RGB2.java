package org.example._39week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RGB2 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;

    private static int[][][] dp;
    private static int[][] costs;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        final int N = Integer.parseInt(br.readLine());
        dp = new int[3][N][N];
        costs = new int[N][N];

        for (int i = 0; i < N; i++) {
            costs[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        Arrays.fill(dp[RED][0], costs[0][RED]);
        Arrays.fill(dp[GREEN][0], costs[0][GREEN]);
        Arrays.fill(dp[BLUE][0], costs[0][BLUE]);

        for (int color = 0; color < 3; color++) {
            dp[color][1][RED] = Math.min(dp[color][0][BLUE], dp[color][0][GREEN]) + costs[1][RED];
            dp[color][1][GREEN] = Math.min(dp[color][0][RED], dp[color][0][BLUE]) + costs[1][GREEN];
            dp[color][1][BLUE] = Math.min(dp[color][0][RED], dp[color][0][GREEN]) + costs[1][BLUE];
            dp[color][1][color] = Integer.MAX_VALUE;

            for (int i = 2; i < N; i++) {
                dp[color][i][RED] = Math.min(dp[color][i - 1][BLUE], dp[color][i - 1][GREEN]) + costs[i][RED];
                dp[color][i][GREEN] = Math.min(dp[color][i - 1][RED], dp[color][i - 1][BLUE]) + costs[i][GREEN];
                dp[color][i][BLUE] = Math.min(dp[color][i - 1][RED], dp[color][i - 1][GREEN]) + costs[i][BLUE];
            }
        }

        for (int zeroColor = 0; zeroColor < 3; zeroColor++) {
            for (int color = 0; color < 3; color++) {
                if(zeroColor != color){
                    answer = Math.min(dp[zeroColor][N - 1][color], answer);
                }
            }
        }

        System.out.println(answer);
    }
}
