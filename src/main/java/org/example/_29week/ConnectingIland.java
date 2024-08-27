package org.example._29week;

import java.util.*;

class ConnectingIland {
    public int solution(int n, int[][] costs) {
        int[] parents = new int[n];
        Arrays.setAll(parents, idx -> idx);

        int edgeCount = 0;
        int cost = 0;

        Arrays.sort(costs, (e1, e2) -> Integer.compare(e1[2], e2[2]));
//        for (int i = 0; i < costs.length; i++) {
//            System.out.println(costs[i][0] + ", " + costs[i][1] + " : " + costs[i][2]);
//        }

        for (int i = 0; edgeCount < n - 1; i++) {
            boolean isSameGraph = isSameGraph(parents, costs[i][0], costs[i][1]);
            if (isSameGraph) {
                continue;
            }

            union(parents, costs[i][0], costs[i][1]);
            cost += costs[i][2];
            edgeCount++;
        }

        return cost;
    }

    private static boolean isSameGraph(int[] parents, int x, int y) {
        int parentX = getParent(parents, x);
        int parentY = getParent(parents, y);

        return parentX == parentY;
    }

    private static int getParent(int[] parents, int x) {
        if (parents[x] == x) {
            return x;
        }

        return parents[x] = getParent(parents, parents[x]);
    }

    private static void union(int[] parents, int x, int y) {
        x = getParent(parents, x);
        y = getParent(parents, y);

        if (x < y) {
            parents[y] = x;
        } else {
            parents[x] = y;
        }
    }}
