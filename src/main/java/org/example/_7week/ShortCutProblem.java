package org.example._7week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ShortCutProblem {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int shortCutCount = Integer.parseInt(st.nextToken());
        final int endPoint = Integer.parseInt(st.nextToken());

        ArrayList<ShortCut> shortCuts = new ArrayList<>();
        for (int i = 0; i < shortCutCount; i++) {
            st = new StringTokenizer(br.readLine());
            final int from = Integer.parseInt(st.nextToken());
            final int to = Integer.parseInt(st.nextToken());
            final int cost = Integer.parseInt(st.nextToken());

            if (to - from <= cost) continue;
            if (to > endPoint) continue;

            shortCuts.add(new ShortCut(from, to, cost));
        }


        // 지름길을 앞에있는 순으로 정렬하기.
        shortCuts.sort((o1, o2) -> {
            if (o1.from == o2.from) {
                return Integer.compare(o1.to, o2.to);
            }

            return Integer.compare(o1.from, o2.from);
        });

        int curPosition = 0;
        int[] dp = new int[endPoint + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        int shortCutIndex = 0;

        while (curPosition < endPoint) {
            if (shortCutIndex < shortCuts.size()) {
                final ShortCut shortCut = shortCuts.get(shortCutIndex);
                if (curPosition == shortCut.from) {
                    dp[shortCut.to] = Math.min(dp[shortCut.to], dp[curPosition] + shortCut.cost);
                    shortCutIndex++;
                }else{
                    dp[curPosition + 1] = Math.min(dp[curPosition + 1], dp[curPosition] + 1);
                    curPosition++;
                }
            } else {
                dp[curPosition + 1] = Math.min(dp[curPosition + 1], dp[curPosition] + 1);
                curPosition++;
            }
        }

        System.out.println(dp[endPoint]);
    }

    private static class ShortCut {
        private final int from;
        private final int to;
        private final int cost;

        public ShortCut(final int from, final int to, final int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
