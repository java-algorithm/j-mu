package org.example._37week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HeaterSolution {

    public static class Node {
        int row;
        int col;
        int dir;
        int wind;

        public Node(int row, int col, int dir, int wind) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.wind = wind;
        }

        @Override
        public String toString() {
            return "Heater{" +
                    "row=" + row +
                    ", col=" + col +
                    ", dir=" + dir +
                    '}';
        }
    }

    public static class Wall {
        int row;
        int col;
        int t;

        public Wall(int row, int col, int t) {
            this.row = row;
            this.col = col;
            this.t = t;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Wall wall = (Wall) o;
            return row == wall.row && col == wall.col && t == wall.t;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col, t);
        }
    }

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int[][] map;
    private static boolean[][] targetRegions;
    private static List<Node> heaters = new ArrayList<>();
    private static Set<Wall> walls = new HashSet<>();
    private static int R;
    private static int C;
    private static int W;
    private static int targetTemparature;

    // X, 오, 왼, 위, 아
    private static final int RIGHT = 1;
    private static final int LEFT = 2;
    private static final int UP = 3;
    private static final int DOWN = 4;
    private static int[] dr = {0, 0, 0, -1, 1};
    private static int[] dc = {0, 1, -1, 0, 0};

    /*
        1. 2<= R,C <=20
            배열 전체 순회 : 400
            N^3까지도 허용될 듯. 시간복잡도는 넉넉함.
        2. 집에있는 모든 온풍기에서 바람이 나옴. => 바람 나오는 것을 구현하는 함수 필요. => int[][] winds 반환.
            2-1. winds와 map을 합쳐서 지도 다시 그리기.
        3. map에서 1행, R행, 1열, C열 다 내리기. (400)
        4. 조사하는 모든 칸의 온도가 k이상 되었는지 검사. (400)
    */
    public static void main(String[] args) throws IOException {
        init();
        for (int chocolate = 0; chocolate <= 100; ) {
            // 1. 바람이 나온다.
            int[][] winds = blowsWind();

            // 1-1. map 합치기.
            calculateTemperature(winds);

            // 2. 온도 조정
            adjustTemperature();

            // 3. 온도 내리기.
            coolDown();

            // 4. 초콜렛 먹기.
            chocolate++;

            // 5. 완성됐나?
            boolean completed = checkCompleted();
            if (completed) {
                System.out.println(chocolate);
                return;
            }
        }
        System.out.println(101);
    }

    private static void adjustTemperature() {
        int[][] temp = new int[R][C];
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (map[row][col] < 4) {
                    continue;
                }

                for (int dir = 1; dir <= 4; dir++) {
                    int nextRow = row + dr[dir];
                    int nextCol = col + dc[dir];

                    if (!isInBound(nextRow, nextCol)) {
                        continue;
                    }

                    if (map[nextRow][nextCol] >= map[row][col]) {
                        continue;
                    }

                    if (dir == RIGHT && walls.contains(new Wall(row, col, 1))) {
                        continue;
                    }

                    if (dir == LEFT && walls.contains(new Wall(row, col - 1, 1))) {
                        continue;
                    }

                    if (dir == UP && walls.contains(new Wall(row, col, 0))) {
                        continue;
                    }

                    if (dir == DOWN && walls.contains(new Wall(row + 1, col, 0))) {
                        continue;
                    }

                    temp[nextRow][nextCol] += (map[row][col] - map[nextRow][nextCol]) / 4;
                    temp[row][col] -= (map[row][col] - map[nextRow][nextCol]) / 4;
                }
            }
        }

        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                map[row][col] += temp[row][col];
            }
        }
    }

    public static int[][] blowsWind() {
        int[][] winds = new int[R][C];
        for (Node heater : heaters) {
            Queue<Node> queue = new LinkedList<>();
            int startingPointRow = heater.row + dr[heater.dir];
            int startingPointCol = heater.col + dc[heater.dir];
            queue.add(new Node(startingPointRow, startingPointCol, heater.dir, heater.wind));

            winds[startingPointRow][startingPointCol] += 5;
            boolean[][] visited = new boolean[R][C];
            visited[startingPointRow][startingPointCol] = true;

            while (!queue.isEmpty()) {
                Node poll = queue.poll();
                if (poll.wind == 1) {
                    continue;
                }

                int nextRow;
                int nextCol;
                switch (poll.dir) {
                    case RIGHT:
                        nextRow = poll.row;
                        nextCol = poll.col + 1;
                        if (isInBound(nextRow, nextCol) &&
                                !visited[nextRow][nextCol] &&
                                !walls.contains(new Wall(poll.row, poll.col, 1))
                        ) {
                            queue.add(new Node(nextRow, nextCol, poll.dir, poll.wind - 1));
                            winds[nextRow][nextCol] += poll.wind - 1;
                            visited[nextRow][nextCol] = true;
                        }

                        nextRow = poll.row - 1;
                        nextCol = poll.col + 1;
                        if (
                                isInBound(nextRow, nextCol) &&
                                        !visited[nextRow][nextCol] &&
                                        !walls.contains(new Wall(poll.row, poll.col, 0)) &&
                                        !walls.contains(new Wall(poll.row - 1, poll.col, 1))
                        ) {
                            queue.add(new Node(nextRow, nextCol, poll.dir, poll.wind - 1));
                            winds[nextRow][nextCol] += poll.wind - 1;
                            visited[nextRow][nextCol] = true;
                        }

                        nextRow = poll.row + 1;
                        nextCol = poll.col + 1;
                        if (
                                isInBound(nextRow, nextCol) &&
                                        !visited[nextRow][nextCol] &&
                                        !walls.contains(new Wall(poll.row + 1, poll.col, 0)) &&
                                        !walls.contains(new Wall(poll.row + 1, poll.col, 1))
                        ) {
                            queue.add(new Node(nextRow, nextCol, poll.dir, poll.wind - 1));
                            winds[nextRow][nextCol] += poll.wind - 1;
                            visited[nextRow][nextCol] = true;
                        }
                        break;
                    case LEFT:
                        nextRow = poll.row;
                        nextCol = poll.col - 1;
                        if (isInBound(nextRow, nextCol) &&
                                !visited[nextRow][nextCol] &&
                                !walls.contains(new Wall(poll.row, poll.col - 1, 1))
                        ) {
                            queue.add(new Node(nextRow, nextCol, poll.dir, poll.wind - 1));
                            winds[nextRow][nextCol] += poll.wind - 1;
                            visited[nextRow][nextCol] = true;
                        }

                        nextRow = poll.row - 1;
                        nextCol = poll.col - 1;
                        if (
                                isInBound(nextRow, nextCol) &&
                                        !visited[nextRow][nextCol] &&
                                        !walls.contains(new Wall(poll.row, poll.col, 0)) &&
                                        !walls.contains(new Wall(poll.row - 1, poll.col - 1, 1))
                        ) {
                            queue.add(new Node(nextRow, nextCol, poll.dir, poll.wind - 1));
                            winds[nextRow][nextCol] += poll.wind - 1;
                            visited[nextRow][nextCol] = true;
                        }

                        nextRow = poll.row + 1;
                        nextCol = poll.col - 1;
                        if (
                                isInBound(nextRow, nextCol) &&
                                        !visited[nextRow][nextCol] &&
                                        !walls.contains(new Wall(poll.row + 1, poll.col, 0)) &&
                                        !walls.contains(new Wall(poll.row + 1, poll.col - 1, 1))
                        ) {
                            queue.add(new Node(nextRow, nextCol, poll.dir, poll.wind - 1));
                            winds[nextRow][nextCol] += poll.wind - 1;
                            visited[nextRow][nextCol] = true;
                        }
                        break;
                    case UP:
                        nextRow = poll.row - 1;
                        nextCol = poll.col;
                        if (isInBound(nextRow, nextCol) &&
                                !visited[nextRow][nextCol] &&
                                !walls.contains(new Wall(poll.row, poll.col, 0))
                        ) {
                            queue.add(new Node(nextRow, nextCol, poll.dir, poll.wind - 1));
                            winds[nextRow][nextCol] += poll.wind - 1;
                            visited[nextRow][nextCol] = true;
                        }

                        nextRow = poll.row - 1;
                        nextCol = poll.col - 1;
                        if (
                                isInBound(nextRow, nextCol) &&
                                        !visited[nextRow][nextCol] &&
                                        !walls.contains(new Wall(poll.row, poll.col - 1, 1)) &&
                                        !walls.contains(new Wall(poll.row, poll.col - 1, 0))
                        ) {
                            queue.add(new Node(nextRow, nextCol, poll.dir, poll.wind - 1));
                            winds[nextRow][nextCol] += poll.wind - 1;
                            visited[nextRow][nextCol] = true;
                        }

                        nextRow = poll.row - 1;
                        nextCol = poll.col + 1;
                        if (
                                isInBound(nextRow, nextCol) &&
                                        !visited[nextRow][nextCol] &&
                                        !walls.contains(new Wall(poll.row, poll.col, 1)) &&
                                        !walls.contains(new Wall(poll.row, poll.col + 1, 0))
                        ) {
                            queue.add(new Node(nextRow, nextCol, poll.dir, poll.wind - 1));
                            winds[nextRow][nextCol] += poll.wind - 1;
                            visited[nextRow][nextCol] = true;
                        }
                        break;
                    case DOWN:
                        nextRow = poll.row + 1;
                        nextCol = poll.col;
                        if (isInBound(nextRow, nextCol) &&
                                !visited[nextRow][nextCol] &&
                                !walls.contains(new Wall(poll.row + 1, poll.col, 0))
                        ) {
                            queue.add(new Node(nextRow, nextCol, poll.dir, poll.wind - 1));
                            winds[nextRow][nextCol] += poll.wind - 1;
                            visited[nextRow][nextCol] = true;
                        }

                        nextRow = poll.row + 1;
                        nextCol = poll.col - 1;
                        if (
                                isInBound(nextRow, nextCol) &&
                                        !visited[nextRow][nextCol] &&
                                        !walls.contains(new Wall(poll.row, poll.col - 1, 1)) &&
                                        !walls.contains(new Wall(poll.row + 1, poll.col - 1, 0))
                        ) {
                            queue.add(new Node(nextRow, poll.col - 1, poll.dir, poll.wind - 1));
                            winds[nextRow][poll.col - 1] += poll.wind - 1;
                            visited[nextRow][nextCol] = true;
                        }

                        nextRow = poll.row + 1;
                        nextCol = poll.col + 1;
                        if (
                                isInBound(nextRow, nextCol) &&
                                        !visited[nextRow][nextCol] &&
                                        !walls.contains(new Wall(poll.row, poll.col, 1)) &&
                                        !walls.contains(new Wall(poll.row + 1, poll.col + 1, 0))
                        ) {
                            queue.add(new Node(nextRow, nextCol, poll.dir, poll.wind - 1));
                            winds[nextRow][nextCol] += poll.wind - 1;
                            visited[nextRow][nextCol] = true;
                        }

                        break;
                }
            }
        }

        return winds;
    }

    private static boolean isInBound(int row, int col) {
        return row >= 0 && row < R && col >= 0 && col < C;
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        targetTemparature = Integer.parseInt(st.nextToken());

        targetRegions = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (value == 0) {
                    continue;
                }

                if (value == 5) {
                    targetRegions[i][j] = true;
                    continue;
                }

                heaters.add(new Node(i, j, value, 5));
            }
        }

        W = Integer.parseInt(br.readLine());
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            walls.add(new Wall(row - 1, col - 1, t));
        }
    }

    private static void calculateTemperature(int[][] winds) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] += winds[i][j];
            }
        }
    }

    // 3. map에서 1행, R행, 1열, C열 다 내리기. (400)
    public static void coolDown() {
        for (int row = 0; row < R; row++) {
            if (map[row][0] >= 1) {
                map[row][0]--;
            }

            if (map[row][C - 1] >= 1) {
                map[row][C - 1]--;
            }
        }

        for (int col = 1; col < C - 1; col++) {
            if (map[0][col] >= 1) {
                map[0][col]--;
            }

            if (map[R - 1][col] >= 1) {
                map[R - 1][col]--;
            }
        }
    }

    // 4. 조사하는 모든 칸의 온도가 k이상 되었는지 검사. (400)
    public static boolean checkCompleted() {
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (!targetRegions[row][col]) {
                    continue;
                }

                if (map[row][col] < targetTemparature) {
                    return false;
                }
            }
        }

        return true;
    }

}
