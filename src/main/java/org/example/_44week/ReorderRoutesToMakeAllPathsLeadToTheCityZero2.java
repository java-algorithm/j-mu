package org.example._44week;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReorderRoutesToMakeAllPathsLeadToTheCityZero2 {

    private static final int FORWARD = 0;
    private static final int REVERSE = 1;

    private static List<List<Bridge>> graph;
    private static boolean[] visited;
    private static int N;
    private static int answer;

    public static void main(String[] args) {
        int n = 6;
        int[][] connections = {{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}};

        int ans = minReorder(n, connections);
        System.out.println(ans);
    }

    public static int minReorder(int n, int[][] connections) {
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        visited = new boolean[n];
        N = n;
        answer = 0;

        for (int[] connection : connections) {
            graph.get(connection[0]).add(new Bridge(connection[1], FORWARD));
            graph.get(connection[1]).add(new Bridge(connection[0], REVERSE));
        }

        Stack<Integer> stack = new Stack<>();
        visited[0] = true;
        push(stack, 0);

        while (!stack.isEmpty()) {
            Integer current = stack.pop();

            push(stack, current);
        }

        return answer;
    }

    private static void push(Stack<Integer> stack, int currentNode) {
        for (Bridge bridge : graph.get(currentNode)) {
            if (visited[bridge.node]) {
                continue;
            }

            if (bridge.direction == FORWARD) {
                answer++;
            }

            visited[bridge.node] = true;
            stack.push(bridge.node);
        }
    }

    private static class Bridge {
        int node;
        int direction;

        public Bridge(int node, int direction) {
            this.node = node;
            this.direction = direction;
        }
    }
}
