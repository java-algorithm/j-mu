package org.example._16week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SmallWorld {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int usersCount = Integer.parseInt(st.nextToken());
        final int relationsCount = Integer.parseInt(st.nextToken());

        int[][] relations = new int[usersCount + 1][usersCount + 1];

        for (int i = 0; i < relationsCount; i++) {
            st = new StringTokenizer(br.readLine());
            final int user1 = Integer.parseInt(st.nextToken());
            final int user2 = Integer.parseInt(st.nextToken());

            relations[user1][user2] = 1;
            relations[user2][user1] = 1;
        }

        // 친구 관계 초기화 - 플로이드 워셜
        for (int i = 1; i < usersCount + 1; i++) {
            for (int j = 1; j < usersCount + 1; j++) {
                for (int k = 1; k < usersCount + 1; k++) {
                    if (relations[j][i] == 0 || relations[i][k] == 0) {
                        continue;
                    }

                    if (j == k) {
                        continue;
                    }

                    if (relations[j][k] == 0 || relations[j][k] > relations[j][i] + relations[i][k]) {
                        relations[j][k] = relations[j][i] + relations[i][k];
                    }
                }
            }
        }

        boolean isSmall = true;
        for (int i = 1; i < usersCount + 1; i++) {
            for (int j = 1; j < usersCount + 1; j++) {
                if (i == j) {
                    continue;
                }

                if (relations[i][j] > 6 || relations[i][j] == 0) {
                    isSmall = false;
                    break;
                }
            }

            if (!isSmall) {
                break;
            }
        }

        System.out.println(isSmall ? "Small World!" : "Big World!");
    }
}
