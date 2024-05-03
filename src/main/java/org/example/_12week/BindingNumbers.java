package org.example._12week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Function;

public class BindingNumbers {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        final int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> plusNumbers = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minusNumbers = new PriorityQueue<>();
        int hasZero = 0;

        for (int i = 0; i < N; i++) {
            final int value = Integer.parseInt(br.readLine());
            if (value > 0) {
                plusNumbers.offer(value);
            } else if (value < 0) {
                minusNumbers.offer(value);
            } else {
                hasZero++;
            }
        }

        final List<Integer> results = new ArrayList<>();
        // plus부터 처리하기.
        while (!plusNumbers.isEmpty()) {
            if (plusNumbers.size() >= 2) {
                final Integer value1 = plusNumbers.poll();
                final Integer value2 = plusNumbers.poll();
                if (value1 == 1 || value2 == 1) {
                    results.add(value1);
                    results.add(value2);
                } else {
                    results.add(value1 * value2);
                }
            } else {
                final Integer value = plusNumbers.poll();
                results.add(value);
            }
        }

        // minus처리하기.
        while (!minusNumbers.isEmpty()) {
            if (minusNumbers.size() >= 2) {
                final Integer value1 = minusNumbers.poll();
                final Integer value2 = minusNumbers.poll();

                results.add(value1 * value2);
            } else {
                final Integer value = minusNumbers.poll();
                if (hasZero > 0) {
                    hasZero--;
                    continue;
                }
                results.add(value);
            }
        }

        // result i
        final int sum = results.stream().mapToInt(i -> i).sum();
        System.out.println(sum);
    }
}
