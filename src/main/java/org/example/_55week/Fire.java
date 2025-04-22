package org.example._55week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Fire {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    private static final char WALL = '#';
    private static final char EMPTY = '.';
    private static final char JIHOON = 'J';
    private static final char FIRE = 'F';

    private static int rowSize;
    private static int colSize;
    private static char[][] map;
    private static boolean[][] visited;
    private static int[][] fires;

    private static char[][] copyMap;

    public static void main(String[] args) throws IOException {
        Queue<int[]> jihoonQueue = new LinkedList<>();
        Queue<int[]> firesQueue = new LinkedList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        map = new char[rowSize][colSize];
        visited = new boolean[rowSize][colSize];
        fires = new int[rowSize][colSize];

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                fires[row][col] = -1;
            }
        }

        for (int row = 0; row < rowSize; row++) {
            String input = br.readLine();
            for (int col = 0; col < colSize; col++) {
                map[row][col] = input.charAt(col);

                if (map[row][col] == JIHOON) {
                    jihoonQueue.add(new int[]{row, col, 0});
                    visited[row][col] = true;
                }

                if (map[row][col] == FIRE) {
                    firesQueue.add(new int[]{row, col, 0});
                    fires[row][col] = 0;
                }
            }
        }

        flagFires(firesQueue);

//        printFire();

        int time = bfs(jihoonQueue);

        System.out.println(time == -1 ? "IMPOSSIBLE" : time);
    }

    private static int bfs(Queue<int[]> queue) {
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = poll[0] + dr[i];
                int nextCol = poll[1] + dc[i];

                if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) {
                    return poll[2] + 1;
                }

                if (map[nextRow][nextCol] == WALL) {
                    continue;
                }

                if (visited[nextRow][nextCol]) {
                    continue;
                }

                if (fires[nextRow][nextCol] != -1 && fires[nextRow][nextCol] <= poll[2] + 1) {
                    continue;
                }

                visited[nextRow][nextCol] = true;
                queue.add(new int[]{nextRow, nextCol, poll[2] + 1});
            }
        }

        return -1;
    }

    private static void printFire() {
        System.out.println("--------fires---------");
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                System.out.print(fires[row][col] == -1 ? "_ " : fires[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void flagFires(Queue<int[]> firesQueue) {
        while (!firesQueue.isEmpty()) {
            int[] poll = firesQueue.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = poll[0] + dr[i];
                int nextCol = poll[1] + dc[i];

                if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) {
                    continue;
                }

                if (map[nextRow][nextCol] == WALL) {
                    continue;
                }

                if (fires[nextRow][nextCol] != -1) {
                    continue;
                }

                fires[nextRow][nextCol] = poll[2] + 1;
                firesQueue.add(new int[]{nextRow, nextCol, poll[2] + 1});
            }
        }

    }
}
