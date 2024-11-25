package org.example._39week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class FireStorm {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int Q;
    private static int[] step;
    private static int[][] arr;
    private static int arrSize;
    private static int tempSize = 0;
    private static int biggestIceSize = 0;
    private static final int[] dr = {1, 0, -1, 0};
    private static final int[] dc = {0, 1, 0, -1};
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        init();
        for (int i = 0; i < Q; i++) {
            rotateArray(step[i]);
            meltIce();
        }

        printSumOfIces();
        printBiggestIceSize();
    }

    private static void printArray(String message) {
        System.out.println(message);
        System.out.println("-------------------------");
        for (int row = 0; row < arrSize; row++) {
            for (int col = 0; col < arrSize; col++) {
                System.out.print(arr[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        arrSize = (int) Math.pow(2, N);
        arr = new int[arrSize][arrSize];
        visited = new boolean[arrSize][arrSize];

        Q = Integer.parseInt(st.nextToken());
        step = new int[Q];

        for (int i = 0; i < arrSize; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        step = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private static void rotateArray(int step) {
        if (step == 0) {
            return;
        }

        int[][] temp = new int[arrSize][arrSize];
        int divisionSize = (int) Math.pow(2, step);

        for (int row = 0; row < arrSize; row += divisionSize) {
            for (int col = 0; col < arrSize; col += divisionSize) {
                rotateArray(row, col, divisionSize, temp);
            }
        }

        arr = temp;
    }

    private static void rotateArray(int row, int col, int size, int[][] temp) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temp[row + i][col + j] = arr[row + size - 1 - j][col + i];
            }
        }
    }

    private static void meltIce() {
        int[][] temp = new int[arrSize][arrSize];

        for (int row = 0; row < arrSize; row++) {
            for (int col = 0; col < arrSize; col++) {
                if (arr[row][col] == 0) {
                    continue;
                }

                int iceCount = 0;
                for (int i = 0; i < 4; i++) {
                    int adjacentRow = row + dr[i];
                    int adjacentCol = col + dc[i];

                    if (isOut(adjacentRow, adjacentCol)) {
                        continue;
                    }

                    if (arr[adjacentRow][adjacentCol] == 0) {
                        continue;
                    }

                    iceCount++;
                }

                if (iceCount < 3) {
                    temp[row][col] = arr[row][col] - 1;
                } else {
                    temp[row][col] = arr[row][col];
                }
            }
        }

        arr = temp;
    }

    private static void printSumOfIces() {
        int sum = 0;
        for (int row = 0; row < arrSize; row++) {
            for (int col = 0; col < arrSize; col++) {
                sum += arr[row][col];
            }
        }

        System.out.println(sum);
    }

    private static void printBiggestIceSize() {
        for (int row = 0; row < arrSize; row++) {
            for (int col = 0; col < arrSize; col++) {
                if (!visited[row][col] && arr[row][col] != 0) {
                    bfs(row, col);
                }
            }
        }

        System.out.println(biggestIceSize);
    }

    private static void bfs(int row, int col) {
        tempSize = 1;
        visited[row][col] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col, tempSize});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            biggestIceSize = Math.max(biggestIceSize, poll[2]);

            for (int i = 0; i < 4; i++) {
                int nextRow = poll[0] + dr[i];
                int nextCol = poll[1] + dc[i];

                if (isOut(nextRow, nextCol)) {
                    continue;
                }

                if (visited[nextRow][nextCol]) {
                    continue;
                }

                if (arr[nextRow][nextCol] == 0) {
                    continue;
                }

                visited[nextRow][nextCol] = true;
                queue.add(new int[]{nextRow, nextCol, ++tempSize});
            }
        }
    }

    private static boolean isOut(int nextRow, int nextCol) {
        return nextRow < 0 || nextRow >= arrSize || nextCol < 0 || nextCol >= arrSize;
    }
}
