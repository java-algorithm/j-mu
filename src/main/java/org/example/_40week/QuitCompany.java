package org.example._40week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class QuitCompany {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] times;
    private static int[] prices;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        times = new int[N + 1];
        prices = new int[N + 1];
        dp = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            prices[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i > 0; i--) {
            if (times[i] + i - 1 > N) {
                dp[i] = dp[i + 1];
                continue;
            }

            dp[i] = Math.max(dp[i + 1], prices[i] + dp[times[i] + i]);
        }

        System.out.println(dp[1]);
    }
}
