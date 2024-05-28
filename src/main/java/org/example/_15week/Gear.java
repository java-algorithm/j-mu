package org.example._15week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Gear {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final int CLOCKWISE = 1;
    private static final int COUNTER_CLOCKWISE = -1;

    private static final int RIGHT = 2;
    private static final int LEFT = 6;
    private static final int TOP = 0;

    private static final int N = 0;
    private static final int S = 1;

    public static void main(String[] args) throws IOException {
        int[][] gears = new int[5][8];

        for (int i = 1; i < 5; i++) {
            gears[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        final int rotationsCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < rotationsCount; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            final int gearNumber = Integer.parseInt(st.nextToken());
            final int rotationDirection = Integer.parseInt(st.nextToken());

            int leftPropagationDirection = rotationDirection;
            int rightPropagationDirection = rotationDirection;

            // 왼쪽으로
            List<Integer> leftRotatable = new ArrayList<>();
            for (int j = gearNumber - 1; j >= 1; j--) {
                if (gears[j][RIGHT] == gears[j + 1][LEFT]) {
                    break;
                }

                leftRotatable.add(j);
            }

            // 오른쪽으로
            List<Integer> rightRotatable = new ArrayList<>();
            for (int j = gearNumber + 1; j <= 4; j++) {
                if (gears[j - 1][RIGHT] == gears[j][LEFT]) {
                    break;
                }

                rightRotatable.add(j);
            }

            for (int j = 0; j < leftRotatable.size(); j++) {
                final Integer rotatableGearNum = leftRotatable.get(j);
                if (leftPropagationDirection == CLOCKWISE) {
                    gears[rotatableGearNum] = counterClockwiseRotation(gears[rotatableGearNum]);
                } else {
                    gears[rotatableGearNum] = clockwiseRotation(gears[rotatableGearNum]);
                }

                leftPropagationDirection *= -1;
            }


            for (int j = 0; j < rightRotatable.size(); j++) {
                final Integer rotatableGearNum = rightRotatable.get(j);
                if (rightPropagationDirection == CLOCKWISE) {
                    gears[rotatableGearNum] = counterClockwiseRotation(gears[rotatableGearNum]);
                } else {
                    gears[rotatableGearNum] = clockwiseRotation(gears[rotatableGearNum]);
                }
                rightPropagationDirection *= -1;
            }

            if (rotationDirection == CLOCKWISE) {
                gears[gearNumber] = clockwiseRotation(gears[gearNumber]);
            } else {
                gears[gearNumber] = counterClockwiseRotation(gears[gearNumber]);
            }
        }

        int score = 0;
        for (int i = 1; i <= 4; i++) {
            if (gears[i][TOP] == S) {
                score += (int) Math.pow(2, i - 1);
            }
        }
        System.out.println(score);
    }

    private static int[] clockwiseRotation(final int[] gear) {
        int[] newGear = new int[8];
        newGear[0] = gear[7];
        for (int i = 0; i < 7; i++) {
            newGear[i + 1] = gear[i];
        }

        return newGear;
    }

    private static int[] counterClockwiseRotation(final int[] gear) {
        int[] newGear = new int[8];
        newGear[7] = gear[0];
        for (int i = 1; i < 8; i++) {
            newGear[i - 1] = gear[i];
        }

        return newGear;
    }
}
