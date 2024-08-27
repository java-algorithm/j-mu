package org.example._29week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SnakeMaze {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final int[] dr = {0, 1, 0, -1};
    private static final int[] dc = {1, 0, -1, 0};

    private static final int APPLE = -1;
    private static final int EXIT = -1;
    private static final int SNAKE = 1;
    private static final int EMPTY = 0;

    private static int[][] map;
    private static Map<Integer, String> commands = new HashMap<>();
    private static int mapSize;

    public static void main(String[] args) throws IOException {
        mapSize = Integer.parseInt(br.readLine());
        map = new int[mapSize + 1][mapSize + 1];

        int appleCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < appleCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            map[row][col] = APPLE;
        }

        int commandCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < commandCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int second = Integer.parseInt(st.nextToken());
            String command = st.nextToken();

            commands.put(second, command);
        }

        Snake snake = new Snake();
        for (int i = 1; ; i++) {
            if (snake.moveHead() == EXIT) {
                System.out.println(i);
                return;
            }

            String command = commands.get(i);
            if (command != null) {
                snake.rotate(command);
            }
        }

    }

    private static class Snake {
        List<Position> snakes;
        int dir;

        public Snake() {
            snakes = new ArrayList<>();
            snakes.add(new Position(1, 1));
            map[1][1] = SNAKE;

            this.dir = 0;
        }

        public int moveHead() {
            int headIndex = snakes.size() - 1;
            int nextRow = snakes.get(headIndex).row + dr[dir];
            int nextCol = snakes.get(headIndex).col + dc[dir];

            if (nextRow <= 0 || nextRow > mapSize || nextCol <= 0 || nextCol > mapSize) {
                return EXIT;
            }

            if (map[nextRow][nextCol] == SNAKE) {
                return EXIT;
            }

            if (map[nextRow][nextCol] != APPLE) {
                moveTail();
            }

            snakes.add(new Position(nextRow, nextCol));
            map[nextRow][nextCol] = SNAKE;
            return 0;
        }

        private void moveTail() {
            Position tail = snakes.get(0);
            snakes.remove(0);
            map[tail.row][tail.col] = EMPTY;
        }

        public void rotate(String command) {
            switch (command) {
                case "L":
                    rotateLeft();
                    break;
                case "D":
                    rotateRight();
                    break;
                default:
                    break;
            }
        }

        private void rotateLeft() {
            dir = dir == 0 ? 3 : dir - 1;
        }

        private void rotateRight() {
            dir = dir == 3 ? 0 : dir + 1;
        }
    }

    private static class Position {
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }
}
