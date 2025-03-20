package org.example._50week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TheSwanLake {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final int WATER = 0;
    private static final int ICE = 1;
    private static final int SWAN = 2;

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    private static int rowSize;
    private static int colSize;
    private static int[][] map;
    private static int[][] group;
    private static Queue<int[]> melts;
    private static Queue<int[]> nextMelts;
    private static final List<Integer> swanGroups = new ArrayList<>();
    private static final Map<Integer, Integer> parent = new HashMap<>();

    public static void main(String[] args) throws IOException {
        input();

        int groupNo = 3;
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (map[row][col] != ICE && group[row][col] == 0) { // WATER, SWAN
                    markGroup(row, col, groupNo);
                    groupNo++;
                }
            }
        }

        // 처음에 바로 만나있으면
        // sout 0
        Integer firstSwanGroup = swanGroups.get(0);
        Integer secondSwanGroup = swanGroups.get(1);
        if (firstSwanGroup.equals(secondSwanGroup)) {
            System.out.println(0);
        }

        // 1일차.
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (map[row][col] == ICE) {
                    boolean isConnected = melt(row, col);
                    if (isConnected) {
                        System.out.println(1);
                        return;
                    }
                }
            }
        }

        for (int[] melt : melts) {
            map[melt[0]][melt[1]] = WATER;
        }

        for (int day = 2; ; day++) {
            melts = nextMelts;
            nextMelts = new LinkedList<>();

            for (int[] melt : melts) {
                int row = melt[0];
                int col = melt[1];

                if (map[row][col] == ICE) {
                    boolean isConnected = melt(row, col);
                    if (isConnected) {
                        System.out.println(day);
                        return;
                    }
                }
            }

            for (int[] melt : melts) {
                map[melt[0]][melt[1]] = WATER;
            }
        }
    }

    private static boolean melt(int row, int col) {
        for (int i = 0; i < 4; i++) {
            int nextRow = row + dr[i];
            int nextCol = col + dc[i];

            if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) {
                continue;
            }

            if (map[nextRow][nextCol] == ICE) {
                continue;
            }

            melts.add(new int[]{row, col});
            group[row][col] = group[nextRow][nextCol];
        }

        if (group[row][col] == 0) {
            return false;
        }

        for (int i = 0; i < 4; i++) {
            int nextRow = row + dr[i];
            int nextCol = col + dc[i];

            if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) {
                continue;
            }

            int adjacent = group[nextRow][nextCol];
            if (adjacent == 0) {
                nextMelts.add(new int[]{nextRow, nextCol});
                continue;
            }

            if (group[row][col] == adjacent) {
                continue;
            }

            union(group[row][col], adjacent);

            Integer firstSwanGroup = swanGroups.get(0);
            Integer secondSwanGroup = swanGroups.get(1);
            if (isSame(firstSwanGroup, secondSwanGroup)) {
                return true;
            }
        }

        return false;
    }

    private static void union(int first, int second) {
        if (first == 0) {
            parent.put(first, second);
            return;
        } else if (second == 0) {
            parent.put(second, first);
            return;
        }

        int firstParent = find(first);
        int secondParent = find(second);

        if (firstParent <= secondParent) {
            parent.put(secondParent, firstParent);
        } else {
            parent.put(firstParent, secondParent);
        }
    }

    private static boolean isSame(int first, int second) {
        int firstParent = find(first);
        int secondParent = find(second);

        return firstParent == secondParent;
    }

    private static int find(int unit) {
        if (parent.get(unit) == unit) {
            return unit;
        }

        int p = parent.get(parent.get(unit));
        parent.put(unit, p);

        return p;
    }

    private static void markGroup(int row, int col, int groupNo) {
        // 진영 선택
        // 백조 진영 확인
        // union find 배열도 만들어줘야함.
        parent.put(groupNo, groupNo);

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        group[row][col] = groupNo;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int curRow = pos[0];
            int curCol = pos[1];
            group[curRow][curCol] = groupNo;

            if (map[curRow][curCol] == SWAN) {
                swanGroups.add(groupNo);
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = curRow + dr[i];
                int nextCol = curCol + dc[i];

                if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) {
                    continue;
                }

                if (map[nextRow][nextCol] == ICE) {
                    continue;
                }

                if (group[nextRow][nextCol] != 0) {
                    continue;
                }

                group[nextRow][nextCol] = groupNo;
                queue.add(new int[]{nextRow, nextCol});
            }
        }
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];
        group = new int[rowSize][colSize];
//        melts = new boolean[rowSize][colSize];
        melts = new LinkedList<>();
        nextMelts = new LinkedList<>();

        for (int row = 0; row < rowSize; row++) {
            String input = br.readLine();

            for (int col = 0; col < colSize; col++) {
                char cell = input.charAt(col);
                if (cell == 'X') {
                    map[row][col] = ICE;
                } else if (cell == '.') {
                    map[row][col] = WATER;
                } else {
                    map[row][col] = SWAN;
                }
            }
        }
    }
}