package org.example._19week;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SheepAndWolf {

    private static final int SHEEP = 0;
    private static final int WOLF = 1;
    private static final int PARENT = 0;
    private static final int CHILD = 1;

    private static List<List<Node>> tree;
    private static int maxSheepCount = 0;

    public static void main(String[] args) {
        int[] info1 = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edges1 = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};

        int[] info2 = {0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0};
        int[][] edges2 = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9, 10}};

        System.out.println(solution(info2, edges2));
    }

    private static int solution(final int[] info, final int[][] edges) {
        // tree 초기화
        tree = new ArrayList<>();
        for (int i = 0; i < info.length; i++) {
            tree.add(new ArrayList<>());
        }

        // tree 세팅
        for (final int[] edge : edges) {
            final int parent = edge[PARENT];
            final int child = edge[CHILD];
            final int childType = info[child];
            final Node childNode = new Node(child, childType);

            tree.get(parent).add(childNode);
        }

        // 1번 노드
        final List<Node> children = tree.get(0);
        dfs(0, new ArrayList<>(), 1, 0, info);

        return maxSheepCount;
    }

    private static void dfs(final int node, final List<Node> children, final int sheepCount, final int wolfCount, final int[] info) {
        maxSheepCount = Math.max(maxSheepCount, sheepCount);
        final List<Node> nodes = tree.get(node);
        children.addAll(nodes);

        for (final Node child : children) {
            final int childType = info[child.node];
            final int newWolfCount = wolfCount + (childType == WOLF ? 1 : 0);
            final int newSheepCount = sheepCount + (childType == SHEEP ? 1 : 0);

            if (newWolfCount < newSheepCount) {
                final List<Node> newChildren = children.stream().filter(e -> !e.equals(child)).collect(Collectors.toList());
                dfs(child.node, newChildren, newSheepCount, newWolfCount, info);
            }
        }
    }

    private static class Node {
        int node;
        int type;

        public Node(final int node, final int type) {
            this.node = node;
            this.type = type;
        }
    }
}
