package org.example._14week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Jump {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        final int N = Integer.parseInt(br.readLine());
        final int[][] map = new int[N][N];
        final long[][] dp = new long[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dp[0][0] = 1;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                final int jumpSize = map[row][col];

                if (jumpSize == 0) {
                    continue;
                }

                if (row + jumpSize < N) {
                    dp[row + jumpSize][col] += dp[row][col];
                }

                if (col + jumpSize < N) {
                    dp[row][col + jumpSize] += dp[row][col];
                }
            }
        }

        System.out.println(dp[N - 1][N - 1]);
    }
}
