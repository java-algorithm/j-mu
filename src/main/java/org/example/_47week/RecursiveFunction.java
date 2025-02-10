package org.example._47week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RecursiveFunction {

    private static final int UNDEFINED = -1;
    private static int[][][] dp = new int[21][21][21];

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        for (int a = 0; a < 21; a++) {
            for (int b = 0; b < 21; b++) {
                for (int c = 0; c < 21; c++) {
                    if (a <= 0 || b <= 0 || c <= 0) {
                        dp[a][b][c] = 1;
                    }else{
                        dp[a][b][c] = UNDEFINED;
                    }
                }
            }
        }

        for (int a = 0; a < 21; a++) {
            for (int b = 0; b < 21; b++) {
                for (int c = 0; c < 21; c++) {
                    w(a, b, c);
                }
            }
        }

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1 && c == -1) {
                return;
            }

            System.out.printf("w(%d, %d, %d) = %d\n", a, b, c, w(a, b, c));
        }
    }

    private static int w(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return 1;
        }

        if (a > 20 || b > 20 || c > 20) {
            return w(20, 20, 20);
        }

        if (dp[a][b][c] != UNDEFINED) {
            return dp[a][b][c];
        }

        if (a < b && b < c) {
            return dp[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
        }

        return dp[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
    }
}
