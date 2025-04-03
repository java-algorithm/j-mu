package org.example._52week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class LongestParitialNumberArray2 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] dp;
    private static int[] nums;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dp = new int[N];

        dp[0] = nums[0];
        for (int i = 1; i < N; i++) {

            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    max = Math.max(max, dp[j]);
                }
            }

            dp[i] = max + nums[i];
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
