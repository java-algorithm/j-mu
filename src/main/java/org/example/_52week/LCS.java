package org.example._52week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LCS {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String a = br.readLine();
        String b = br.readLine();

        int[][] dp = new int[a.length() + 1][b.length() + 1];

        int max = 0;
        for (int aIdx = 0; aIdx < a.length(); aIdx++) {
            for (int bIdx = 0; bIdx < b.length(); bIdx++) {
                if (a.charAt(aIdx) == b.charAt(bIdx)) {
                    dp[aIdx + 1][bIdx + 1] = dp[aIdx][bIdx] + 1;
                } else {
                    dp[aIdx + 1][bIdx + 1] = Math.max(dp[aIdx + 1][bIdx], dp[aIdx][bIdx + 1]);
                }

                max = Math.max(max, dp[aIdx + 1][bIdx + 1]);
            }
        }

        System.out.println(max);
    }
}
