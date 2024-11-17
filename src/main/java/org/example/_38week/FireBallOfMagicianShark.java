package org.example._38week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class FireBallOfMagicianShark {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static List<FireBall>[][] map;

    private static int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    private static int N;
    private static int M;
    private static int K;


    public static void main(String[] args) throws IOException {
        init();

        for (int i = 0; i < K; i++) {
            List<FireBall>[][] tempMap = initMap();

            move(tempMap);

            combineFireBall(tempMap);

            splitFireBalls(tempMap);

            map = tempMap;
        }

        int mass = calculateFireBallMass();
        System.out.println(mass);
    }

    private static void splitFireBalls(List<FireBall>[][] combinedFireballs) {
        for (int row = 1; row < N + 1; row++) {
            for (int col = 1; col < N + 1; col++) {
                List<FireBall> fireBalls = combinedFireballs[row][col];
                if (fireBalls.isEmpty()) {
                    continue;
                }

                FireBall fireBall = fireBalls.remove(0);
                if (fireBall.count == 0) { // 합쳐진 fireBall이 아니라면
                    combinedFireballs[row][col].add(fireBall);
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    int direction = fireBall.isAllMatchDirection ? 2 * i : 2 * i + 1;
                    FireBall newFireBall = new FireBall(fireBall.mass / 5, direction, fireBall.speed / fireBall.count);
                    combinedFireballs[row][col].add(newFireBall);
                }
            }
        }
    }

    private static void combineFireBall(List<FireBall>[][] tempMap) {
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                List<FireBall> fireBalls = tempMap[row][col];
                if (fireBalls.isEmpty() || fireBalls.size() == 1) {
                    continue;
                }

                int mass = 0;
                int speed = 0;
                boolean isAllMatchDirection = fireBalls.stream()
                        .map(fireBall -> fireBall.direction % 2 == 0)
                        .distinct()
                        .count() == 1;

                for (FireBall fireBall : fireBalls) {
                    mass += fireBall.mass;
                    speed += fireBall.speed;
                }

                // allMatch면 direction 0, 아니면 1
                tempMap[row][col] = new ArrayList<>();
                tempMap[row][col].add(new FireBall(mass, speed, isAllMatchDirection, fireBalls.size()));
            }
        }
    }

    private static void move(List<FireBall>[][] map) {
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                List<FireBall> fireBalls = FireBallOfMagicianShark.map[row][col];
                for (FireBall fireBall : fireBalls) {
                    int nextRow = move(row, dir[fireBall.direction][0] * fireBall.speed);
                    int nextCol = move(col, dir[fireBall.direction][1] * fireBall.speed);

                    map[nextRow][nextCol].add(fireBall);
                }
            }
        }
    }


    private static int move(int pos, int directionSize) {
        int nextPos = pos + directionSize;
        if (nextPos < 0) {
            return (nextPos % N) + N;
        } else {
            return (nextPos % N) == 0 ? N : (nextPos % N);
        }
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = initMap();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int mass = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            map[row][col].add(new FireBall(mass, direction, speed));
        }
    }

    private static List<FireBall>[][] initMap() {
        List<FireBall>[][] map = new List[N + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        return map;
    }

    private static int calculateFireBallMass() {
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                List<FireBall> fireBalls = map[i][j];
                for (FireBall fireBall : fireBalls) {
                    answer += fireBall.mass;
                }
            }
        }

        return answer;
    }

    private static class FireBall {
        int mass;
        int direction;
        int speed;

        boolean isAllMatchDirection;
        int count;

        public FireBall(int mass, int direction, int speed) {
            this.mass = mass;
            this.direction = direction;
            this.speed = speed;
        }

        public FireBall(int mass, int speed, boolean isAllMatchDirection, int count) {
            this.mass = mass;
            this.speed = speed;
            this.isAllMatchDirection = isAllMatchDirection;
            this.count = count;
        }
    }
}
