package org.example._40week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SharkElementSchool {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer,int[]> likeStudentsMap;
    private static int[][] likeStudents;
    private static boolean[][] positioned;
    private static int[][] positions;
    private static int[][] scores;
    private static int N;
    private static int studentCount;

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        init();
        for (int i = 0; i < studentCount; i++) {
            int currentStudent = likeStudents[i][0];
            int maxValue = scanPositions(currentStudent);
            seat(maxValue, currentStudent);
        }

        int answer = calculateSatisfaction();
        System.out.println(answer);
    }

    private static int scanPositions(int student) {
        scores = new int[N][N];

        int maxValue = Integer.MIN_VALUE;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (positioned[row][col]) {
                    continue;
                }

                int value = 0;
                for (int i = 0; i < 4; i++) {
                    int nextRow = row + dr[i];
                    int nextCol = col + dc[i];

                    if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) {
                        continue;
                    }

                    boolean like = isLike(student, positions[nextRow][nextCol]);
                    if (like) {
                        value+=10;
                    }

                    boolean hasStudent = positioned[nextRow][nextCol];
                    if (!hasStudent) {
                        value+=1;
                    }
                }

                scores[row][col] = value;
                maxValue = Math.max(maxValue, value);
            }
        }

        return maxValue;
    }

    private static boolean isLike(int student, int likeStudent) {
        int[] likeStudents = likeStudentsMap.get(student);

        for (int i = 1; i <= 4; i++) {
            if (likeStudents[i] == likeStudent) {
                return true;
            }
        }

        return false;
    }

    private static void seat(int maxValue, int student) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (scores[row][col] == maxValue && !positioned[row][col]) {
                    positions[row][col] = student;
                    positioned[row][col] = true;
                    return;
                }
            }
        }
    }

    private static int calculateSatisfaction() {
        int answer = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                int student = positions[row][col];

                int value = 0;
                for (int i = 0; i < 4; i++) {
                    int nextRow = row + dr[i];
                    int nextCol = col + dc[i];

                    if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) {
                        continue;
                    }

                    boolean like = isLike(student, positions[nextRow][nextCol]);
                    if (like) {
                        value++;
                    }
                }

                if (value == 1) {
                    answer += 1;
                } else if (value == 2) {
                    answer += 10;
                } else if (value == 3) {
                    answer += 100;
                } else if (value == 4) {
                    answer += 1000;
                }
            }
        }

        return answer;
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        studentCount = N * N;
        // 1차: 입력 순서, 2차 본인, 좋아하는사람4명
        likeStudents = new int[studentCount + 1][5];
        positions = new int[N][N];
        positioned = new boolean[N][N];
        likeStudentsMap = new HashMap<>();

        for (int i = 0; i < studentCount; i++) {
            likeStudents[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            likeStudentsMap.put(likeStudents[i][0], likeStudents[i]);
        }
    }
}
