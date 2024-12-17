package org.example._41week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bizard {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 1 2 3 4
    // u d l r
    // 0 1 2 3
    // l d r u
    private static final int[] dr = {0, 1, 0, -1};
    private static final int[] dc = {-1, 0, 1, 0};
    private static final int[] answers = new int[4];
    private static final int EMPTY = 0;
    private static int N;
    private static int M;
    private static int[][] map;
    private static int[][] commands;

    private static int sharkRow;
    private static int sharkCol;

    private static boolean[][] visited;


    public static void main(String[] args) throws IOException {
//        makeNumber();
//        expressVisited();

        init();

        for (int i = 0; i < M; i++) {
            blizzard(commands[i][0], commands[i][1]);

            while (boom()) {
            }

            transformBeads();
        }


        System.out.println(answers[1] + 2 * answers[2] + 3 * answers[3]);
    }

        private static void expressVisited() {
        N = 7;
        int row = 4;
        int col = 4;
        visited = new boolean[N + 1][N + 1];
        visited[row][col] = true;
        print(visited);

        for (int i = 0; i <= 8; i++) {
            for (int j = 1; j <= 2 * (i + 1); j++) {
                if (j <= (i + 1)) {
                    row = row + dr[(2 * i) % 4];
                    col = col + dc[(2 * i) % 4];
                } else {
                    row = row + dr[(2 * i + 1) % 4];
                    col = col + dc[(2 * i + 1) % 4];
                }

                if (isOut(row, col)) {
                    return;
                }

                visited[row][col] = true;
                print(visited);
            }

            System.out.println();
        }
    }

    private static void makeNumber() {
        for (int i = 0; i <= 8; i++) {
            for (int j = 1; j <= 2 * (i + 1); j++) {
                if (j <= (i + 1)) {
                    System.out.print((2 * i) % 4 + " ");
                } else {
                    System.out.print((2 * i + 1) % 4 + " ");
                }
            }

            System.out.println();
        }
    }

    private static void print(boolean[][] visited) {
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                System.out.print(visited[row][col] ? "V " : "0 ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void blizzard(int dir, int size) {
        // before
        // 1 2 3 4
        // u d l r

        // after
        // 0 1 2 3
        // l d r u
        dir = adjust(dir);

        int row = sharkRow;
        int col = sharkCol;

        for (int i = 0; i < size; i++) {
            row = row + dr[dir];
            col = col + dc[dir];

            map[row][col] = EMPTY;
        }
    }

    private static int adjust(int dir) {
        if (dir == 1) {
            dir = 3;
        } else if (dir == 2) {
            dir = 1;
        } else if (dir == 3) {
            dir = 0;
        } else {
            dir = 2;
        }
        return dir;
    }

    private static Deque<List<Integer>> pickUp() {
        int row = sharkRow;
        int col = sharkCol;

        Deque<List<Integer>> deque = new ArrayDeque<>();

        // 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
        // 1 1 2 2 3 3 4 4 5 5  6  6  7  7  8  8
        for (int i = 0; ; i++) {
            for (int j = 1; j <= 2 * (i + 1); j++) {
                if (j <= (i + 1)) {
                    row = row + dr[(2 * i) % 4];
                    col = col + dc[(2 * i) % 4];
                } else {
                    row = row + dr[(2 * i + 1) % 4];
                    col = col + dc[(2 * i + 1) % 4];
                }

                if (isOut(row, col)) {
                    return deque;
                }

                List<Integer> peek = deque.peekLast();

                if (map[row][col] == EMPTY) {
                    continue;
                }

                if (peek == null || peek.get(0) != map[row][col]) {
                    List<Integer> list = new LinkedList<>();
                    list.add(map[row][col]);

                    deque.add(list);
                } else {
                    peek.add(map[row][col]);
                }

                map[row][col] = 0;
            }
        }
    }

    private static Deque<Integer> pickUpForTransform() {
        int row = sharkRow;
        int col = sharkCol;

        Deque<List<Integer>> temp = pickUp();
        Deque<Integer> breads = new ArrayDeque<>();

        while (!temp.isEmpty()) {
            List<Integer> pop = temp.pop();
            breads.addLast(pop.size());
            breads.addLast(pop.get(0));
        }

        return breads;
    }

    private static boolean isOut(int row, int col) {
        return row <= 0 || row > N || col <= 0 || col > N;
    }

    // 폭발 채워놓기
    private static boolean boom() {
        int[][] newMap = new int[N + 1][N + 1];
        int row = sharkRow;
        int col = sharkCol;

        boolean hasConsecutiveBeads = false;

        Deque<List<Integer>> breads = pickUp();

        for (int i = 0; ; i++) {
            for (int j = 1; j <= 2 * (i + 1); j++) {
                int prevRow = row;
                int prevCol = col;
                if (j <= (i + 1)) {
                    row = row + dr[(2 * i) % 4];
                    col = col + dc[(2 * i) % 4];
                } else {
                    row = row + dr[(2 * i + 1) % 4];
                    col = col + dc[(2 * i + 1) % 4];
                }

                if (breads.isEmpty()) {
                    map = newMap;

                    return hasConsecutiveBeads;
                }

                List<Integer> peek = breads.peek();
                if (peek.size() >= 4) {
                    breads.pop();

                    // 값 되돌리기.
                    j--;
                    row = prevRow;
                    col = prevCol;

                    hasConsecutiveBeads = true;

                    answers[peek.get(0)] += peek.size();
                    continue;
                }

                Integer value = peek.remove(0);
                newMap[row][col] = value;
                if (peek.isEmpty()) {
                    breads.pop();
                }
            }
        }
    }

    private static void transformBeads() {
        int[][] newMap = new int[N + 1][N + 1];
        int row = sharkRow;
        int col = sharkCol;

        Deque<Integer> breads = pickUpForTransform();

        for (int i = 0; ; i++) {
            for (int j = 1; j <= 2 * (i + 1); j++) {
                if (j <= (i + 1)) {
                    row = row + dr[(2 * i) % 4];
                    col = col + dc[(2 * i) % 4];
                } else {
                    row = row + dr[(2 * i + 1) % 4];
                    col = col + dc[(2 * i + 1) % 4];
                }

                if (isOut(row, col) || breads.isEmpty()) {
                    map = newMap;
                    return;
                }

                newMap[row][col] = breads.pop();
            }
        }
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        commands = new int[M][2];

        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= N; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            commands[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        sharkRow = (N + 1) / 2;
        sharkCol = (N + 1) / 2;
    }
}
