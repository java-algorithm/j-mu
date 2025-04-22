package org.example._55week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PopulationMovement {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    private static int N;
    private static int min;
    private static int max;

    private static int[][] map;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        min = Integer.parseInt(st.nextToken());
        max = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int day = 0; ; day++) {
            // 인구 이동이 발생할건지 확인.

            int[][] regions = new int[N][N];
            boolean[][] visited = new boolean[N][N];
            int[][] newMap = new int[N][N];
            Map<Integer, Population> mapSize = new HashMap<>();

            // 국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루 동안 연다.
            // 위의 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.
            // 국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.
            int region = 1;
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (!visited[row][col]) {
                        flag(regions, visited, mapSize, row, col, region);
                        region++;
                    }
                }
            }

            boolean move = false;
            //연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    Population population = mapSize.get(regions[row][col]);
                    int newPopulation = population.getNewPopulation();
                    if (map[row][col] != newPopulation) {
                        move = true;
                    }

                    newMap[row][col] = newPopulation;
                }
            }

            if (!move) {
                System.out.println(day);
                return;
            }

            map = newMap;
        }

    }

    private static void flag(int[][] regions, boolean[][] visited, Map<Integer, Population> mapSize, int initRow, int initCol, int region) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{initRow, initCol});

        regions[initRow][initCol] = region;
        visited[initRow][initCol] = true;
        mapSize.put(region, new Population(1, map[initRow][initCol]));

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int row = poll[0];
            int col = poll[1];

            for (int i = 0; i < 4; i++) {
                int nextRow = row + dr[i];
                int nextCol = col + dc[i];

                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) {
                    continue;
                }

                if (visited[nextRow][nextCol]) {
                    continue;
                }

                int difference = Math.abs(map[row][col] - map[nextRow][nextCol]);
                if (difference >= min && difference <= max) {
                    regions[nextRow][nextCol] = region;

                    visited[nextRow][nextCol] = true;
                    queue.add(new int[]{nextRow, nextCol});
                    Population population = mapSize.get(region);
                    population.size++;
                    population.population += map[nextRow][nextCol];
                }
            }
        }
    }

    private static class Population {
        int size;
        int population;

        public Population(int size, int population) {
            this.size = size;
            this.population = population;
        }

        public int getNewPopulation() {
            return population / size;
        }
    }
}
