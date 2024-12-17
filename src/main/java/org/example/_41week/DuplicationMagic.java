package org.example._41week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DuplicationMagic {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    private static final int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};

    // 4 * 4* 4 = 2^6 = 64
    // 상 좌 하 우
    private static final int[] sdr = {-1, 0, 1, 0};
    private static final int[] sdc = {0, -1, 0, 1};

    private static int maxFishCount = Integer.MIN_VALUE;
    private static Position[] bestPractice;

    private static int fishCount;
    private static int testCount;
    private static Fish shark;
    private static List<Fish>[][] map = new List[5][5];
    private static final int[][] smells = new int[5][5];
    private static List<Fish>[][] ready;

    public static void main(String[] args) throws IOException {
        init();

        for (int stage = 1; stage <= testCount; stage++) {
            // duplication magic 실행.
//            System.out.println("init: " + stage);
//            print(stage);

            useMagic();

            // move all fishes
//            System.out.println("move fishes");
//            System.out.println();
            moveFishes(stage);
//            print(stage);

//            System.out.println("move shark");
//            System.out.println();
            // 상어가 3칸 연속 이동.
            moveShark(stage);
//            print(stage);

//            System.out.println("realize magic");
//            System.out.println();
            // duplication magic 실현
            realizeMagic();
//            print(stage);

//            System.out.println();
        }

        printRemainFishes();
    }

    private static void print(int stage) {
        for (int row = 1; row < 5; row++) {
            for (int col = 1; col < 5; col++) {
                if (shark.row == row && shark.col == col) {
                    System.out.print("S ");
                    continue;
                }

                if (hasSmell(stage, row, col)) {
                    System.out.print("X ");
                    continue;
                }

                System.out.print(map[row][col].isEmpty() ? "0 " : map[row][col].size() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    private static void printRemainFishes() {
        int count = 0;
        for (int row = 1; row < 5; row++) {
            for (int col = 1; col < 5; col++) {
                count += map[row][col].size();
            }
        }

        System.out.println(count);
    }

    private static void moveShark(int stage) {
        // find best practice
        // 격자범위 벗어나면 x
        // 중간에 물고기있으면 잡아먹고.
        // dir1, dir2, dir3, fishNo;
        maxFishCount = Integer.MIN_VALUE;
        bestPractice = new Position[3];
        Position[] practice = new Position[3];
        findBestPractice(shark.row, shark.col, 0, practice);
        // 3가지 위치를 가지고있는데

        // map 4*4 => 16개 밖에 안되고
        // 같은 위치의 물고기를 모아놓고 한번에 제거할 수 있으면 좋겠다.

        // 물고기들의 위치를 모두 조사해서 각 3가지 위치에 포함되면은 제거해야한다.
        // 1000000 * 3 = 300만 * 100 => 3억번의 연산이 필요해요.
        // JAVA 1억 = 1초 쯤 된다.
        List<Fish>[][] newFishes = generateTempMap();
        for (int row = 1; row < 5; row++) {
            for (int col = 1; col < 5; col++) {
                if (isSharkTrace(row, col)) {
                    if (!map[row][col].isEmpty()) {
                        smells[row][col] = stage;
                    }

                    continue;
                }

                newFishes[row][col].addAll(map[row][col]);
            }
        }


        map = newFishes;
        shark.row = bestPractice[2].row;
        shark.col = bestPractice[2].col;
    }

    private static boolean isSharkTrace(int row, int col) {
        return (row == bestPractice[0].row && col == bestPractice[0].col) ||
                (row == bestPractice[1].row && col == bestPractice[1].col) ||
                (row == bestPractice[2].row && col == bestPractice[2].col);
    }

    private static void findBestPractice(int row, int col, int depth, Position[] practice) {
        if (depth == 3) {
            Set<Position> positions = new HashSet<>();
            positions.add(practice[0]);
            positions.add(practice[1]);
            positions.add(practice[2]);

            int sum = 0;
            for (Position position : positions) {
                sum += map[position.row][position.col].size();
            }

            if (maxFishCount < sum) {
                for (int i = 0; i < 3; i++) {
                    bestPractice[i] = new Position(practice[i].row, practice[i].col);
                }

                maxFishCount = sum;
            }

            return;
        }

        for (int idx = 0; idx < 4; idx++) {
            int nextRow = row + sdr[idx];
            int nextCol = col + sdc[idx];

            if (isOut(nextRow, nextCol)) {
                continue;
            }

            practice[depth] = new Position(nextRow, nextCol);
            findBestPractice(nextRow, nextCol, depth + 1, practice);
        }
    }

    private static void moveFishes(int stage) {
        List<Fish>[][] newFishes = generateTempMap();

        for (int row = 1; row < 5; row++) {
            for (int col = 1; col < 5; col++) {
                List<Fish> fishes = map[row][col];

                for (Fish fish : fishes) {
                    for (int i = 0; i < 8; i++) {
                        int nextRow = fish.nextRow();
                        int nextCol = fish.nextCol();

                        if (isOut(nextRow, nextCol)) {
                            fish.rotate();
                            continue;
                        }

                        if (nextRow == shark.row && nextCol == shark.col) {
                            fish.rotate();
                            continue;
                        }

                        if (hasSmell(stage, nextRow, nextCol)) {
                            fish.rotate();
                            continue;
                        }

                        fish.row = nextRow;
                        fish.col = nextCol;
                        break;
                    }
                }

                for (Fish fish : fishes) {
                    newFishes[fish.row][fish.col].add(fish);
                }
            }
        }

        map = newFishes;
    }

    private static List<Fish>[][] generateTempMap() {
        List<Fish>[][] newFishes = new List[5][5];

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                newFishes[row][col] = new ArrayList();
            }
        }

        return newFishes;
    }

    private static boolean hasSmell(int stage, int nextRow, int nextCol) {
        return smells[nextRow][nextCol] != 0 && smells[nextRow][nextCol] + 2 >= stage;
    }

    private static void realizeMagic() {
        List<Fish>[][] clones = ready;

        for (int row = 1; row < 5; row++) {
            for (int col = 1; col < 5; col++) {
                map[row][col].addAll(clones[row][col]);
            }
        }
    }

    private static void useMagic() {
        List<Fish>[][] cloneFishes = new List[5][5];

        for (int row = 1; row < 5; row++) {
            for (int col = 1; col < 5; col++) {
                cloneFishes[row][col] = new ArrayList<>();

                for (Fish fish : map[row][col]) {
                    cloneFishes[row][col].add(fish.clone());
                }
            }
        }

        ready = cloneFishes;
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        fishCount = Integer.parseInt(st.nextToken());
        testCount = Integer.parseInt(st.nextToken());

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                map[row][col] = new ArrayList<>();
            }
        }

        for (int i = 0; i < fishCount; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken()) - 1;

            map[row][col].add(new Fish(row, col, dir));
        }

        st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        shark = new Fish(row, col, 0);
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
            return
                    "row=" + row +
                            ", col=" + col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return row == position.row && col == position.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    private static class Fish {
        int row;
        int col;
        int dir;

        public Fish(int row, int col, int dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }

        public Fish clone() {
            return new Fish(row, col, dir);
        }

        public void rotate() {
            dir = (dir + 7) % 8;
        }

        public int nextRow() {
            return this.row + dr[dir];
        }

        public int nextCol() {
            return this.col + dc[dir];
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "row=" + row +
                    ", col=" + col +
                    ", dir=" + dir(dir) +
                    '}';
        }

        private String dir(int dir) {
            switch (dir) {
                case 0:
                    return "←";
                case 1:
                    return "↖";
                case 2:
                    return "↑";
                case 3:
                    return "↗";
                case 4:
                    return "→";
                case 5:
                    return "↘";
                case 6:
                    return "↓";
                case 7:
                    return "↙";
                default:
                    return "";
            }
        }
    }

    private static boolean isOut(int row, int col) {
        return (row <= 0 || row >= 5 || col <= 0 || col >= 5);
    }
}
