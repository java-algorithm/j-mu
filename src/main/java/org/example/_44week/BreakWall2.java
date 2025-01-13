package org.example._44week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BreakWall2 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int EMPTY = 0;
    private static final int WALL = 1;
    private static final int ROW = 0;
    private static final int COL = 1;

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};
    private static int[][] regions;
    private static Map<Integer, Integer> regionSizeMap;
    private static int[][] map;

    private static int rowSize;
    private static int colSize;

    public static void main(String[] args) throws IOException {
        input();

        // 여기서 regions랑 regionSizes랑 체크할거임.
        regionInitialize();

        // 실제로 크기 인지하기.
        StringBuilder sb = new StringBuilder();
        calculateNewRegionSize(sb);

        System.out.println(sb);
    }

    private static void calculateNewRegionSize(StringBuilder sb) {
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (map[row][col] == EMPTY) {
                    sb.append(0);
                    continue;
                }

                Set<Integer> regionNums = new HashSet<>();
                for (int i = 0; i < 4; i++) {
                    int nextRow = row + dr[i];
                    int nextCol = col + dc[i];

                    if (isOut(nextRow, nextCol)) {
                        continue;
                    }

                    if (map[nextRow][nextCol] == EMPTY) {
                        regionNums.add(regions[nextRow][nextCol]);
                    }
                }

                int sum = regionNums.stream().mapToInt(region -> regionSizeMap.get(region)).sum();
                sb.append((sum+1)%10);
            }

            sb.append("\n");
        }
    }

    private static void regionInitialize() {
        int region = 2;
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (map[row][col] == WALL || regions[row][col] !=EMPTY) {
                    continue;
                }

                // BFS
                Stack<int[]> stack = new Stack<>();
                stack.add(new int[]{row, col});
                regions[row][col] = region;
                int size = 1;
                while (!stack.isEmpty()) {
                    int[] pop = stack.pop();

                    for (int i = 0; i < 4; i++) {
                        int nextRow = pop[ROW] + dr[i];
                        int nextCol = pop[COL] + dc[i];

                        if (isOut(nextRow, nextCol)) {
                            continue;
                        }

                        if (map[nextRow][nextCol] == EMPTY && regions[nextRow][nextCol] == EMPTY) {
                            regions[nextRow][nextCol] = region;
                            stack.push(new int[]{nextRow, nextCol});
                            size++;
                        }
                    }
                }

                regionSizeMap.put(region, size);
                region++;
            }
        }
    }

    private static boolean isOut(int nextRow, int nextCol) {
        return nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize;
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];
        regions = new int[rowSize][colSize];
        regionSizeMap = new HashMap<>();
        for (int i = 0; i < rowSize; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
    }


}
