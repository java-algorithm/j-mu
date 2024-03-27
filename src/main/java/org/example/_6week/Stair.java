package org.example._6week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Stair {

    public static void main(String[] args) throws IOException {
        final int DECRETE = 0;
        final int CONTINUOUS = 1;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] stairs = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        if (N <= 2) {
            if(N==1)
                System.out.println(stairs[1]);
            else {
                int[][] dp = new int[3][2];
                dp[1][DECRETE] = stairs[1];
                dp[2][DECRETE] = stairs[2];
                dp[2][CONTINUOUS] = stairs[1] + stairs[2];

                System.out.println(Math.max(dp[N][DECRETE], dp[N][CONTINUOUS]));
            }

            return;
        }

        // 이전 계단을 밟은경우 Continuous에
        // 이전 계단을 밟지 않은 경우 DECRETE에 저장한다.
        int[][] dp = new int[N + 1][2];
        dp[1][DECRETE] = stairs[1];
        dp[2][DECRETE] = stairs[2];
        dp[2][CONTINUOUS] = stairs[1] + stairs[2];


        for (int i = 3; i <= N; i++) {
            dp[i][DECRETE] = Math.max(dp[i - 2][DECRETE], dp[i - 2][CONTINUOUS]) + stairs[i];
            // 이번에 연속으로 밟으려면 이전칸에서는 연속으로 밟은 상태이면 안됨.
            dp[i][CONTINUOUS] = dp[i - 1][DECRETE] + stairs[i];
        }

        System.out.println(Math.max(dp[N][DECRETE], dp[N][CONTINUOUS]));
    }
}
