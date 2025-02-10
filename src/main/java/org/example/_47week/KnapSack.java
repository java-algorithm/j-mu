package org.example._47week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KnapSack {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int K;

    private static int[] weights;
    private static int[] values;

    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        input();

        for (int weight = 1; weight <= K; weight++) {
            for (int itemNo = 1; itemNo <= N; itemNo++) {
                if (weights[itemNo] > weight) {
                    dp[itemNo][weight] = dp[itemNo - 1][weight];
                }

                if (weights[itemNo] <= weight) {
                    dp[itemNo][weight] = Math.max(dp[itemNo - 1][weight], values[itemNo] + dp[itemNo - 1][weight - weights[itemNo]]);
                }
            }
        }


        System.out.println(dp[N][K]);
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        weights = new int[N + 1];
        values = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            weights[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 1][K + 1];
    }
}
