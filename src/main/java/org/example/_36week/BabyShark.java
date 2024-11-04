package org.example._36week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BabyShark {

    private static final int EMPTY = 0;
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static final int BABY_SHARK = 9;
    private static int[][] map;
    private static int N;
    private static Shark shark;

    // 위 왼 오 아.
    private static final int[] dr = {-1, 0, 0, 1};
    private static final int[] dc = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        initMap();
//        printMap(0);

        int time = 0;
        while (true) {
            int[] edibleFishPosition = findEdibleFish();

            // 더 이상 먹을 수 있는 물고기가 없는 경우.
            if (edibleFishPosition == null) {
                System.out.println(time);
                return;
            }

            shark.eat(edibleFishPosition);
            time += edibleFishPosition[2];
        }
    }

    public static void printMap(int time) {
        System.out.println("현재 상어 크기: " + shark.size);
        System.out.println("소요 시간: " + time);
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (row == shark.row && col == shark.col) {
                    System.out.print("S ");
                } else {
                    System.out.print(map[row][col] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int[] findEdibleFish() {
        PriorityQueue<int[]> foundFishes = new PriorityQueue<>(fishPriorityComparator());

        boolean[][] visited = new boolean[N][N];

        Queue<int[]> queue = new LinkedList<>();
        visited[shark.row][shark.col] = true;
        queue.add(new int[]{shark.row, shark.col, 0});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            // 위 왼 오 아 순으로 탐색
            for (int i = 0; i < 4; i++) {
                int nextRow = poll[0] + dr[i];
                int nextCol = poll[1] + dc[i];

                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) {
                    continue;
                }

                if (visited[nextRow][nextCol]) {
                    continue;
                }

                int value = map[nextRow][nextCol];
                if(shark.isEdible(value)){
                    foundFishes.add(new int[]{nextRow, nextCol, poll[2] + 1});
                } else if (shark.isMovable(value) && foundFishes.isEmpty()) {
                    visited[nextRow][nextCol] = true;
                    queue.add(new int[]{nextRow, nextCol, poll[2] + 1});
                }
            }
        }

        return foundFishes.isEmpty() ? null : foundFishes.poll();
    }

    private static Comparator<int[]> fishPriorityComparator() {
        return (f1, f2) -> {
            int compareDistance = Integer.compare(f1[2], f2[2]);
            if (compareDistance != 0) {
                return compareDistance;
            }

            int compareRow = Integer.compare(f1[0], f2[0]);
            if (compareRow != 0) {
                return compareRow;
            }

            return Integer.compare(f1[1], f2[1]);
        };
    }

    private static void initMap() throws IOException {
        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                int value = Integer.parseInt(st.nextToken());
                map[row][col] = value;

                if (value == BABY_SHARK) {
                    shark = new Shark(row, col);
                    map[row][col] = 0;
                }
            }
        }
    }


    static class Shark {
        int row;
        int col;
        int size;
        int eatingCount;

        public Shark(int row, int col) {
            this.row = row;
            this.col = col;
            this.size = 2;
            this.eatingCount = 0;
        }

        // {ROW,COL}
        public void eat(int[] edibleFishPosition) {
            map[edibleFishPosition[0]][edibleFishPosition[1]] = 0;
            shark.row = edibleFishPosition[0];
            shark.col = edibleFishPosition[1];

            this.eatingCount++;
            if (size == eatingCount) {
                size++;
                eatingCount = 0;
            }
        }

        public boolean isEdible(int fishSize) {
            if (fishSize == 0) {
                return false;
            }

            return this.size > fishSize;
        }

        public boolean isMovable(int fishSize) {
            return size >= fishSize;
        }
    }
}
