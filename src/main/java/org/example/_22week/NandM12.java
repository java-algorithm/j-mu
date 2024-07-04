package org.example._22week;

import java.util.*;
import java.util.stream.Collectors;

public class NandM12 {

    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();
        final int M = sc.nextInt();

        Set<Integer> numbersSet = new HashSet<>();
        for (int i = 0; i < N; i++) {
            int number = sc.nextInt();
            numbersSet.add(number);
        }

        List<Integer> numbers = new ArrayList<>(numbersSet);
        Collections.sort(numbers);

        dfs(numbers, M, 0, new ArrayList<>());
    }

    public static void dfs(List<Integer> numbers, int targetLength, int index, List<Integer> values) {
        if (values.size() == targetLength) {
            for (final Integer value : values) {
                System.out.print(value+" ");
            }
            System.out.println();
            return;
        }

        for (int i = index; i < numbers.size(); i++) {
            values.add(numbers.get(i));
            dfs(numbers, targetLength, i, values);
            values.remove(values.size() - 1);
        }
    }
}
