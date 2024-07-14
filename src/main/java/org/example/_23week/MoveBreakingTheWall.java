package org.example._23week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MoveBreakingTheWall {

    private static int ROW_SIZE;
    private static int COL_SIZE;

    private static int[][] map;
    private static boolean[][] visited;

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        ROW_SIZE = Integer.parseInt(st.nextToken());
        COL_SIZE = Integer.parseInt(st.nextToken());

        map = new int[ROW_SIZE][COL_SIZE];
        visited = new boolean[ROW_SIZE][COL_SIZE];
        for (int i = 0; i < ROW_SIZE; i++) {
            map[i] = Arrays.stream(br.readLine().split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        bfs(0,0);
    }

    private static void bfs(final int startingRow, final int startingCol) {

    }
}
