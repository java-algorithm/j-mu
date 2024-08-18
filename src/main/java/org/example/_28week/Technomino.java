package org.example._28week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Technomino {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int rowSize;
    private static int colSize;

    private static int[][] map;
    private static int max = Integer.MIN_VALUE;
    private static List<int[]> visitPoints;
    private static int[] dr = {1, -1, 0, 0};
    private static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        // map 초기화
        map = new int[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

//
//        for (int row = 0; row < rowSize; row++) {
//            for (int col = 0; col < colSize; col++) {
//                bfs(row, col);
//            }
//        }

        System.out.println(max);
        System.out.println(visitPoints);

    }

    private static void dfs(int row, int col, int depth, int sum, boolean[][] visited) {
        if (depth == 4) {
            max = Math.max(max, sum);
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

            visited[nextRow][nextCol] = true;
            dfs(nextRow, nextCol, depth + 1, sum + map[nextRow][nextCol], visited);
            visited[nextRow][nextCol] = false;
        }
    }
}
