package org.example._15week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RobotCleaner {

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    private static final int NORTH = 0;
    private static final int EAST = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;

    private static final int NON_CLEAN = 0;
    private static final int WALL = 1;
    private static final int CLEANED = -1;


    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        st = new StringTokenizer(br.readLine());
        final int curRow = Integer.parseInt(st.nextToken());
        final int curCol = Integer.parseInt(st.nextToken());
        final int curDir = Integer.parseInt(st.nextToken());

        final Position position = new Position(curRow, curCol, curDir);

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int cleanedCell = 0;
        while (true) {
            if (map[position.row][position.col] == NON_CLEAN) {
                map[position.row][position.col] = CLEANED;
                cleanedCell++;
            }

            // 청소되지 않은 빈 칸이 있는지 확인.
            // 반시계 방향부터 확인해야함.
            boolean movable = false;
            for (int i = 0; i < 4; i++) {
                position.dir = calNextDir(position);
                final int nextPos = map[position.row + dr[position.dir]][position.col + dc[position.dir]];
                if (nextPos == NON_CLEAN) {
                    position.row += dr[position.dir];
                    position.col += dc[position.dir];
                    movable = true;
                    break;
                }
            }
            // 이동 가능하면 이동.
            if (movable) {
                continue;
            }

            // 이동 가능한 공간이 없는 경우.
            // 북쪽을 바라보고 있는경우 -> 남쪽
            // 북 -> 남 0 -> 2
            // 동 -> 서 1 -> 3
            // 남 -> 북 2 -> 0
            // 서 -> 동 3 -> 1
            // (position.dir + 2) % 4
            final int nextDir = calBackDir(position.dir);
            final int nextPos = map[position.row + dr[nextDir]][position.col + dc[nextDir]];
            if (nextPos == WALL) {
                System.out.println(cleanedCell);
                return;
            }

            position.row += dr[nextDir];
            position.col += dc[nextDir];
        }

    }

    private static int calBackDir(final int dir) {
        return (dir + 2) % 4;
    }

    private static int calNextDir(final Position position) {
        return position.dir - 1 < 0 ? 3 : position.dir - 1;
    }

    private static class Position {
        int row;
        int col;
        int dir;

        public Position(final int row, final int col, final int dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }
    }
}
