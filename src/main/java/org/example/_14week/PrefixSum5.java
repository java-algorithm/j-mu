package org.example._14week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PrefixSum5 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int operationsCount = Integer.parseInt(st.nextToken());

        final int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        final int[][] dp = new int[N][N];
        dp[0][0] = map[0][0];
        for (int i = 1; i < N; i++) {
            dp[0][i] = dp[0][i - 1] + map[0][i];
            dp[i][0] = dp[i - 1][0] + map[i][0];
        }

        // cal prefix sum
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                dp[i][j] = map[i][j] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
            }
        }

        for (int i = 0; i < operationsCount; i++) {
            st = new StringTokenizer(br.readLine());
            final int x1 = Integer.parseInt(st.nextToken()) - 1;
            final int y1 = Integer.parseInt(st.nextToken()) - 1;
            final int x2 = Integer.parseInt(st.nextToken()) - 1;
            final int y2 = Integer.parseInt(st.nextToken()) - 1;

            int answer = dp[x2][y2];
            if (x1 - 1 >= 0) {
                answer -= dp[x1-1][y2];
            }
            if (y1 - 1 >= 0) {
                answer -= dp[x2][y1 - 1];
            }
            if (x1 - 1 >= 0 && y1 - 1 >= 0) {
                answer += dp[x1 - 1][y1 - 1];
            }

            System.out.println(answer);
        }
    }
//
//    private static void printArray(final int N, final int[][] map) {
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        System.out.println();
//    }
}
