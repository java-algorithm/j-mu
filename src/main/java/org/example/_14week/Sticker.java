package org.example._14week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sticker {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        final int testCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCase; i++) {
            final int N = Integer.parseInt(br.readLine());

            final int[][] dp = new int[2][N];
            final int[][] map = new int[2][N];
            map[0] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            map[1] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            dp[0][0] = map[0][0];
            dp[1][0] = map[1][0];
            for (int j = 1; j < N; j++) {
                dp[0][j] = Math.max(dp[0][j - 1], dp[1][j - 1] + map[0][j]);
                dp[1][j] = Math.max(dp[1][j - 1], dp[0][j - 1] + map[1][j]);
            }

            final int answer = Math.max(dp[0][N - 1], dp[1][N - 1]);
            System.out.println(answer);
        }
    }
}
