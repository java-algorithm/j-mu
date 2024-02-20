package org.example._3week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Relatives {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        int peopleCount = Integer.parseInt(br.readLine());

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < peopleCount + 1; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        // target A, B
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int edgeCount = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[peopleCount + 1];

        for (int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(br.readLine());
            int vertex1 = Integer.parseInt(st.nextToken());
            int vertex2 = Integer.parseInt(st.nextToken());

            graph.get(vertex1).add(vertex2);
            graph.get(vertex2).add(vertex1);
        }

        boolean found = false;
        int depth = 1;
        List<Deque<Integer>> deque = new ArrayList<>(new ArrayDeque<>());
        for (int i = 0; i < peopleCount; i++) {
            deque.add(new ArrayDeque<>());
        }
        deque.get(depth).add(A);

        while (!deque.get(depth).isEmpty()) {
            while (true) {
                Deque<Integer> curDeque = deque.get(depth);
                if (curDeque.isEmpty()) {
                    break;
                }
                Integer target = curDeque.pollFirst();

                if (visited[target]) {
                    continue;
                }
                visited[target] = true;

                List<Integer> adjacents = graph.get(target);
                for (Integer adjacent : adjacents) {
                    if (adjacent == B) {
                        found = true;
                        break;
                    }

                    if (!visited[adjacent]) {
                        if (depth + 1 > peopleCount) {
                            break;
                        }
                        deque.get(depth + 1).offerLast(adjacent);
                    }
                }
            }
            if (found) {
                break;
            }

            if (depth + 1 > peopleCount) {
                break;
            }
            depth++;
        }

        System.out.println(found ? depth : -1);
    }
}
