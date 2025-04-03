package org.example._52week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ContiniousSum {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] nums;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dp = new int[2][N];

        // 0: 제거 아무것도 안한거
        // 1: 제거 한개 한상태
        dp[0][0] = nums[0];
        dp[1][0] = 0;

        int max = nums[0];
        for (int i = 1; i < N; i++) {
            dp[0][i] = Math.max(0, dp[0][i - 1]) + nums[i];
            dp[1][i] = Math.max(dp[0][i - 1] + 0, dp[1][i - 1] + nums[i]);

            max = Math.max(max, dp[0][i]);
            max = Math.max(max, dp[1][i]);
        }

        printDp(dp);

        System.out.println(max);
    }

    private static void printDp(int[][] dp) {
        System.out.println("dp");
        for (int i = 0; i < dp[0].length; i++) {
            System.out.print(dp[0][i] + " ");
        }
        System.out.println();
        for (int i = 0; i < dp[0].length; i++) {
            System.out.print(dp[1][i] + " ");
        }

        System.out.println();
    }
}
