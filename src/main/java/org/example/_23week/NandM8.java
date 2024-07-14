package org.example._23week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class NandM8 {

    private static int n;
    private static int m;
    private static int[] numbers;
    private static final StringBuilder sb = new StringBuilder();
    private static final List<String> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);
        numbers = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .sorted()
            .toArray();

        dfs(new ArrayList<>(), 0, 0);

        for (String element : answer) {
            System.out.println(element);
        }
        System.out.println(answer.size());
    }

    private static void dfs(
        List<Integer> elements,
        int count,
        int node
    ) {
        if (count == m) {
            for (Integer element : elements) {
                sb.append(element)
                    .append(" ");
            }
            answer.add(sb.toString().trim());
            sb.setLength(0);
            return;
        }
        for (int index = node; index < numbers.length; index++) {
            elements.add(numbers[index]);
            dfs(elements, count + 1, index);
            elements.remove(elements.size() - 1);
        }
    }
}
