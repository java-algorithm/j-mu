package org.example._16week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KebinBacon {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int usersCount = Integer.parseInt(st.nextToken());
        final int relationsCount = Integer.parseInt(st.nextToken());

        int[][] relations = new int[usersCount + 1][usersCount + 1];

        // 최초 친구 관계 초기화
        for (int i = 0; i < relationsCount; i++) {
            st = new StringTokenizer(br.readLine());

            final int user1 = Integer.parseInt(st.nextToken());
            final int user2 = Integer.parseInt(st.nextToken());

            relations[user1][user2] = 1;
            relations[user2][user1] = 1;
        }

        // 친구관계 갱신 - 플로이드 워셜
        for (int i = 1; i <= usersCount; i++) {
            for (int j = 1; j <= usersCount; j++) {
                for (int k = 1; k <= usersCount; k++) {
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

        int minScore = Integer.MAX_VALUE;
        int[] kebinScores = new int[usersCount + 1];
        for (int i = 1; i <= usersCount; i++) {
            kebinScores[i] = Arrays.stream(relations[i]).sum();
            if (minScore > kebinScores[i]) {
                minScore = kebinScores[i];
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int userNo = 1; userNo <= usersCount; userNo++) {
            if (minScore == kebinScores[userNo] && answer > userNo) {
                answer = userNo;
            }
        }

        System.out.println(answer);
    }
}
