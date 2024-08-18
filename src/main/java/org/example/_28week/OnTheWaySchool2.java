package org.example._28week;

public class OnTheWaySchool2 {

    private static final int PUDDLE = -1;

    public static void main(String[] args) {

        int m = 4;
        int n = 3;
        int[][] puddles = {{2, 2}};

        int result = solution(m, n, puddles);
        System.out.println(result);
    }

    public static int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[m + 1][n + 1];

        for (int[] puddle : puddles) {
            int row = puddle[0];
            int col = puddle[1];

            map[row][col] = PUDDLE;
        }

        map[1][1] = 1;

        for (int row = 1; row <= m; row++) {
            for (int col = 1; col <= n; col++) {
                if (row == 1 && col == 1) {
                    continue;
                }

                if (map[row][col] == PUDDLE) {
                    continue;
                }

                int left = map[row][col - 1] == PUDDLE ? 0 : map[row][col - 1] % 1_000_000_007;
                int top = map[row - 1][col] == PUDDLE ? 0 : map[row - 1][col] % 1_000_000_007;

                map[row][col] = (left + top) % 1_000_000_007;
            }
        }

        return map[m][n];
    }
}
