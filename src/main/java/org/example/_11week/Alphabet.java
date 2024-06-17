package org.example._11week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Alphabet {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static boolean[][] visited;
    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, -1, 0, 1};

    private static class Position {
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static class Process {
        Position position;
        String process;

        public Process(Position position, String process) {
            this.position = position;
            this.process = process;
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        String[][] graph = new String[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String[] split = br.readLine().split("");
            graph[i] = split;
        }

        Queue<Process> queue = new LinkedList<>();
        Process process = new Process(new Position(0, 0), graph[0][0]);
        queue.offer(process);
        visited[0][0] = true;

        int maxLength = 1;
        while (!queue.isEmpty()) {
            Process poll = queue.poll();

            for (int i = 0; i < dr.length; i++) {
                Position position = poll.position;
                String curProcess = poll.process;

                int newRow = position.row + dr[i];
                int newCol = position.col + dc[i];

                if (newRow < 0 || newRow >= R || newCol < 0 || newCol >= C) {
                    continue;
                }

                if (visited[newRow][newCol]) {
                    continue;
                }

                if (curProcess.contains(graph[newRow][newCol])) {
                    continue;
                }

                if (maxLength < curProcess.length() + 1) {
                    maxLength = curProcess.length() + 1;
                }

                visited[newRow][newCol] = true;
                Process newProcess = new Process(new Position(newRow, newCol), curProcess + graph[newRow][newCol]);
                queue.offer(newProcess);
            }
        }

        System.out.println(maxLength);
    }
}
