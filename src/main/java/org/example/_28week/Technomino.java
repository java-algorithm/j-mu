package org.example._28week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Technomino {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] map;
    private static int rowSize;
    private static int colSize;

    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, 1, -1};

    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];

        for (int i = 0; i < rowSize; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        boolean[][] visited = new boolean[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                visited[row][col] = true;
                dfs(row, col, 1, map[row][col], visited);
                visited[row][col] = false;
            }
        }

        System.out.println(answer);
    }

    private static void dfs(int row, int col, int depth, int sum, boolean[][] visited) {
        if (depth == 4) {
            answer = Math.max(answer, sum);
//            print(visited);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextRow = row + dr[i];
            int nextCol = col + dc[i];

            if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) {
                continue;
            }

            if (visited[nextRow][nextCol]) {
                continue;
            }

            if (depth == 2) {
                visited[nextRow][nextCol] = true;
                dfs(row, col, depth + 1, sum + map[nextRow][nextCol], visited);
                visited[nextRow][nextCol] = false;
            }

            visited[nextRow][nextCol] = true;
            dfs(nextRow, nextCol, depth + 1, sum + map[nextRow][nextCol], visited);
            visited[nextRow][nextCol] = false;
        }

    }

    private static void print(boolean[][] visited) {
        System.out.println("---------graph---------");
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[0].length; j++) {
                System.out.print(visited[i][j] ? "1" : "0");
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}