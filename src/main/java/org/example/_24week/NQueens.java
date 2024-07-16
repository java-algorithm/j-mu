package org.example._24week;

public class NQueens {

    private static int[][] map;
    private static int n;
    private static int answer = 0;

    private static final int ROW = 0;
    private static final int COL = 1;

    public static void main(String[] args) {
        int _n = 4;
        final int solution = solution(4);
        System.out.println(solution);
    }

    private static int solution(final int _n) {
        n = _n;
        map = new int[n][n];
        final int[][] selectedPositions = new int[n][2];
        dfs(0, 0, 0, selectedPositions);

        return answer;
    }

    private static void dfs(final int row, final int col, int depth, int[][] selectedPositions) {
        if (depth == n) {
            answer++;
            return;
        }

        for (int i = row; i < n; i++) {
            for (int j = i == row ? col : 0; j < n; j++) {
                if (isInValid(depth,selectedPositions, i, j)) {
                    continue;
                }

                selectedPositions[depth] = new int[]{i, j};
                dfs(i, j + 1, depth + 1, selectedPositions);
                selectedPositions[depth] = null;
            }
        }
    }

    private static boolean isInValid(final int depth, final int[][] selectedPositions, final int newPositionRow, final int newPositionCol) {
        for (int i = 0; i < depth; i++) {
            final int[] selectedPosition = selectedPositions[i];

            // 가로
            if (selectedPosition[COL] == newPositionCol) {
                return true;
            }
            // 세로
            if (selectedPosition[ROW] == newPositionRow) {
                return true;
            }
            // Slash /
            if (selectedPosition[ROW] + selectedPosition[COL] == newPositionRow + newPositionCol) {
                return true;
            }
            // backSlash \
            if (selectedPosition[ROW] - selectedPosition[COL] == newPositionRow - newPositionCol) {
                return true;
            }
        }

        return false;
    }
}
