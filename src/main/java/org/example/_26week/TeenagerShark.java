package org.example._26week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TeenagerShark {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] directions = {{0, 0}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1},
            {-1, 1}};

    private static final int[] shark = new int[3];
    private static final String[] dirCh = {"", "↑", "↖", "←", "↙", "↓", "↘", "→", "↗"};

    private static int maxEating = 0;

    public static void main(String[] args) throws IOException {
        Fish[][] map = new Fish[4][4];
        Fish[] fishes = new Fish[17];

        for (int row = 0; row < 4; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int col = 0; col < 4; col++) {
                int fishNo = Integer.parseInt(st.nextToken());
                int fishDir = Integer.parseInt(st.nextToken());

                Fish fish = new Fish(row, col, fishDir, fishNo);
                map[row][col] = fish;
                fishes[fishNo] = fish;
            }
        }

        // 0,0에 상어 넣기
        Fish firstFish = map[0][0];
        int firstFishNo = firstFish.no;

        shark[0] = 0;
        shark[1] = 0;
        shark[2] = firstFish.dir;
        map[0][0] = null;
        fishes[firstFishNo] = null;

//        printMap(null, null, map);
        dfs(firstFishNo, map, fishes);

        System.out.println(maxEating);
    }

    public static void dfs(int eat, Fish[][] map, Fish[] fishes) {
        maxEating = Math.max(eat, maxEating);

        // 물고기 이동
        for (int fishNo = 1; fishNo <= 16; fishNo++) {
            Fish fish = fishes[fishNo];

            if (fish == null) {
                continue;
            }

            for (int i = 1; i <= 8; i++) {
                int nextRow = fish.row + directions[fish.dir][0];
                int nextCol = fish.col + directions[fish.dir][1];

                if (nextRow < 0 || nextRow >= 4 || nextCol < 0 || nextCol >= 4) {
                    fish.rotate();
                    continue;
                }

                if (nextRow == shark[0] && nextCol == shark[1]) {
                    fish.rotate();
                    continue;
                }

                Fish targetFish = map[nextRow][nextCol];
                if (targetFish == null) {
                    map[nextRow][nextCol] = fish;
                    map[fish.row][fish.col] = null;

                    fish.row = nextRow;
                    fish.col = nextCol;
                } else {
                    swap(fish, targetFish, map, fishes);
                }

//                printMap(fish, targetFish, map);
                break;
            }
        }

        for (int i = 1; ; i++) {
            int curSharkRow = shark[0];
            int curSharkCol = shark[1];
            int curSharkDir = shark[2];

            int nextRow = curSharkRow + directions[curSharkDir][0] * i;
            int nextCol = curSharkCol + directions[curSharkDir][1] * i;

            if (nextRow < 0 || nextRow >= 4 || nextCol < 0 || nextCol >= 4) {
                break;
            }

            if (map[nextRow][nextCol] == null) {
                continue;
            }

            Fish targetFish = map[nextRow][nextCol];
            map[nextRow][nextCol] = null;
            fishes[targetFish.no] = null;

            shark[0] = targetFish.row;
            shark[1] = targetFish.col;
            shark[2] = targetFish.dir;

            Fish[][] cloneMap = cloneMap(map);
            Fish[] cloneFishes = cloneFish(fishes);

//            PrintEatFish(targetFish, map, fishes);
            dfs(eat + targetFish.no, cloneMap, cloneFishes);

            map[nextRow][nextCol] = targetFish;
            fishes[targetFish.no] = targetFish;

            shark[0] = curSharkRow;
            shark[1] = curSharkCol;
            shark[2] = curSharkDir;
        }
    }

    private static void PrintEatFish(Fish targetFish, Fish[][] map, Fish[] fishes) {
        System.out.println("-------- shark eat " + targetFish.no + " fish ---------");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (shark[0] == i && shark[1] == j) {
                    System.out.print("{S, " + dirCh[shark[2]] + "} ");
                    continue;
                }

                if (map[i][j] == null) {
                    System.out.print("{   }");
                    continue;
                }


                System.out.print("{" + map[i][j].no + ", " + dirCh[map[i][j].dir] + "} ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("---- fishes list -----");
        Arrays.stream(fishes).forEach(System.out::println);
    }

    public static void printMap(Fish fish1, Fish fish2, Fish[][] map) {
        String fish1Name = fish1 == null ? "" : fish1.no + "";
        String fish2Name = fish2 == null ? "" : fish2.no + "";

        System.out.println("------fish move (" + fish1Name + " swap " + fish2Name + " --------");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (shark[0] == i && shark[1] == j) {
                    System.out.print("{S, " + dirCh[shark[2]] + "} ");
                    continue;
                }

                if (map[i][j] == null) {
                    System.out.print("{   }");
                    continue;
                }


                System.out.print("{" + map[i][j].no + ", " + dirCh[map[i][j].dir] + "} ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void swap(Fish pivot, Fish target, Fish[][] map, Fish[] fishes) {
        int pivotNo = pivot.no;
        int targetNo = target.no;


        map[target.row][target.col] = pivot;
        map[pivot.row][pivot.col] = target;

        int tempRow = pivot.row;
        int tempCol = pivot.col;

        pivot.row = target.row;
        pivot.col = target.col;
        fishes[pivotNo].row = target.row;
        fishes[pivotNo].col = target.col;

        target.row = tempRow;
        target.col = tempCol;
        fishes[targetNo].row = tempRow;
        fishes[targetNo].col = tempCol;
    }

    public static Fish[][] cloneMap(Fish[][] map) {
        Fish[][] cloneMap = new Fish[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cloneMap[i][j] = Fish.clone(map[i][j]);
            }
        }

        return cloneMap;
    }

    public static Fish[] cloneFish(Fish[] fishes) {
        Fish[] cloneFish = new Fish[17];
        for (int i = 0; i < 17; i++) {
            cloneFish[i] = Fish.clone(fishes[i]);
        }

        return cloneFish;
    }

    private static class Fish {
        int row;
        int col;
        int dir;
        int no;

        public Fish(int row, int col, int dir, int no) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.no = no;
        }

        public void rotate() {
            dir++;

            if (dir == 9) {
                dir = 1;
            }
        }

        public static Fish clone(Fish fish) {
            if (fish == null) {
                return null;
            }

            return new Fish(fish.row, fish.col, fish.dir, fish.no);
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "row=" + row +
                    ", col=" + col +
                    ", dir=" + dir +
                    ", no=" + no +
                    '}';
        }
    }
}
