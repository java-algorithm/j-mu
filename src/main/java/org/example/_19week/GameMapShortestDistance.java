package org.example._19week;

import java.util.LinkedList;
import java.util.Queue;

public class GameMapShortestDistance {

    private static final int[] dr = {0, -1, 0, 1};
    private static final int[] dc = {1, 0, -1, 0};
    private static boolean[][] visited;

    public static void main(String[] args) {
        int[][] map = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}};
        int[][] map1 = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,0},{0,0,0,0,1}};

        final int answer = solution(map1);
        System.out.println(answer);
    }

    private static int solution(final int[][] map) {
        return bfs(map);
    }

    private static int bfs(final int[][] map) {
        int shortestDistance = Integer.MAX_VALUE;
        final int rowLength = map.length;
        final int colLength = map[0].length;
        visited = new boolean[rowLength][colLength];

        final Node startingPoint = new Node(0, 0, 1);
        visited[0][0] = true;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(startingPoint);

        while (!queue.isEmpty()) {
            final Node curNode = queue.poll();
            if (curNode.row == rowLength - 1 && curNode.col == colLength - 1) {
                shortestDistance = Math.min(shortestDistance, curNode.distance);
                continue;
            }

            for (int i = 0; i < dr.length; i++) {
                int nextRow = curNode.row + dr[i];
                int nextCol = curNode.col + dc[i];

                if (nextRow >= rowLength || nextCol >= colLength || nextRow < 0 || nextCol < 0) {
                    continue;
                }

                if (map[nextRow][nextCol] == 0) {
                    continue;
                }

                if (!visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    queue.offer(new Node(nextRow, nextCol, curNode.distance + 1));
                }
            }
        }

        return shortestDistance == Integer.MAX_VALUE ? -1 : shortestDistance;
    }

    private static class Node {
        int row;
        int col;
        int distance;

        public Node(final int row, final int col, final int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }
}
