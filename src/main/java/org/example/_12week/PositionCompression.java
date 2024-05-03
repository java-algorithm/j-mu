package org.example._12week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PositionCompression {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        final int N = Integer.parseInt(br.readLine());

        final int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        final int[] sortedNumbers = Arrays.stream(numbers).sorted().toArray();

        //[1,2,3,4,2,3,4,5]
        // map
        // 1 -> 0
        // 2 -> 1
        // 3 -> 2
        // 4 -> 3
        // 5 -> 7
        int rank = 0;
        for (int i = 0; i < N; i++) {
            if (!map.containsKey(sortedNumbers[i])) {
                map.put(sortedNumbers[i], rank);
                rank++;
            }
        }

        // 참 쉽죠~?
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            final Integer value = map.get(numbers[i]);
            sb.append(value).append(" ");
        }

        System.out.println(sb);
    }
}
