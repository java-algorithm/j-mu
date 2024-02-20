package org.example._3week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Bonus {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = inputs[0];
        int M = inputs[1];
        int V = inputs[2];

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        boolean[] visited = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            int[] vertexs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph.get(vertexs[0]).add(vertexs[1]);
            graph.get(vertexs[1]).add(vertexs[0]);
        }

        Stack<Integer> stack = new Stack<>();
        stack.add(V);

        while (!stack.isEmpty()) {
            Integer target = stack.pop();
            if (visited[target]) {
                continue;
            }

            visited[target] = true;
            sb.append(target).append(" ");

            List<Integer> adjacents = graph.get(target).stream().sorted().collect(Collectors.toList());
            int size = adjacents.size();

            for (int i = size - 1; i >= 0; i--) {
                Integer adjacent = adjacents.get(i);

                if (!visited[adjacent]) {
                    stack.push(adjacent);
                }
            }
        }

        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);

        //BFS
        sb.setLength(0);

        visited = new boolean[N + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        deque.add(V);

        while (!deque.isEmpty()) {
            Integer target = deque.pollFirst();
            if (visited[target]) {
                continue;
            }
            visited[target] = true;
            sb.append(target).append(" ");

            List<Integer> adjacents = graph.get(target).stream().sorted().collect(Collectors.toList());
            for (Integer adjacent : adjacents) {
                if (!visited[adjacent]) {
                    deque.offerLast(adjacent);
                }
            }
        }

        sb.setLength(sb.length()-1);

        System.out.println(sb);
    }
}
