package org.example._26week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BabyShark {

    private static final int BABY_SHARK = 9;
    private static final List<Fish> fishes = new ArrayList<>();

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int[][] map;
    private static int[][] distances;
    private static int mapSize;
    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        mapSize = Integer.parseInt(br.readLine());
        map = new int[mapSize][mapSize];
        distances = new int[mapSize][mapSize];

        Shark babyShark = null;
        for (int row = 0; row < mapSize; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int col = 0; col < mapSize; col++) {
                int value = Integer.parseInt(st.nextToken());
                if (value == BABY_SHARK) {
                    babyShark = new Shark(row, col);
                } else if (value >= 1 && value <= 6) {
                    Fish fish = new Fish(row, col, value);
                    fishes.add(fish);
                }

                map[row][col] = value;
            }
        }

//        printFishes();
//        bfs(babyShark);
//        printMap(map);
//        printMap(distance);
//        System.out.println(babyShark);
        while (!fishes.isEmpty()) {
            Fish fish = getEatableFish(babyShark);
            if (fish == null) {
                break;
            }

            assert babyShark != null;
            babyShark.eat(fish);
//            printFishes();
//            System.out.println(babyShark);
        }

//        printFishes();
//        System.out.println(babyShark);
        System.out.println(babyShark.second);
    }

    private static void bfs(Shark babyShark) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{babyShark.row, babyShark.col, 0});
        boolean[][] visited = new boolean[mapSize][mapSize];
        distances = new int[mapSize][mapSize];
        visited[babyShark.row][babyShark.col] = true;

        while (!queue.isEmpty()) {
            int[] curPosition = queue.poll();
            int curRow = curPosition[0];
            int curCol = curPosition[1];
            int distance = curPosition[2];

            for (int i = 0; i < 4; i++) {
                int nextRow = curRow + dr[i];
                int nextCol = curCol + dc[i];

                if (nextRow < 0 || nextRow >= mapSize || nextCol < 0 || nextCol >= mapSize) {
                    continue;
                }

                if (visited[nextRow][nextCol]) {
                    continue;
                }

                if (map[nextRow][nextCol] > babyShark.size) {
                    continue;
                }

                visited[nextRow][nextCol] = true;
                distances[nextRow][nextCol] = distance + 1;
                queue.offer(new int[]{nextRow, nextCol, distance + 1});
            }
        }
    }

    private static void printFishes() {
//        fishes.forEach(System.out::println);
        System.out.println("----map----");
        printMap(map);


        System.out.println("-----distance-----");
        printMap(distances);
    }

    private static void printMap(int[][] map) {
        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                System.out.print(map[row][col] == BABY_SHARK ? "S " : map[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static Fish getEatableFish(Shark babyShark) {
        bfs(babyShark);

        List<Fish> smallFishes = fishes.stream()
                .filter(fish -> fish.size < babyShark.size)
                .filter(fish -> distances[fish.row][fish.col] > 0)
                .collect(Collectors.toList());

        if (smallFishes.isEmpty()) {
            return null;
        }

        smallFishes.sort((fish, other) -> {
            int fishDistance = distances[fish.row][fish.col];
            int otherFishDistance = distances[other.row][other.col];

            int distanceCompare = Integer.compare(fishDistance, otherFishDistance);
            if (distanceCompare != 0) {
                return distanceCompare;
            }

            int rowDistance = Integer.compare(fish.row, other.row);
            if (rowDistance != 0) {
                return rowDistance;
            }

            int colDistance = Integer.compare(fish.col, other.col);
            return colDistance;
        });

        return smallFishes.get(0);
    }

    private static class Shark {
        int row;
        int col;
        int size;
        int gauge;

        int second;

        public Shark(int row, int col) {
            this.row = row;
            this.col = col;
            this.size = 2;
            this.gauge = 0;
            this.second = 0;
        }

        @Override
        public String toString() {
            return "row=" + row +
                    ", col=" + col +
                    ", size=" + size +
                    ", gauge=" + gauge +
                    ", second=" + second;
        }

        private void eat(Fish fish) {
            this.gauge++;

            if (size == gauge) {
                size++;
                gauge = 0;
            }

            map[this.row][this.col] = 0;
            this.second += calculateDistance(fish);
            this.row = fish.row;
            this.col = fish.col;
            fishes.remove(fish);
            map[this.row][this.col] = BABY_SHARK;
        }

        public int calculateDistance(Fish fish) {
            return distances[fish.row][fish.col];
        }
    }

    private static class Fish {
        int row;
        int col;
        int size;

        public Fish(int row, int col, int size) {
            this.row = row;
            this.col = col;
            this.size = size;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Fish fish = (Fish) o;
            return row == fish.row && col == fish.col && size == fish.size;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col, size);
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "row=" + row +
                    ", col=" + col +
                    ", size=" + size +
                    '}';
        }
    }
}
