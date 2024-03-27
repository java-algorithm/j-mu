package org.example._6week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LeaveFromJob {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N + 1];
        int[] P = new int[N + 1];
        // 마지막날엔 이전날이 없으므로 0으로 초기화시켜놓기위함.
        int[] dp = new int[N + 2];

        for (int i = 1; i < N + 1; i++) {
            int[] TP = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            T[i] = TP[0];
            P[i] = TP[1];
        }

        int maxValue = 0;
        for (int i = N; i > 0; i--) {
            // 상담을 퇴사일 전까지 끝낼 수 없는 경우.
            if (i + T[i] - 1 > N) {
                continue;
            }

            // 상담이 끝나는 다음날
            int prev = dp[i + T[i]];

            // prev+P[i] = 오늘 상담을 하는 경우, dp[i-1] = 오늘 상담을 안하는 경우
            dp[i] = Math.max(prev + P[i], dp[i + 1]);
            maxValue = Math.max(maxValue, dp[i]);
        }

        System.out.println(maxValue);
    }
}
