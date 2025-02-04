package org.example._46week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SteppingStones {
    private static final int EMPTY = 0;
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] steps;
    private static int answer = Integer.MIN_VALUE;
    private static int[] dp;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        steps = new int[N + 1];
        dp = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            steps[i] = Integer.parseInt(st.nextToken());
        }
        steps[N] = Integer.MAX_VALUE;
        dp[N] = -1;

        for (int i = N - 1; i >= 0; i--) {
            dfs(i, 1);
        }

        System.out.println(Arrays.stream(dp).max().getAsInt());
    }

    private static int dfs(int index, int depth) {
        dp[index] = Math.max(dp[index], depth);

        int currentValue = steps[index];
        for (int i = index + 1; i < N; i++) {
            if (steps[i] > currentValue) {
                if (dp[i] != EMPTY) {
                    dp[index] = Math.max(dp[index], dp[i] + depth);
                } else {
                    dp[index] = Math.max(dp[index], dfs(i, depth + 1) + depth);
                }
            }
        }

        return dp[index];
    }
}
