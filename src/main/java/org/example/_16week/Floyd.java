package org.example._16week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Floyd {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        final int citiesCount = Integer.parseInt(br.readLine());
        final int busesCount = Integer.parseInt(br.readLine());

        final int[][] costs = new int[citiesCount + 1][citiesCount + 1];

        for (int i = 0; i < busesCount; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            final int from = Integer.parseInt(st.nextToken());
            final int to = Integer.parseInt(st.nextToken());
            final int cost = Integer.parseInt(st.nextToken());

            if (costs[from][to] == 0) {
                costs[from][to] = cost;
            } else if (costs[from][to] != 0 && costs[from][to] > cost) {
                costs[from][to] = cost;
            }
        }

        for (int i = 1; i <= citiesCount; i++) {
            for (int j = 1; j <= citiesCount; j++) {
                for (int k = 1; k <= citiesCount; k++) {
                    if (j == k) {
                        continue;
                    }

                    if (costs[j][i] == 0 || costs[i][k] == 0) {
                        continue;
                    }

                    if (costs[j][k] == 0 || costs[j][k] > costs[j][i] + costs[i][k]) {
                        costs[j][k] = costs[j][i] + costs[i][k];
                    }
                }
            }
        }

        for (int i = 1; i <= citiesCount; i++) {
            for (int j = 1; j <= citiesCount; j++) {
                System.out.print(costs[i][j]);
                if (j != citiesCount) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
