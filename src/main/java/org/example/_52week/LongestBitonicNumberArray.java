package org.example._52week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LongestBitonicNumberArray {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] nums;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dp = new int[2][N];

        for (int i = 0; i < N; i++) {

            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    max = Math.max(max, dp[0][j]);
                }
            }

            dp[0][i] = max + 1;
        }

        for (int i = 0; i < N; i++) {

            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[i]) {
                    max = Math.max(max, dp[0][j]);
                    max = Math.max(max, dp[1][j]);
                }
            }

            dp[1][i] = max + 1;
        }

        int max = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, dp[i][j]);
            }
        }

        System.out.println(max);
    }
}
