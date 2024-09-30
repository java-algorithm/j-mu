package org.example._32week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FishhookKing {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int answer = 0;

    private static int[] dr = {0, -1, 1, 0, 0};
    private static int[] dc = {0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rowSize = Integer.parseInt(st.nextToken());
        int colSize = Integer.parseInt(st.nextToken());
        int sharkCount = Integer.parseInt(st.nextToken());

        Shark[][] map = new Shark[rowSize + 1][colSize + 1];
        List<Shark> sharks = new LinkedList<>();

        for (int i = 0; i < sharkCount; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            Shark shark = new Shark(row, col, speed, direction, size);
            sharks.add(shark);
            map[row][col] = shark;
        }

        // 100*100 + 100*100*100
        // 10000 + 1000000
        // 100
        // 낚시꾼 이동
        for (int humanCol = 1; humanCol < colSize + 1; humanCol++) {
            // 낚시
            // 100
            for (int row = 1; row < rowSize + 1; row++) {
                if (map[row][humanCol] == null) {
                    continue;
                }

                Shark shark = map[row][humanCol];
                answer += shark.size;
                map[row][humanCol] = null;
                sharks.remove(shark);
                break;
            }

            // 100*100
            // 상어 이동
            List<Shark> shouldRemoveShark = new ArrayList<>();
            Shark[][] newMap = new Shark[rowSize + 1][colSize + 1];
            for (int i = 0; i < sharks.size(); i++) {
                Shark shark = sharks.get(i);
                int speed = 0;
                if (shark.direction == 1 || shark.direction == 2) {
                    speed = shark.speed % (rowSize * 2);
                }else{
                    speed = shark.speed % (colSize * 2);
                }

                for (int j = 0; j < speed; j++) {
                    int nextRow = shark.row + dr[shark.direction];
                    int nextCol = shark.col + dc[shark.direction];

                    if (nextRow <= 0 || nextRow > rowSize || nextCol <= 0 || nextCol > colSize) {
                        shark.row -= dr[shark.direction];
                        shark.col -= dc[shark.direction];
                        shark.reverseDirection();
                        continue;
                    }

                    shark.move(nextRow, nextCol);
                }

                // 해당 자리에 상어가 없다면 이동
                // 이미 map에 상어가 있는지 확인.
                if (newMap[shark.row][shark.col] == null) {
                    newMap[shark.row][shark.col] = shark;
                    continue;
                }

                // 해당 자리에 상어가 있다면
                // 크기가 작은 상어 잡아먹기.
                Shark existingShark = newMap[shark.row][shark.col];
                if (existingShark.size > shark.size) {
                    shouldRemoveShark.add(shark);
                } else {
                    newMap[shark.row][shark.col] = shark;
                    shouldRemoveShark.add(existingShark);
                }
            }

            sharks.removeAll(shouldRemoveShark);
            map = newMap;
        }

        System.out.println(answer);
    }

    private static class Shark {
        int row;
        int col;
        int speed;
        int direction;
        int size;

        public Shark(int row, int col, int speed, int direction, int size) {
            this.row = row;
            this.col = col;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }

        public void reverseDirection() {
            switch (direction) {
                case 1:
                    this.direction = 2;
                    break;
                case 2:
                    this.direction = 1;
                    break;
                case 3:
                    this.direction = 4;
                    break;
                case 4:
                    this.direction = 3;
                    break;
            }
        }

        public void move(int nextRow, int nextCol) {
            this.row = nextRow;
            this.col = nextCol;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "row=" + row +
                    ", col=" + col +
                    ", speed=" + speed +
                    ", direction=" + direction +
                    ", size=" + size +
                    '}';
        }
    }
}
