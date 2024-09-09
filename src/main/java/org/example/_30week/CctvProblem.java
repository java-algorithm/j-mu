package org.example._30week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CctvProblem {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int[][] map;
    private static List<CCTV> cctvs;
    private static int rowSize;
    private static int colSize;
    private static int emptySize;

    private static final int WALL = 6;

    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, 1, 0, -1};

    private static int blindSpotSize = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        emptySize = 0;

        map = new int[rowSize][colSize];
        cctvs = new ArrayList<>();

        for (int row = 0; row < rowSize; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < colSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
                if (map[row][col] == 0) {
                    emptySize++;
                } else if (map[row][col] >= 1 && map[row][col] <= 5) {
                    cctvs.add(new CCTV(row, col, map[row][col]));
                }
            }
        }

        boolean[][] flags = new boolean[rowSize][colSize];
        dfs(0, 0, flags);

        System.out.println(blindSpotSize);
    }

    private static void dfs(int index, int count, boolean[][] flags) {
        if (cctvs.size() == index) {
            blindSpotSize = Math.min(blindSpotSize, emptySize - count);
            return;
        }

        CCTV cctv = cctvs.get(index);
        for (int[] direction : cctv.directions()) {
            boolean[][] newFlags = copyFlags(flags);
            int tempCount = count;
            for (int dir : direction) {
                int row = cctv.row;
                int col = cctv.col;

                while (true) {
                    row = row + dr[dir];
                    col = col + dc[dir];

                    if (row < 0 || row >= rowSize || col < 0 || col >= colSize) {
                        break;
                    }

                    if (map[row][col] == WALL) {
                        break;
                    }

                    if (map[row][col] >= 1 && map[row][col] <= 5) {
                        continue;
                    }

                    if (newFlags[row][col]) {
                        continue;
                    }

                    newFlags[row][col] = true;
                    tempCount++;
                }
            }

            dfs(index + 1, tempCount, newFlags);
        }
    }

    private static boolean[][] copyFlags(boolean[][] flags) {
        boolean[][] newFlags = new boolean[rowSize][colSize];

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                newFlags[row][col] = flags[row][col];
            }
        }

        return newFlags;
    }

    private static class CCTV {
        private static final int[][] one = {{0}, {1}, {2}, {3}};
        private static final int[][] two = {{0, 2}, {1, 3}};
        private static final int[][] three = {{0, 1}, {1, 2}, {2, 3}, {3, 0}};
        private static final int[][] four = {{3, 0, 1}, {0, 1, 2}, {1, 2, 3}, {2, 3, 0}};
        private static final int[][] five = {{0, 1, 2, 3}};

        int row;
        int col;
        int no;

        public CCTV(int row, int col, int no) {
            this.row = row;
            this.col = col;
            this.no = no;
        }

        @Override
        public String toString() {
            return "CCTV{" +
                    "row=" + row +
                    ", col=" + col +
                    ", no=" + no +
                    '}';
        }

        public int[][] directions() {
            switch (no) {
                case 1:
                    return one;
                case 2:
                    return two;
                case 3:
                    return three;
                case 4:
                    return four;
                case 5:
                    return five;
                default:
                    return null;
            }
        }

        public int directionSize() {
            switch (no) {
                case 1:
                    return one.length;
                case 2:
                    return two.length;
                case 3:
                    return three.length;
                case 4:
                    return four.length;
                case 5:
                    return five.length;
                default:
                    return 0;
            }
        }
    }

}
