package org.example._10week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MineCraft {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        final StringTokenizer st = new StringTokenizer(br.readLine());

        final int rowLength = Integer.parseInt(st.nextToken());
        final int colLength = Integer.parseInt(st.nextToken());
        int inventory = Integer.parseInt(st.nextToken());

        int[][] map = new int[rowLength][colLength];

        int minHeight = Integer.MAX_VALUE;
        int maxHeight = Integer.MIN_VALUE;

        for (int i = 0; i < rowLength; i++) {
            final int[] row = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < colLength; j++) {
                map[i][j] = row[j];
                if (minHeight > map[i][j]) {
                    minHeight = map[i][j];
                }
                if (maxHeight < map[i][j]) {
                    maxHeight = map[i][j];
                }
            }
        }

        int minTime = Integer.MAX_VALUE;
        int height = 0;

        for (int target = minHeight; target <=maxHeight ; target++) {
            int need=0;
            int exceed = 0;

            for (int i = 0; i < rowLength; i++) {
                for (int j = 0; j < colLength; j++) {
                    final int value = map[i][j] - target;

                    if (value > 0) {
                        exceed+=value;
                    } else if (value < 0) {
                        need+=(-value);
                    }
                }
            }

            if (need > exceed + inventory) {
                continue;
            }

            final int needTime = exceed * 2 + need;

            if (minTime > needTime || minTime == needTime && height < target) {
                minTime = needTime;
                height = target;
            }
        }

        System.out.println(minTime+" "+height);
    }
}
