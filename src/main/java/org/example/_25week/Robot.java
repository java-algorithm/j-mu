package org.example._25week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Robot {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int exitCondition = Integer.parseInt(st.nextToken());

        int conveyorSize = N * 2 + 1;
        final int[] conveyorBelts = new int[conveyorSize];
        final boolean[] robots = new boolean[conveyorSize];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 2 * N; i++) {
            conveyorBelts[i] = Integer.parseInt(st.nextToken());
        }

        int zeroCount = 0;
        int step = 0;

        while (true) {
            // 0. 단계 시작!
            step++;

            // 1. 벨트가 각 칸위에 있는 로봇과 함께 한 칸 회전한다.
            conveyorBelts[0] = conveyorBelts[2 * N];
            for (int i = 2 * N - 1; i >= 0; i--) {
                conveyorBelts[i + 1] = conveyorBelts[i];
                robots[i + 1] = robots[i];
            }
            robots[N] = false;

            // 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다.
            for (int i = N; i > 0; i--) {
                if (!robots[i]) {
                    continue;
                }

                if (robots[i + 1]) {
                    continue;
                }

                if (conveyorBelts[i + 1] < 1) {
                    continue;
                }

                conveyorBelts[i + 1]--;
                if (conveyorBelts[i + 1] == 0) {
                    zeroCount++;
                }

                robots[i + 1] = true;
                robots[i] = false;
            }

            // 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
            if (conveyorBelts[1] > 0) {
                robots[1] = true;
                conveyorBelts[1]--;
                if (conveyorBelts[1] == 0) {
                    zeroCount++;
                }
            }

            // 4. 내구도가 0인 칸의 개수가 K개 이상이면 과정을 종료한다.
            if (zeroCount >= exitCondition) {
                break;
            }
        }

        System.out.println(step);
    }
}
