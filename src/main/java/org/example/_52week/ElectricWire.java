package org.example._52week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ElectricWire {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[501];
        int[] wires = new int[501];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            wires[a] = b;
        }

        for (int i = 1; i < 501; i++) {

            int max = 0;
            for (int j = 1; j < i; j++) {
                if (wires[i] > wires[j]) {
                    max = Math.max(max, dp[j]);
                }
            }

            if (wires[i] != 0) {
                dp[i] = max + 1;
            }
        }

//        for (int i = 0; i <= 10; i++) {
//            System.out.print(i+" ");
//        }
//        System.out.println();
//
//        for (int i = 0; i <= 10; i++) {
//            System.out.print(dp[i]+" ");
//        }
//        System.out.println();

        int max = 0;
        for (int i = 1; i < 501; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(N - max);
    }

}
