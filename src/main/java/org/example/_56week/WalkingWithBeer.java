package org.example._56week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class WalkingWithBeer {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int testCaseCnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < testCaseCnt; i++) {
            boolean isPossible = test();

            System.out.println(isPossible ? "happy" : "sad");
        }
    }

    private static boolean test() throws IOException {
        int N = Integer.parseInt(br.readLine()) + 2;
        Position[] positions = new Position[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            positions[i] = new Position(x, y);
        }

        int[][] distances = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                distances[i][j] = calculateDistance(positions[i], positions[j]);
            }
        }

        return bfs(distances, positions, N);
    }

    private static boolean bfs(int[][] distances, Position[] positions, int N) {
        boolean[] visited = new boolean[N];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            for (int i = 0; i < N; i++) {
                int distance = distances[poll][i];

                if (visited[i]) {
                    continue;
                }

                if (distance <= 1000) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }

        return visited[N - 1];
    }

    public static int calculateDistance(Position pos1, Position pos2) {
        if (pos1.x == pos2.x && pos1.y == pos2.y) {
            return Integer.MAX_VALUE;
        }

        return Math.abs(pos1.x - pos2.x) + Math.abs(pos1.y - pos2.y);
    }

    private static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
