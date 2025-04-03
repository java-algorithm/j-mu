package org.example._52week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LCS2 {

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

                if (max < dp[aIdx + 1][bIdx + 1]) {
                    max = dp[aIdx + 1][bIdx + 1];
                }
            }
        }

        System.out.println(max);
        if (max == 0) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        int row = a.length();
        int col = b.length();
        while (true) {
            if (row <= 0 || col <= 0) {
                break;
            }

            if (dp[row][col] == dp[row - 1][col]) {
                row = row - 1;
                continue;
            }

            if (dp[row][col] == dp[row][col - 1]) {
                col = col - 1;
                continue;
            }

            sb.append(a.charAt(row - 1));
            row = row - 1;
            col = col - 1;
        }

        System.out.println(sb.reverse());

        HashMap<Integer, String> map = new HashMap<>();
    }
}
