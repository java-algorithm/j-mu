package org.example._3week;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Virus {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int vertexCount = Integer.parseInt(scanner.nextLine());
        int edgeCount = Integer.parseInt(scanner.nextLine());

        boolean[] visited = new boolean[vertexCount + 1];

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < vertexCount + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edgeCount; i++) {
            String line = scanner.nextLine();
            StringTokenizer stringTokenizer = new StringTokenizer(line);
            Integer vertex1 = Integer.parseInt(stringTokenizer.nextToken());
            Integer vertex2 = Integer.parseInt(stringTokenizer.nextToken());

            graph.get(vertex1).add(vertex2);
            graph.get(vertex2).add(vertex1);
        }

        //DFS
        Stack<Integer> stack = new Stack<>();

        stack.push(1);

        while (!stack.isEmpty()) {
            Integer target = stack.pop();

            if (visited[target]) {
                continue;
            }
            visited[target] = true;

            List<Integer> adjacents = graph.get(target);
            int size = adjacents.size();
            for (int i = size - 1; i >= 0; i--) {
                Integer adjacent = adjacents.get(i);

                if (!visited[adjacent]) {
                    stack.push(adjacent);
                }
            }
        }

        int count = 0;
        for (int i = 1; i < visited.length; i++) {
            if (visited[i]) {
                count++;
            }
        }

        // 1번 컴퓨터는 포함안하는듯?
        System.out.println(count - 1);
    }
}
