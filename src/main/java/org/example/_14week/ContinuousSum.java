package org.example._14week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ContinuousSum {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        final int N = Integer.parseInt(br.readLine());

        final int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        final int[] dp = new int[N];

        dp[0] = arr[0];
        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
        }

        final int answer = Arrays.stream(dp).max().getAsInt();
        System.out.println(answer);
    }
}
