package org.example._51week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Players {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final int VALUE = 0;
    private static final int CLASS = 1;

    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int classCnt = Integer.parseInt(st.nextToken());
        int studentCnt = Integer.parseInt(st.nextToken());

        int[][] classes = new int[classCnt][studentCnt];
        int[] sp = new int[classCnt];

        for (int i = 0; i < classCnt; i++) {
            classes[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(classes[i]);
        }

        int maxValue = 0;
        PriorityQueue<int[]> minimums = new PriorityQueue<>((e1, e2) -> Integer.compare(e1[VALUE], e2[VALUE]));
        for (int classNo = 0; classNo < classCnt; classNo++) {
            int value = classes[classNo][0];
            minimums.add(new int[]{value, classNo});
            maxValue = Math.max(maxValue, value);
        }

        while (true) {
            int[] min = minimums.poll();
            answer = Math.min(answer, maxValue - min[VALUE]);

            int targetClass = min[CLASS];
            if (++sp[targetClass] >= studentCnt) {
                System.out.println(answer);
                return;
            }

            int nextMin = classes[targetClass][sp[targetClass]];
            maxValue = Math.max(maxValue, nextMin);
            minimums.add(new int[]{nextMin, targetClass});
        }
    }
}
