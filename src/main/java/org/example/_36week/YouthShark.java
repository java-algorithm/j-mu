package org.example._36week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class YouthShark {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static Fish shark;
    private static Fish[] fishes = new Fish[17];
    private static final int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {

        initMap();
        moveFishes();
    }

    private static void moveFishes() {
        for (int no = 1; no < 17; no++) {
            Fish fish = fishes[no];
            if (fish == null) {
                continue;
            }

            for (int i = 0; i < 8; i++) {
                int[] nextPosition = fish.getNextPosition();
                int nextRow = nextPosition[0];
                int nextCol = nextPosition[1];

                if (nextRow < 0 || nextRow >= 4 || nextCol < 0 || nextCol >= 4) {
                    fish.turn();
                    continue;
                }

                if (nextRow == shark.row && nextCol == shark.col) {
                    fish.turn();
                    continue;
                }

                Fish targetFish = Arrays.stream(fishes)
                        .filter(f -> f.row == nextRow && f.col == nextCol)
                        .findFirst()
                        .orElse(null);

                if (targetFish == null) {
                    fish.row = nextRow;
                    fish.col = nextCol;

                } else {

                }
            }
        }
    }

    private static void initMap() throws IOException {
        for (int row = 0; row < 4; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < 4; col++) {
                int fishNo = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken());

                if (row == 0 && col == 0) {
                    shark = new Fish(0, row, col, direction);
                    fishes[fishNo] = null;
                } else {
                    fishes[fishNo] = new Fish(fishNo, row, col, direction);
                }
            }
        }
    }

    static class Fish {
        int fishNo;
        int row;
        int col;
        int direction;

        public Fish(int fishNo, int row, int col, int direction) {
            this.fishNo = fishNo;
            this.row = row;
            this.col = col;
            this.direction = direction;
        }

        public int[] getNextPosition() {
            return new int[]{row + dr[direction], col + dc[direction]};
        }

        public void turn() {
            if (this.direction - 1 < 0) {
                this.direction += 7;
            } else {
                this.direction -= 1;
            }
        }
    }
}
