package org.example._47week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SumOfTwoArray {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T;
    private static int[] A;
    private static int[] B;
    private static long answer = 0;

    private static Map<Integer, Integer> sumA;
    private static Map<Integer, Integer> sumB;

    public static void main(String[] args) throws IOException {
        input();

        sumA = makeSumMap(A);
        sumB = makeSumMap(B);

        for (int key : sumA.keySet()) {
            int cnt = sumB.getOrDefault(T - key, 0);
            answer += (long) cnt * sumA.get(key);
        }

        System.out.println(answer);
    }

    private static Map<Integer, Integer> makeSumMap(int[] a) {
        int[] sums = new int[a.length + 1];
        Map<Integer, Integer> sumMap = new HashMap<>();

        sums[0] = 0;
        sums[1] = a[0];
        for (int i = 2; i < a.length + 1; i++) {
            sums[i] = a[i - 1] + sums[i - 1];
        }

        for (int startIdx = 0; startIdx < a.length + 1; startIdx++) {
            for (int endIdx = startIdx + 1; endIdx < a.length + 1; endIdx++) {
                int sum = sums[endIdx] - sums[startIdx];
                sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
            }
        }

        return sumMap;
    }

    private static void input() throws IOException {
        T = Integer.parseInt(br.readLine());

        int N = Integer.parseInt(br.readLine());
        A = new int[N];
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int M = Integer.parseInt(br.readLine());
        B = new int[M];
        B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
