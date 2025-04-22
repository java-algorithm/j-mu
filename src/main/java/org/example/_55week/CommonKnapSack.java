package org.example._55week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CommonKnapSack {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final int WEIGHT = 0;
    private static final int VALUE = 1;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] values = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            values[i][WEIGHT] = weight;
            values[i][VALUE] = value;
        }

        int[][] dp = new int[K + 1][N + 1];
        for (int item = 1; item <= N; item++) {
            int curWeight = values[item][WEIGHT];
            int curValue = values[item][VALUE];

            for (int weight = 1; weight <= K; weight++) {
                if (curWeight > weight) {
                    dp[weight][item] = dp[weight][item - 1];
                    continue;
                }

                dp[weight][item] = Math.max(dp[weight][item - 1], dp[weight - curWeight][item - 1] + curValue);
            }
        }

        System.out.println(dp[K][N]);
    }
}
