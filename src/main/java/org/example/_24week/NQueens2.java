package org.example._24week;

public class NQueens2 {

    private static int n;
    private static int answer = 0;
    private static int wrong = 0;

    private static boolean[] visitedCol;
    private static boolean[] visitedBackSlash = new boolean[23];
    private static boolean[] visitedSlash = new boolean[23];

    public static void main(String[] args) {
        final long startTime = System.nanoTime();
        final int solution = solution(12);
        final long endTime = System.nanoTime();

        System.out.println(endTime - startTime);

        System.out.println(solution);
        System.out.println(wrong);
    }

    private static int solution(final int _n) {
        n = _n;
        visitedCol = new boolean[n];
        final int[][] selectedPositions = new int[n][2];
        dfs(0, selectedPositions);

        return answer;
    }

    private static void dfs(final int row, int[][] selectedPositions) {
        if (row == n) {
            answer++;
            return;
        }

        for (int newCol = 0; newCol < n; newCol++) {
            final int slash = row + newCol;
            final int backSlash = row - newCol >= 0 ? row - newCol : newCol - row + 11;

            if (isInValid(newCol, slash, backSlash)) {
                continue;
            }

            visitedCol[newCol] = true;
            visitedSlash[slash] = true;
            visitedBackSlash[backSlash] = true;

            selectedPositions[row] = new int[]{row, newCol};
            dfs(row + 1, selectedPositions);

            visitedCol[newCol] = false;
            visitedSlash[slash] = false;
            visitedBackSlash[backSlash] = false;
        }
    }

    private static boolean isInValid(final int newPositionCol, final int slash, final int backSlash) {
        if (
            visitedCol[newPositionCol] ||
                visitedSlash[slash] ||
                visitedBackSlash[backSlash]
        ) {
            return true;
        }

        return false;
    }
}
