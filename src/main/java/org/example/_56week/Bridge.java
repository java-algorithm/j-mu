package org.example._56week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bridge {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final int SEA = 0;
    private static final int LAND = 1;

    private static int N;
    private static int[][] map;
    private static int[][] regions;

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        input();

        List<Queue<int[]>> beaches = findBeaches();

        int answer = Integer.MAX_VALUE;
        for (Queue<int[]> beach : beaches) {
            int minimumDistance = markDistanceFromLand(beach);

            answer = Math.min(answer, minimumDistance);
        }

        System.out.println(answer);
    }

    private static int markDistanceFromLand(Queue<int[]> queue) {
        int minimumDistance = Integer.MAX_VALUE;
        boolean[][] visited = new boolean[N][N];

        while (!queue.isEmpty()) {
            int[] cur = queue.poll(); // row, col, region, distance
            int curRow = cur[0];
            int curCol = cur[1];
            int region = cur[2];
            int distance = cur[3];

            for (int i = 0; i < 4; i++) {
                int nextRow = curRow + dr[i];
                int nextCol = curCol + dc[i];

                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) {
                    continue;
                }

                if (visited[nextRow][nextCol]) {
                    continue;
                }

                if (map[nextRow][nextCol] == SEA) {
                    queue.add(new int[]{nextRow, nextCol, region, distance + 1});
                    visited[nextRow][nextCol] = true;
                    continue;
                }

                if (regions[nextRow][nextCol] == region) {
                    continue;
                }

                minimumDistance = Math.min(minimumDistance, distance);
            }
        }

        return minimumDistance;
    }

    private static List<Queue<int[]>> findBeaches() {
        List<Queue<int[]>> beaches = new ArrayList<>();
        regions = new int[N][N];

        int region = 1;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (map[row][col] == SEA) {
                    continue;
                }

                if (regions[row][col] != 0) {
                    continue;
                }


                Queue<int[]> queue = markRegion(row, col, region++, regions);
                beaches.add(queue);
            }
        }

        return beaches;
    }

    private static Queue<int[]> markRegion(int row, int col, int region, int[][] regions) {
        Queue<int[]> beach = new LinkedList<>();
        Queue<int[]> queue = new LinkedList<>();

        boolean[][] visited = new boolean[N][N];
        visited[row][col] = true;
        regions[row][col] = region;

        queue.add(new int[]{row, col});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = cur[0] + dr[i];
                int nextCol = cur[1] + dc[i];

                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) {
                    continue;
                }

                if (visited[nextRow][nextCol]) {
                    continue;
                }

                if (map[nextRow][nextCol] == SEA) {
                    continue;
                }

                regions[nextRow][nextCol] = region;
                visited[nextRow][nextCol] = true;
                queue.add(new int[]{nextRow, nextCol});
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = cur[0] + dr[i];
                int nextCol = cur[1] + dc[i];

                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) {
                    continue;
                }

                if (map[nextRow][nextCol] == SEA) {
                    beach.add(new int[]{cur[0], cur[1], region, 0});
                    break;
                }
            }
        }

        return beach;
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
    }
}
