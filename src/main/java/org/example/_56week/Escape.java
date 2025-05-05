package org.example._56week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Escape {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int rowSize;
    private static int colSize;

    private static int[] initPosition;
    private static int[] beaverHouse;

    private static char[][] map;
    private static boolean[][] visited;

    private static final char beaver = 'D';
    private static final char stone = 'X';
    private static final char water = '*';
    private static final char empty = '.';
    private static final char hedgehog = 'S';

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    private static final String FAIL = "KAKTUS";
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        input();


        visited = new boolean[rowSize][colSize];
        visited[initPosition[0]][initPosition[1]] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{initPosition[0], initPosition[1], 0});

        while (!queue.isEmpty()) {
            flood();

            queue = move(queue);
        }


        System.out.println(answer == Integer.MAX_VALUE ? FAIL : answer);
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

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        map = new char[rowSize][colSize];

        for (int row = 0; row < rowSize; row++) {
            String input = br.readLine();

            for (int col = 0; col < colSize; col++) {
                map[row][col] = input.charAt(col);

                if (map[row][col] == beaver) {
                    beaverHouse = new int[]{row, col};
                }

                if (map[row][col] == hedgehog) {
                    initPosition = new int[]{row, col};
                }
            }
        }


    }

    private static void flood() {
        char[][] newMap = new char[rowSize][colSize];

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                newMap[row][col] = empty;
            }
        }

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (map[row][col] == beaver) {
                    newMap[row][col] = beaver;
                    continue;
                }

                if (map[row][col] == stone) {
                    newMap[row][col] = stone;
                    continue;
                }

                if (map[row][col] == water) {
                    // 비버처리.
                    newMap[row][col] = water;

                    for (int i = 0; i < 4; i++) {
                        int nextRow = row + dr[i];
                        int nextCol = col + dc[i];

                        if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) {
                            continue;
                        }

                        if (newMap[nextRow][nextCol] == stone) {
                            continue;
                        }

                        if (beaverHouse[0] == nextRow && beaverHouse[1] == nextCol) {
                            continue;
                        }

                        newMap[nextRow][nextCol] = water;
                    }
                }
            }
        }

        map = newMap;
    }

    private static Queue<int[]> move(Queue<int[]> queue) {
        Queue<int[]> currentStep = queue;
        Queue<int[]> nextStep = new LinkedList<>();

        while (!currentStep.isEmpty()) {
            int[] poll = currentStep.poll();

            if (poll[0] == beaverHouse[0] && poll[1] == beaverHouse[1]) {
                answer = Math.min(answer, poll[2]);
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = poll[0] + dr[i];
                int nextCol = poll[1] + dc[i];

                if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) {
                    continue;
                }

                if (map[nextRow][nextCol] == stone) {
                    continue;
                }

                if (map[nextRow][nextCol] == water) {
                    continue;
                }

                if (visited[nextRow][nextCol]) {
                    continue;
                }

                visited[nextRow][nextCol] = true;
                nextStep.add(new int[]{nextRow, nextCol, poll[2] + 1});
            }
        }

        return nextStep;
    }
}
