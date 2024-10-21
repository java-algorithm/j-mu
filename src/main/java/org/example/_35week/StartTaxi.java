package org.example._35week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class StartTaxi {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    private static final int EMPTY = 0;
    private static final int WALL = 1;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int mapSize = Integer.parseInt(st.nextToken());
        int customerCount = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());
        int[][] map = new int[mapSize][mapSize];
        int[][] starts = new int[mapSize][mapSize];
        int[][] ends = new int[mapSize][mapSize];

        for (int i = 0; i < mapSize; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < M; i++) {

        }


    }
}
