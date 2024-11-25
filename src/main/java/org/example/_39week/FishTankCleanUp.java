package org.example._39week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class FishTankCleanUp {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[] dr = {0, 0, 1, -1};
    private static final int[] dc = {1, -1, 0, -0};
    private static int N;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        arr[0] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; ; i++) {
            int difference = foundMaxDifference();
            if (difference <= K) {
                System.out.println(i);
                break;
            }

            step();
        }
    }

    private static int foundMaxDifference() {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            max = Math.max(max, arr[0][i]);
            min = Math.min(min, arr[0][i]);
        }

        return max - min;
    }

    private static void step() {
        // 가장 물고기가 적은 어항에 물고기 주기.
        addFish();

        // 가장 왼쪽에 있는 어항을 그 어항의 오른쪽에 있는 어항 위에 올린다.
        moveMostLeftTank();

        // 2개 이상 쌓여있는 어항을 모두 90도 회전시켜 바닥에 있는 어항 위에 올려놓는다.
        moveStackedTank();

        // 물고기 수 조정
        adjustFishCount();

        // 다시 어항 일렬로 정렬
        alignFishTank();

        // n/2개를 공중부양하고 180도 회전시켜 올리기 x2
        moveHalfTank();
        moveHalfTank();

        // 물고기 수 조정
        adjustFishCount();

        // 다시 어항 일렬로 정렬
        alignFishTank();
    }

    private static void moveHalfTank() {
        int mostLeftPosition = 0;
        for (int i = 0; i < N; i++) {
            if (arr[0][i] != 0) {
                mostLeftPosition = i;
                break;
            }
        }

        int height = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i][mostLeftPosition] != 0) {
                height++;
            }
        }

        int mostRightPosition = N - 1;

        int mid = (mostLeftPosition + mostRightPosition + 1) / 2 - 1;
        for (int h = 1; h <= height; h++) {
            for (int j = 0; j <= mid - mostLeftPosition; j++) {
                arr[height + h - 1][mid + j + 1] = arr[height - h][mid - j];
                arr[height - h][mid - j] = 0;
            }
        }
    }

    private static void moveStackedTank() {
        while (true) {
            int mostLeftStackedPosition = 0;
            for (int i = 0; i < N; i++) {
                if (arr[0][i] != 0) {
                    mostLeftStackedPosition = i;
                    break;
                }
            }

            int height = 0;
            for (int i = 0; i < N; i++) {
                if (arr[i][mostLeftStackedPosition] != 0) {
                    height++;
                }
            }

            int mostRightStackedPosition = 0;
            for (int i = mostLeftStackedPosition; i < N; i++) {
                if (arr[1][i] != 0) {
                    mostRightStackedPosition = i;
                    continue;
                }

                break;
            }

            boolean isMovable = (N - mostRightStackedPosition - 1) >= height;
            if (!isMovable) {
                break;
            }

            // 실제로 90도 돌려서 옮기는 부분.
            for (int i = mostRightStackedPosition, j = 1; i >= mostLeftStackedPosition; i--, j++) {
                for (int d = 0; d < height; d++) {
                    arr[j][i + j + d] = arr[d][i];
                    arr[d][i] = 0;
                }
            }
        }
    }

    private static void addFish() {
        List<Integer> smallestPosition = new ArrayList<>();
        int smallest = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            if (smallest > arr[0][i]) {
                smallest = arr[0][i];
                smallestPosition.clear();
                smallestPosition.add(i);
            } else if (smallest == arr[0][i]) {
                smallestPosition.add(i);
            }
        }

        for (Integer idx : smallestPosition) {
            arr[0][idx]++;
        }
    }

    private static void moveMostLeftTank() {
        arr[1][1] = arr[0][0];
        arr[0][0] = 0;
    }

    private static void alignFishTank() {
        int mostLeftEmptyPosition = 0;

        int mostLeftStackedPosition = 0;
        for (int i = 0; i < N; i++) {
            if (arr[0][i] != 0) {
                mostLeftStackedPosition = i;
                break;
            }
        }

        int height = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i][mostLeftStackedPosition] != 0) {
                height++;
            }
        }

        int mostRightStackedPosition = 0;
        for (int i = mostLeftStackedPosition; i < N; i++) {
            if (arr[1][i] != 0) {
                mostRightStackedPosition = i;
                continue;
            }

            break;
        }

        for (int i = mostLeftStackedPosition; i <= mostRightStackedPosition; i++) {
            for (int j = 0; j < height; j++) {
                arr[0][mostLeftEmptyPosition++] = arr[j][i];
                arr[j][i] = 0;
            }
        }
    }

    private static void adjustFishCount() {
        int[][] temp = new int[N][N];

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (arr[row][col] == 0) {
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    int nextRow = row + dr[i];
                    int nextCol = col + dc[i];

                    if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) {
                        continue;
                    }

                    if (arr[nextRow][nextCol] == 0) {
                        continue;
                    }

                    int difference = arr[row][col] - arr[nextRow][nextCol];
                    if (difference >= 5) {
                        temp[row][col] -= difference / 5;
                        temp[nextRow][nextCol] += difference / 5;
                    }
                }
            }
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                temp[row][col] += arr[row][col];
            }
        }

        arr = temp;
    }
}
