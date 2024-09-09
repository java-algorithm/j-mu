package org.example._31week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GhostLeg {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int colSize = Integer.parseInt(st.nextToken());
        int legCount = Integer.parseInt(st.nextToken());
        int rowSize = Integer.parseInt(st.nextToken());

        int[][] map = new int[rowSize + 1][colSize + 1];
        for (int i = 0; i < legCount; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            map[row][col] = col + 1;
            map[row][col + 1] = col;
        }

//        printArray(map);

        dfs(map, 0, 1, 1);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
//        System.out.println("---- 사다리 타기 결과-----");
//        for (int i = 1; i < map[0].length; i++) {
//            int result = rideLadder(i, map);
//            System.out.println(i + "번 유저는 " + result + "에 도착했습니다.");
//        }
    }

    private static void dfs(int[][] map, int depth, int row, int col) {
        if (depth == 4) {
            return;
        }
        boolean isAnswer = isAnswer(map);

        if (isAnswer) {
            answer = Math.min(depth, answer);
            return;
        }

        for (int i = row; i < map.length; i++) {
            for (int j = (i == row) ? col : 1; j < map[0].length - 1; j++) {
                if (map[i][j] != 0) {
                    j++;
                    continue;
                }

                if (map[i][j + 1] != 0) {
                    continue;
                }

                map[i][j] = j + 1;
                map[i][j + 1] = j;
                dfs(map, depth + 1, i, j);
                map[i][j] = 0;
                map[i][j + 1] = 0;
            }
        }
    }

    private static boolean isAnswer(int[][] map) {
        for (int i = 0; i < map[0].length; i++) {
            int result = rideLadder(i, map);
            if (result != i) {
                return false;
            }
        }
        return true;
    }

    private static int rideLadder(int no, int[][] map) {
        int rowSize = map.length;
        for (int row = 0; row < rowSize; row++) {
            if (map[row][no] == 0) {
                continue;
            }

            no = map[row][no];
        }

        return no;
    }

    private static void printArray(int[][] map) {
        System.out.println("---------사다리------------");
        for (int i = 1; i < map[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("-------------------------");
        for (int i = 1; i < map.length; i++) {
            for (int j = 1; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

