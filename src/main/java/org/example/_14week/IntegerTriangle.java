package org.example._14week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.StringTokenizer;

public class IntegerTriangle {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][] triangle = new int[N][N];
        int[][] dp = new int[N][N];


        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j <= i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = triangle[0][0];
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j <= i; j++) {
                int curValue = dp[i + 1][j];
                if (curValue < dp[i][j] + triangle[i + 1][j]) {
                    dp[i + 1][j] = dp[i][j] + triangle[i + 1][j];
                }

                curValue = dp[i + 1][j + 1];
                if (curValue < dp[i][j] + triangle[i + 1][j + 1]) {
                    dp[i + 1][j + 1] = dp[i][j] + triangle[i + 1][j + 1];
                }
            }
        }

        final int max = Arrays.stream(dp[N - 1]).max().getAsInt();

        System.out.println(max);
    }
}
