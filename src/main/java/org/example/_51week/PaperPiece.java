package org.example._51week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PaperPiece {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};
    private static final boolean R = true;
    private static final boolean C = false;

    private static int rowSize;
    private static int colSize;
    private static int mapSize;
    private static int[] map;
    private static boolean[] pieces;


    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        input();

//        printMap();

        backtracking(0);

        System.out.println(answer);
    }

    private static void backtracking(int idx) {
        if (idx == rowSize * colSize) {
            int sum = calculate();
            answer = Math.max(answer, sum);
            return;
        }

        backtracking(idx + 1);
        pieces[idx] = true;
        backtracking(idx + 1);
        pieces[idx] = false;
    }

    private static int calculate() {
        boolean[] visited = new boolean[mapSize];

//        printPieces();
        int sum = 0;
        for (int idx = 0; idx < mapSize; idx++) {
            if (visited[idx]) {
                continue;
            }

            sum += bfs(visited, idx);
        }

        return sum;
    }

    private static int bfs(boolean[] visited, int idx) {
        StringBuilder sum = new StringBuilder();

        Queue<Integer> queue = new LinkedList<>();
        queue.add(idx);
        sum.append(map[idx]);
        visited[idx] = true;

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            int row = poll / colSize;
            int col = poll % colSize;

            for (int j = 0; j < 2; j++) {
                int nextRow = (pieces[row * colSize + col] == R) ? row + dr[2 * j] : row + dr[2 * j + 1];
                int nextCol = (pieces[row * colSize + col] == R) ? col + dc[2 * j] : col + dc[2 * j + 1];

                if (isOut(nextRow, nextCol)) {
                    continue;
                }

                if (visited[nextRow * colSize + nextCol]) {
                    continue;
                }

                if (pieces[nextRow * colSize + nextCol] != pieces[row * colSize + col]) {
                    continue;
                }

                queue.add(nextRow * colSize + nextCol);
                sum.append(map[nextRow * colSize + nextCol]);
                visited[nextRow * colSize + nextCol] = true;
            }

        }

        return Integer.parseInt(sum.toString());
    }

    private static boolean isOut(int nextRow, int nextCol) {
        return nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize;
    }

    private static void printPieces() {
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                System.out.print(pieces[row * colSize + col] ? "R " : "C ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void printMap() {
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                System.out.print(map[row * rowSize + col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        mapSize = rowSize * colSize;

        map = new int[mapSize];
        pieces = new boolean[mapSize];
        for (int row = 0; row < rowSize; row++) {
            String[] input = br.readLine().split("");

            for (int col = 0; col < colSize; col++) {
                map[row * colSize + col] = Integer.parseInt(input[col]);
            }
        }
    }
}
