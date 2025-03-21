package org.example._51week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TheSwanLake {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    private static final char WATER = '.';
    private static final char ICE = 'X';
    private static final char SWAN = 'L';
    public static final int EMPTY_REGION = 0;
    private static char[][] map;
    private static int[][] regions;
    private static Map<Integer, Integer> parents = new HashMap<>();
    private static int R;
    private static int C;

    private static int[] swan;
    private static int swanCnt;
    private static int regionNo = 1;

    private static List<int[]> ices = new ArrayList<>(); // 중복 가능성 고려 필요

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        regions = new int[R][C];
        swan = new int[2];

        for (int row = 0; row < R; row++) {
            String input = br.readLine();

            for (int col = 0; col < C; col++) {
                map[row][col] = input.charAt(col);
            }
        }

        // 1. 영역 나누기. 0: 영역 없음
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (map[row][col] == ICE) {
                    continue;
                }

                if (regions[row][col] != EMPTY_REGION) {
                    continue;
                }

                bfs(row, col, map, regions);
                regionNo++;
            }
        }

        if (isSame(swan[0], swan[1])) {
            System.out.println(0);
            return;
        }

//        printRegion();


        // 2. 이번에 녹을 얼음들만 대상으로 bfs돌면서 얼음 녹이기.
        for (int day = 1; ; day++) {
            List<int[]> next = new ArrayList<>();

            // 얼음 녹이고 인접 지역번호 설정.
            for (int idx = 0; idx < ices.size(); idx++) {
                int[] poll = ices.get(idx);

                int row = poll[0];
                int col = poll[1];
                if (map[row][col] != ICE) {
                    continue;
                }

                map[row][col] = WATER;

                for (int i = 0; i < 4; i++) {
                    int nextRow = row + dr[i];
                    int nextCol = col + dc[i];

                    if (isOut(nextRow, nextCol)) {
                        continue;
                    }

                    if (map[nextRow][nextCol] == ICE) {
                        next.add(new int[]{nextRow, nextCol});
                    }

                    if (map[nextRow][nextCol] != ICE) {
                        regions[row][col] = regions[nextRow][nextCol];
                    }
                }
            }

//            printRegion();

            // 녹은 얼음들을 순회하면서 지역끼리 인접해진 것이 없는지 확인. 인접했다면 union
            for (int idx = 0; idx < ices.size(); idx++) {
                int[] melt = ices.get(idx);
                int row = melt[0];
                int col = melt[1];

                List<Integer> adjacents = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    int nextRow = row + dr[i];
                    int nextCol = col + dc[i];

                    if (isOut(nextRow, nextCol)) {
                        continue;
                    }

                    if (regions[nextRow][nextCol] != EMPTY_REGION) {
                        adjacents.add(regions[nextRow][nextCol]);
                    }
                }

                if (adjacents.isEmpty()) {
                    continue;
                }

                Integer pivot = adjacents.get(0);
                for (int i = 0; i < adjacents.size(); i++) {
                    union(pivot, adjacents.get(i));
                }
            }

            // 백조끼리 이어졌는지 확인.
            if (isSame(swan[0], swan[1])) {
//                printRegion();
                System.out.println(day);
                return;
            }

            ices = next;

//            printMap(day);
//            printRegion();
        }
    }

    private static int find(int region) {
        Integer parent = parents.get(region);
        if (parent == region) {
            return parent;
        }

        Integer pp = find(parent);
        parents.put(region, pp);
        return pp;
    }

    private static void union(int region1, int region2) {
        if (region1 == region2) {
            return;
        }

        int parent1 = find(region1);
        int parent2 = find(region2);

        if (parent1 == parent2) {
            return;
        }

        if (parent1 <= parent2) {
            parents.put(parent1, parent2);
        } else {
            parents.put(parent2, parent1);
        }
    }

    private static boolean isSame(int region1, int region2) {
        int parent1 = find(region1);
        int parent2 = find(region2);

        return parent1 == parent2;
    }

    private static void printIces() {
        for (int[] ice : ices) {
            System.out.println(ice[0] + "," + ice[1]);
        }
    }

    private static void printRegion() {
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                System.out.print(regions[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void printMap(int day) {
        System.out.println(day + "일 차");

        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                System.out.print(map[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void bfs(int row, int col, char[][] map, int[][] regions) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        regions[row][col] = regionNo;
        parents.put(regionNo, regionNo);

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int currentRow = poll[0];
            int currentCol = poll[1];

            if (map[currentRow][currentCol] == SWAN) {
                swan[swanCnt++] = regionNo;
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = currentRow + dr[i];
                int nextCol = currentCol + dc[i];

                if (isOut(nextRow, nextCol)) {
                    continue;
                }

                if (map[nextRow][nextCol] == ICE) {
                    ices.add(new int[]{nextRow, nextCol});
                    continue;
                }

                if (regions[nextRow][nextCol] != EMPTY_REGION) {
                    continue;
                }

                queue.add(new int[]{nextRow, nextCol});
                regions[nextRow][nextCol] = regionNo;
            }
        }
    }

    private static boolean isOut(int nextRow, int nextCol) {
        return nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C;
    }
}
