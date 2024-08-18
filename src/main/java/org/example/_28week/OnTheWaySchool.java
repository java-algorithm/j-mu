package org.example._28week;

public class OnTheWaySchool {

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

                int left;
                int top;

                if (map[row][col] == PUDDLE) {
                    continue;
                }

                if (row <= 0 || row > m || col - 1 <= 0 || col - 1 > n || map[row][col - 1] == PUDDLE) {
                    left = 0;
                } else {
                    left = map[row][col - 1] % 1_000_000_007;
                }

                if (row - 1 <= 0 || row - 1 > m || col <= 0 || col > n || map[row - 1][col] == PUDDLE) {
                    top = 0;
                } else {
                    top = map[row - 1][col] % 1_000_000_007;
                }


                map[row][col] = (left + top) % 1_000_000_007;
            }
        }

        return map[m][n];
    }
}
