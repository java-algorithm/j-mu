package org.example._2week;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Statics {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        int[] inputs = new int[N];
        for (int i = 0; i < N; i++) {
            inputs[i] = scanner.nextInt();
        }

        // 1 산술평균
        int sum = Arrays.stream(inputs).sum();
        System.out.println(Math.round(sum / (double) N));

        // 2. 중앙값
        int[] sortedInputs = Arrays.stream(inputs).sorted().toArray();
        int sortedInputsLength = sortedInputs.length;
        System.out.println(sortedInputs[sortedInputsLength / 2]);

        // 3. 최빈값
        HashMap<Integer, Integer> frequency = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int key = sortedInputs[i];

            Integer value = frequency.putIfAbsent(key, 1);
            if (value != null) {
                frequency.put(key, value + 1);
            }
        }

        Comparator<Entry<Integer, Integer>> comparator = new Comparator<Entry<Integer, Integer>>() {
            @Override
            public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
                int compare = -Integer.compare(o1.getValue(), o2.getValue());
                if (compare == 0) {
                    return Integer.compare(o1.getKey(), o2.getKey());
                }
                return compare;
            }
        };

        List<Entry<Integer, Integer>> sortedFrequency
            = frequency.entrySet().stream()
            .sorted(comparator)
            .collect(Collectors.toList());
        int size = sortedFrequency.size();

        if (sortedFrequency.size() == 1) {
            System.out.println(sortedFrequency.get(0).getKey());
        } else {
            Integer value1 = sortedFrequency.get(0).getValue();
            Integer value2 = sortedFrequency.get(1).getValue();
            if (value1 > value2) {
                System.out.println(sortedFrequency.get(0).getKey());
            } else {
                System.out.println(sortedFrequency.get(1).getKey());
            }
        }

        // 4. 범위
        int maxValue = sortedInputs[sortedInputsLength - 1];
        int minValue = sortedInputs[0];
        System.out.println(maxValue - minValue);
    }
}
