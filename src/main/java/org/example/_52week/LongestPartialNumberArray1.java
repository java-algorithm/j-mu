package org.example._52week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LongestPartialNumberArray1 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] nums;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dp = new int[N];

        dp[0] = 1;
        for (int i = 1; i < N; i++) {

            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    max = Math.max(max, dp[j]);
                }
            }

            dp[i] = max + 1;
        }

        print(dp);

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }

    private static void print(int[] dp) {
        for (int i = 0; i < dp.length; i++) {
            System.out.print(dp[i] + " ");
        }
        System.out.println();
    }
}
