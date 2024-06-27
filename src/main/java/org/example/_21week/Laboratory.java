package org.example._21week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Laboratory {

    private static final int EMPTY = 0;
    private static final int WALL = 1;
    private static final int VIRUS = 2;

    private static int originalEmptyCount;
    private static int maxSafeAreaCount = Integer.MIN_VALUE;
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int rowSize;
    private static int colSize;

    private static final int[] dr = {0, 0, 1, -1};
    private static final int[] dc = {1, -1, 0, 0};

    private static int[][] map;

    // 시간복잡도는?
    // 모든 가능한 조합 * bfs이므로
    // 배열을 bfs로 도는데 N*M(가로 * 세로)
    // 가능한 조합을 찾는데 n*mC3 배열의 크기 C 3
    // dfs를 돌기전에 배열을 clone해야하므로 n*m
    // 따라서 n^2*m^2*(n*mC3)
    // n,m의 최대 값은 8이므로
    // 64 * 64 * 40000
    // 163,840,000
    // 하지만 최소 벽 3개는 존재하며, 바이러는 최소 2개가 존재. 총 5개는 탐색할 필요가 없는 좌표이므로
    // 59 * 59 * 32509
    // 113,163,829 ...
    // 음 그냥 벽개수와 바이러스 개수가 테스트 케이스에서 조금씩 더 커서 통과하는듯..?
    // 아 시간제한이 2초구나 ok
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        map = new int[rowSize][colSize];

        Queue<Position> queue = new LinkedList<>();
        for (int i = 0; i < rowSize; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < colSize; j++) {
                final int value = Integer.parseInt(st.nextToken());
                map[i][j] = value;

                if (value == VIRUS) {
                    final Position position = new Position(i, j);
                    queue.offer(position);
                } else if (value == EMPTY) {
                    originalEmptyCount++;
                }
            }
        }

        Position[] walls = new Position[3];
        bruteForce(queue, walls, 0, 0, 0);

        System.out.println(maxSafeAreaCount - 3);
    }

    // 조합 -> 백트래킹.
    static void bruteForce(Queue<Position> viruses, Position[] walls, int wallCount, int startRow, int startCol) {
        if (wallCount == 3) {
            final LinkedList<Position> queue = new LinkedList<>(viruses);
            final int[][] cloneMap = cloneArray();

            bfs(cloneMap, queue, walls);
            return;
        }

        for (int row = startRow; row < rowSize; row++) {
            for (int col = (row == startRow) ? startCol : 0; col < colSize; col++) {
                if (map[row][col] != EMPTY) {
                    continue;
                }

                walls[wallCount] = new Position(row, col);
                bruteForce(viruses, walls, wallCount + 1, row, col + 1);
            }
        }
    }

    static void bfs(int[][] cloneMap, Queue<Position> viruses, Position[] walls) {
        int emptyCount = originalEmptyCount;

        while (!viruses.isEmpty()) {
            final Position poll = viruses.poll();

            for (int i = 0; i < 4; i++) {
                final int nextRow = poll.row + dr[i];
                final int nextCol = poll.col + dc[i];

                if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) {
                    continue;
                }

                if (cloneMap[nextRow][nextCol] != EMPTY) {
                    continue;
                }


                if (isWall(walls, nextRow, nextCol)) {
                    continue;
                }

                cloneMap[nextRow][nextCol] = VIRUS;

                final Position position = new Position(nextRow, nextCol);
                viruses.offer(position);

                emptyCount--;
            }
        }

        maxSafeAreaCount = Math.max(emptyCount, maxSafeAreaCount);
    }

    static boolean isWall(Position[] walls, int nextRow, int nextCol) {
        for (final Position wall : walls) {
            if (wall.row == nextRow && wall.col == nextCol) {
                return true;
            }
        }

        return false;
    }

    public static int[][] cloneArray() {
        int[][] clone = new int[map.length][];
        for (int i = 0; i < map.length; i++) {
            clone[i] = Arrays.copyOf(map[i], map[i].length);
        }
        return clone;
    }

    private static class Position {
        int row;
        int col;

        public Position(final int row, final int col) {
            this.row = row;
            this.col = col;
        }
    }
}
