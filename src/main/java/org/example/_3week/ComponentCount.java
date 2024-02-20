package org.example._3week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class ComponentCount {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        int vertexCount = Integer.parseInt(st.nextToken());
        int edgeCount = Integer.parseInt(st.nextToken());

        if (edgeCount == 0) {
            System.out.println(vertexCount);
            return;
        }

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < vertexCount + 1; i++) {
            graph.add(new ArrayList<>());
        }

        boolean[] visited = new boolean[vertexCount + 1];
        for (int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(br.readLine());
            int vertex1 = Integer.parseInt(st.nextToken());
            int vertex2 = Integer.parseInt(st.nextToken());

            graph.get(vertex1).add(vertex2);
            graph.get(vertex2).add(vertex1);
        }

        Stack<Integer> stack = new Stack<>();

        int count = 0;
        for (int i = 1; i < vertexCount + 1; i++) {
            if (visited[i]) {
                continue;
            }

            stack.push(i);

            while (!stack.isEmpty()) {
                Integer target = stack.pop();
                if (visited[target]) {
                    continue;
                }
                visited[target] = true;

                List<Integer> adjacents = graph.get(target).stream().sorted().collect(Collectors.toList());
                for (Integer adjacent : adjacents) {
                    if (!visited[adjacent]) {
                        stack.push(adjacent);
                    }
                }
            }

            count++;
        }

        System.out.println(count);
    }
}
