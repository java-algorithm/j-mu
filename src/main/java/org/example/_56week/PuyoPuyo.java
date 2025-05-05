package org.example._56week;

import javax.management.Notification;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PuyoPuyo {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static final int rowSize = 12;
    public static final int colSize = 6;

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    private static char[][] map = new char[rowSize][colSize];

    public static void main(String[] args) throws IOException {
        input();

        int answer = 0;
        while (true) {
//            printMap();
            boolean boom = boom();
            if (!boom) {
                break;
            }

            answer++;
            fallDown();
        }

        System.out.println(answer);
    }

    private static void printMap() {
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                System.out.print(map[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean boom() {
        char[][] newMap = new char[rowSize][colSize];
        boolean[][] visited = new boolean[rowSize][colSize];

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                newMap[row][col] = map[row][col];
            }
        }

        boolean boom = false;
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (map[row][col] == '.') {
                    continue;
                }

                if (visited[row][col]) {
                    continue;
                }

                boolean isBoom = boom(row, col, newMap, visited);
                if (isBoom) {
                    boom = true;
                }
            }
        }

        map = newMap;

        return boom;
    }

    private static boolean boom(int row, int col, char[][] newMap, boolean[][] visited) {
        List<int[]> positions = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();

        visited[row][col] = true;
        queue.add(new int[]{row, col});
        positions.add(new int[]{row, col});

        int cnt = 0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            cnt++;

            for (int i = 0; i < 4; i++) {
                int nextRow = poll[0] + dr[i];
                int nextCol = poll[1] + dc[i];

                if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) {
                    continue;
                }

                if (visited[nextRow][nextCol]) {
                    continue;
                }

                if (map[row][col] == map[nextRow][nextCol]) {
                    int[] nextPosition = {nextRow, nextCol};
                    queue.add(nextPosition);
                    visited[nextRow][nextCol] = true;
                    positions.add(nextPosition);
                }
            }
        }

        if (cnt >= 4) {
            for (int[] position : positions) {
                newMap[position[0]][position[1]] = '.';
            }

            return true;
        }

        return false;
    }

    private static void fallDown() {
        char[][] newMap = new char[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                newMap[row][col] = '.';
            }
        }

        for (int col = 0; col < colSize; col++) {
            int pivot = rowSize-1;
            for (int row = rowSize - 1; row >= 0; row--) {
                if (map[row][col] == '.') {
                    continue;
                }

                newMap[pivot--][col] = map[row][col];
            }
        }

        map = newMap;
    }

    private static void input() throws IOException {
        for (int row = 0; row < rowSize; row++) {
            map[row] = br.readLine().toCharArray();
        }
    }
}
