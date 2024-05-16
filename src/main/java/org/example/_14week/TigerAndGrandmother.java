package org.example._14week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TigerAndGrandmother {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static final int IMPOSSIBLE = 0;

    public static void main(String[] args) throws IOException {
        final StringTokenizer st = new StringTokenizer(br.readLine());
        final int lastDay = Integer.parseInt(st.nextToken());
        final int lastCount = Integer.parseInt(st.nextToken());

        int[][] dp = new int[lastDay][(lastCount / 2) + 1];
        for (int i = 1; i < (lastCount / 2) + 1; i++) {
            dp[lastDay - 1][i] = lastCount - i;
            dp[lastDay - 2][i] = i;
        }

        for (int i = lastDay - 3; i > 0; i--) {
            for (int j = 1; j < (lastCount / 2) + 1; j++) {
                if (dp[i + 1][j] == IMPOSSIBLE) {
                    continue;
                }

                final int value = dp[i + 2][j] - dp[i + 1][j];
                if (value > dp[i + 1][j] || value < 0) {
                    continue;
                }

                dp[i][j] = value;
            }
        }

        for (int i = 1; i < (lastCount / 2) + 1 ; i++) {
            if (dp[1][i] != IMPOSSIBLE) {
                System.out.println(dp[1][i]);
                System.out.println(dp[2][i]);
                break;
            }
        }
    }
}
