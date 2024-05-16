package org.example._14week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RGBDistance {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;

    public static void main(String[] args) throws IOException {
        final int N = Integer.parseInt(br.readLine());

        final int[][] map = new int[N][3];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        final int[][] dp = new int[N][3];
        dp[0] = map[0].clone();
        for (int i = 1; i < N; i++) {
            dp[i][RED] = Math.min(dp[i - 1][GREEN], dp[i - 1][BLUE]) + map[i][RED];
            dp[i][GREEN] = Math.min(dp[i - 1][RED], dp[i - 1][BLUE]) + map[i][GREEN];
            dp[i][BLUE] = Math.min(dp[i - 1][RED], dp[i - 1][GREEN]) + map[i][BLUE];
        }

        final int answer = Arrays.stream(dp[N - 1]).min().getAsInt();
        System.out.println(answer);
    }
}
