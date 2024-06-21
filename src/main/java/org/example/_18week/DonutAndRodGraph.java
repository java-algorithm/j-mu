package org.example._18week;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DonutAndRodGraph {

    private static List<Integer>[] graph = (List<Integer>[]) new List[1_000_001];
    private static int[] inNodes = new int[1_000_001];
    private static int[] outNodes = new int[1_000_001];
    private static int rodGraphsCount = 0;

    public static void main(String[] args) {
        int[][] edges = {{2, 3}, {4, 3}, {1, 1}, {2, 1}};
//        int[][] edges = {{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}};

        solution(edges);
    }

    private static int[] solution(final int[][] edges) {
        int[] answer = {};
        // 그래프 초기화
        for (int i = 0; i <= 1_000_000; i++) {
            graph[i] = new ArrayList<>();
        }

        int maxNodeValue = Integer.MIN_VALUE;
        // edges순회하며 그래프 초기화하면서 가짜 node를 찾아야함.
        for (int[] edge : edges) {
            final int from = edge[0];
            final int to = edge[1];

            graph[from].add(to);
            outNodes[from]++;
            inNodes[to]++;

            if (from > maxNodeValue) {
                maxNodeValue = from;
            }
            if (to > maxNodeValue) {
                maxNodeValue = to;
            }
        }

        boolean[] visited = new boolean[maxNodeValue + 1];
        int fakeNode = 0;
        int realGraphCount = 0;
        for (int i = 1; i <= maxNodeValue; i++) {
            if (inNodes[i] == 0 && outNodes[i] >= 2) {
                fakeNode = i;
                realGraphCount = outNodes[i];
                break;
            }
        }

        System.out.println("fakeNode: " + fakeNode);
        System.out.println("realGraphCount: " + realGraphCount);

        //가짜 노드를 그래프에서 제거.
        for (int to : graph[fakeNode]) {
            inNodes[to]--;
        }

        // 막대모양 그래프 startingPoint 판별
        List<Integer> rodGraphSp = new ArrayList<>();
        for (int i = 1; i <= maxNodeValue; i++) {
            if (inNodes[i] == 0 && outNodes[i] == 1) {
                rodGraphSp.add(i);
                rodGraphsCount++;
                continue;
            }

            if (inNodes[i] == 0 && outNodes[i] == 0) {
                rodGraphsCount++;
                visited[i] = true;
            }
        }

        System.out.println("rod Graphs count:" + rodGraphsCount);
        // 1. 막대모양 그래프 판별
        for (int rodStartingPoint : rodGraphSp) {
            visited[rodStartingPoint] = true;
            System.out.println("rod Graph!!");
            System.out.print(rodStartingPoint + " ");
            int prev = rodStartingPoint;
            while (!graph[prev].isEmpty()) {
                final Integer next = graph[prev].get(0);
                visited[next] = true;
                System.out.println(next + " ");
                prev = next;
            }
        }

        // 2. 8자 모양 그래프 판별 (8자 모양은 반드시 in 2, out 2)인 node를 반드시 포함한다....? 이거 이용해야할까..?

        // 3. 도넛, 8자 모양 그래프 판별.
        for (int i = 1; i <= maxNodeValue; i++) {
            if (visited[i]) {
                continue;
            }

            // starting point 초기화.
            int startingPoint = i;
            visited[startingPoint] = true;

            Stack<Integer> stack = new Stack<>();
            stack.add(startingPoint);

            // 그래프 탐색 시작.
            while (!stack.isEmpty()) {
                final Integer next = stack.pop();
                if (next == startingPoint) {
                    continue;
                }

                for (int adjacent : graph[next]) {
                    boolean isIntersectionPoint = inNodes[adjacent] == 2 && outNodes[adjacent] == 2;

                    if (!isIntersectionPoint) {

                    }
                }
            }
        }


        return answer;
    }
}
