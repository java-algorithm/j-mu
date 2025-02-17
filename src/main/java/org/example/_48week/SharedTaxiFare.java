package org.example._48week;

public class SharedTaxiFare {

    private static final int INF = 100_000_000; // 1억

    public static void main(String[] args) {
//        int n = 6;
//        int s = 4;
//        int a = 6;
//        int b = 2;
//        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};

        int n = 7;
        int s = 3;
        int a = 4;
        int b = 1;
        int[][] fares = {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}};

        int solution = solution(n, s, a, b, fares);
        System.out.println(solution);
    }

    // n: node개수
    // s: 시작 지점
    // a: A의 도착 지점
    // b: B의 도착 지점
    // fares: 지점 사이의 예상 택시 요금
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        // make dp
        int[][] dp = initMap(n, fares);

        // floyd warshall O(N^3)
        for (int k = 1; k < n + 1; k++) {
            for (int row = 1; row < n + 1; row++) {
                for (int col = 1; col < n + 1; col++) {
                    dp[row][col] = Math.min(dp[row][col], dp[row][k] + dp[k][col]);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 1; i < n + 1; i++) {
            answer = Math.min(answer, dp[s][i] + dp[i][a] + dp[i][b]);
        }

        return answer;
    }

    private static int[][] initMap(int n, int[][] fares) {
        int[][] dp = new int[n + 1][n + 1];

        for (int row = 1; row < n + 1; row++) {
            for (int col = 1; col < n + 1; col++) {
                if (row == col) {
                    dp[row][col] = 0;
                } else {
                    dp[row][col] = INF;
                }
            }
        }

        for (int[] fare : fares) {
            int node1 = fare[0];
            int node2 = fare[1];
            int cost = fare[2];

            dp[node1][node2] = cost;
            dp[node2][node1] = cost;
        }

        return dp;
    }
}
