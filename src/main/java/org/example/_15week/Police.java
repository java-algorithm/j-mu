package org.example._15week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Police {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int width;
    private static int height;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        // 가게 위치 초기화
        st = new StringTokenizer(br.readLine());
        final int shopsCount = Integer.parseInt(st.nextToken());

        final Position[] shops = new Position[shopsCount];
        for (int i = 0; i < shopsCount; i++) {
            st = new StringTokenizer(br.readLine());
            final int direction = Integer.parseInt(st.nextToken());
            final int distance = Integer.parseInt(st.nextToken());

            final Position position = new Position(direction, distance);
            shops[i] = position;
        }

        // 동근 위치 초기화
        st = new StringTokenizer(br.readLine());
        final int direction = Integer.parseInt(st.nextToken());
        final int distance = Integer.parseInt(st.nextToken());

        final Position dongeun = new Position(direction, distance);

        // 거리 계산
        final int[] distances = new int[shopsCount];
        for (int i = 0; i < shopsCount; i++) {
            // 시계 방향
            final int clockwiseDistance = (height - shops[i].row) + shops[i].col + (height - dongeun.row) + dongeun.col;
            final int counterClockwiseDistance = (height - shops[i].row) + (height - dongeun.row) + (width - shops[i].col) + (width - dongeun.col);

            distances[i] = Math.min(clockwiseDistance, counterClockwiseDistance);
        }

        final int answer = Arrays.stream(distances).sum();
        System.out.println(answer);
    }

    private static class Position {
        int row;
        int col;

        public Position(final int direction, final int distance) {
            switch (direction) {
                case 1:
                    this.row = 0;
                    this.col = distance;
                    break;
                case 2:
                    this.row = height;
                    this.col = distance;
                    break;
                case 3:
                    this.col = 0;
                    this.row = distance;
                    break;
                case 4:
                    this.col = width;
                    this.row = distance;
                    break;
            }
        }
    }
}
