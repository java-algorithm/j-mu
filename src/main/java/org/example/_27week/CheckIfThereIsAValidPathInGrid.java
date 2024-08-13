package org.example._27week;

public class CheckIfThereIsAValidPathInGrid {

    // 왼 오 남 북
    private static final int[] dr = {0, 0, 1, -1};
    private static final int[] dc = {-1, 1, 0, 0};
    private static final int[][] streets = {{}, {0, 1}, {2, 3}, {0, 2}, {1, 2}, {0, 3}, {1, 3}};

    private static final int ROW = 0;
    private static final int COL = 1;
    private static boolean[][] visited;
    private static int gridRowSize;
    private static int gridColSize;
    private static boolean hasPath;

    public static void main(String[] args) {
//        int[][] grid = {{2, 4, 3}, {6, 5, 2}};
//        int[][] grid = {{1, 2, 1}, {1, 2, 1}};
        int[][] grid = {{4, 1}, {6, 1}};

        hasValidPath(grid);
        System.out.println(hasPath);
    }

    public static boolean hasValidPath(int[][] grid) {
        hasPath = false;
        int[] point = {0, 0};
        gridRowSize = grid.length;
        gridColSize = grid[0].length;

        visited = new boolean[gridRowSize][gridColSize];
        visited[0][0] = true;

        dfs(point, grid, gridRowSize - 1, gridColSize - 1);

        return hasPath;
    }

    private static void dfs(int[] point, int[][] grid, int targetRow, int targetCol) {
        System.out.println(point[0] + "," + point[1]);
        if (point[ROW] == targetRow && point[COL] == targetCol) {
            hasPath = true;
            return;
        }

        int streetNo = grid[point[ROW]][point[COL]];

        int[] street = streets[streetNo];
        for (int block : street) {
            int nextRow = point[ROW] + dr[block];
            int nextCol = point[COL] + dc[block];

            if (nextRow < 0 || nextRow >= gridRowSize || nextCol < 0 || nextCol >= gridColSize) {
                continue;
            }

            if (visited[nextRow][nextCol]) {
                continue;
            }

            if (!hasConnection(point, grid, nextRow, nextCol)) {
                continue;
            }

            visited[nextRow][nextCol] = true;
            dfs(new int[]{nextRow, nextCol}, grid, targetRow, targetCol);
        }
    }

    private static boolean hasConnection(int[] point, int[][] grid, int nextRow, int nextCol) {
        int nextStreetNo = grid[nextRow][nextCol];
        int[] nextStreet = streets[nextStreetNo];
        for (int nextBlock : nextStreet) {
            int row = nextRow + dr[nextBlock];
            int col = nextCol + dc[nextBlock];

            if (row == point[ROW] && col == point[COL]) {
                return true;
            }
        }

        return false;
    }
}
