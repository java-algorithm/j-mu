package org.example._36week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AdultShark {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final int[] dr = {0, -1, 1, 0, 0};
    private static final int[] dc = {0, 0, 0, -1, 1};
    private static Shark[] sharks;
    private static int[][] map;
    private static Smell[][] smells;
    private static int smellTime;
    private static int mapSize;
    private static int sharkCount;


    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        mapSize = Integer.parseInt(st.nextToken());
        sharkCount = Integer.parseInt(st.nextToken());
        smellTime = Integer.parseInt(st.nextToken());

        sharks = new Shark[sharkCount + 1];
        map = new int[mapSize][mapSize];
        smells = new Smell[mapSize][mapSize];

        //map 초기화.
        initMap();

        for (int currentTime = 1; currentTime <= 1000; currentTime++) {
            printMap();
            moveShark(currentTime);

            if (isGameEnd()) {
                System.out.println(currentTime);
                return;
            }
        }

        System.out.println(-1);
    }

    private static void printMap() {
        System.out.println("---------   map ----------");
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                System.out.print((map[i][j] == Integer.MAX_VALUE ? 0 : map[i][j]) + " ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("---------   smell ----------");
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                System.out.print((smells[i][j] == null ? 0 : smells[i][j].time) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void moveShark(int currentTime) {
        int[][] tempMap = new int[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            Arrays.fill(tempMap[i], Integer.MAX_VALUE);
        }

        for (int no = 1; no <= sharkCount; no++) {
            if (sharks[no] == null) {
                continue;
            }

            //1. 냄새가 없는 빈칸 찾기.
            int[] nextPosition = findNoSmellCell(no, currentTime);
            //2. 냄새가 없는 빈칸이 없으면 자기 냄새 칸 찾기.
            if (nextPosition == null) {
                nextPosition = findMySmellCell(no, currentTime);
            }

            int nextRow = nextPosition[0];
            int nextCol = nextPosition[1];
            int nextDir = nextPosition[2];

            // 만약 찾은 칸에 자기보다 낮은 no의 상어가 존재하면 탈락.
            // 이를 위해서 새로운 맵을 매번 생성 해주는 것이 좋을 듯.
            if (tempMap[nextRow][nextCol] < no) {
                sharks[no] = null;
                continue;
            }

            //3. 찾은 칸으로 이동.
            tempMap[nextRow][nextCol] = no;
            sharks[no].row = nextRow;
            sharks[no].col = nextCol;
            sharks[no].dir = nextDir;
            smells[nextRow][nextCol] = new Smell(no, currentTime);
        }

        map = tempMap;
    }

    private static boolean isGameEnd() {
        for (int i = 2; i <= sharkCount; i++) {
            if (sharks[i] != null) {
                return false;
            }
        }

        return true;
    }

    private static int[] findMySmellCell(int no, int currentTime) {
        Shark shark = sharks[no];
        int[] currentPriority = shark.getCurrentPriority();
        for (int i = 0; i < 4; i++) {
            int nextRow = shark.row + dr[currentPriority[i]];
            int nextCol = shark.col + dc[currentPriority[i]];

            if (nextRow < 0 || nextRow >= mapSize || nextCol < 0 || nextCol >= mapSize) {
                continue;
            }

            Smell smell = smells[nextRow][nextCol];
            if (smell.no == no && smell.time + smellTime >= currentTime || smell.time == currentTime) {
                return new int[]{nextRow, nextCol, currentPriority[i]};
            }
        }

        return null;
    }

    private static int[] findNoSmellCell(int no, int currentTime) {
        Shark shark = sharks[no];
        int[] currentPriority = shark.getCurrentPriority();
        for (int i = 0; i < 4; i++) {
            int nextRow = shark.row + dr[currentPriority[i]];
            int nextCol = shark.col + dc[currentPriority[i]];

            if (nextRow < 0 || nextRow >= mapSize || nextCol < 0 || nextCol >= mapSize) {
                continue;
            }

            Smell smell = smells[nextRow][nextCol];
            if (smell == null || smell.time + smellTime >= currentTime || smell.time == currentTime) {
                return new int[]{nextRow, nextCol, currentPriority[i]};
            }
        }

        return null;
    }

    private static void initMap() throws IOException {
        StringTokenizer st;
        for (int row = 0; row < mapSize; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < mapSize; col++) {
                int no = Integer.parseInt(st.nextToken());
                map[row][col] = no;

                if (no != 0) {
                    sharks[no] = new Shark(no, row, col);
                    smells[row][col] = new Smell(no, 0);
                }
            }
        }

        // 상어 현재 방향 초기화.
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= sharkCount; i++) {
            sharks[i].dir = Integer.parseInt(st.nextToken());
        }

        // 우선순위 초기화.
        for (int i = 1; i <= sharkCount; i++) {
            sharks[i].priority[1] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            sharks[i].priority[2] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            sharks[i].priority[3] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            sharks[i].priority[4] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
    }

    static class Shark {
        int no;
        int row;
        int col;
        int dir;
        int[][] priority;

        public Shark(int no, int row, int col) {
            this.no = no;
            this.row = row;
            this.col = col;
            this.priority = new int[5][4];
        }

        public int[] getCurrentPriority() {
            return priority[dir];
        }
    }

    static class Smell {
        int no;
        int time;

        public Smell(int no, int time) {
            this.no = no;
            this.time = time;
        }
    }
}
