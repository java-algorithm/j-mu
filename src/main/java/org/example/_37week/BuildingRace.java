package org.example._37week;

import java.util.LinkedList;
import java.util.Queue;

public class BuildingRace {

    private static int[][] board;
    private static int answer = Integer.MAX_VALUE;
    private static int targetRow;
    private static int targetCol;

    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, 1, 0, -1};

    public int solution(int[][] _board) {
        board = _board;
        targetRow = board.length - 1;
        targetCol = board[0].length - 1;

//        boolean[][] visited = new boolean[targetRow + 1][targetCol + 1];
//        visited[0][0] = true;
//        dfs(-500, 0, 0, -1, visited);
        bfs();
        return answer;
    }

    public void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, -1, -500));
        boolean[][][] visited = new boolean[targetRow + 1][targetCol + 1][4];

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.row == targetRow && node.col == targetCol) {
                answer = Math.min(answer, node.cost);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = node.row + dr[i];
                int nextCol = node.col + dc[i];

                if (nextRow < 0 || nextRow > targetRow || nextCol < 0 || nextCol > targetCol) {
                    continue;
                }

                if (board[nextRow][nextCol] == 1) {
                    continue;
                }

                // row, col , dir
                int nextCost = node.cost + ((node.dir == i) ? 100 : 600);
                if (visited[nextRow][nextCol][i] && board[nextRow][nextCol] < nextCost) {
                    continue;
                }

                visited[nextRow][nextCol][i] = true;
                board[nextRow][nextCol] = nextCost;
                queue.offer(new Node(nextRow, nextCol, i, nextCost));
            }
        }


    }

    public class Node {
        int row;
        int col;
        int dir;
        int cost;

        public Node(int row, int col, int dir, int cost) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "row=" + row +
                    ", col=" + col +
                    ", dir=" + dir +
                    ", cost=" + cost +
                    '}';
        }
    }

    public void dfs(int cost, int row, int col, int prevDir, boolean[][] visited) {
        if (row == targetRow && col == targetCol) {
            answer = Math.min(answer, cost);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextRow = row + dr[i];
            int nextCol = col + dc[i];

            if (nextRow < 0 || nextRow > targetRow || nextCol < 0 || nextCol > targetCol) {
                continue;
            }

            if (board[nextRow][nextCol] == 1) {
                continue;
            }

            if (visited[nextRow][nextCol]) {
                continue;
            }

            if (cost + (i == prevDir ? 100 : 600) >= answer) {
                continue;
            }

            cost += i == prevDir ? 100 : 600;
            visited[nextRow][nextCol] = true;
            dfs(cost, nextRow, nextCol, i, visited);
            cost -= i == prevDir ? 100 : 600;
            visited[nextRow][nextCol] = false;
        }

    }

    public void printVisited(boolean[][] visited) {
        if (visited.length > 5) {
            return;
        }

        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[0].length; j++) {
                System.out.print(visited[i][j] ? 1 : 0);
            }
            System.out.println();
        }
        System.out.println();

    }

    class Dir {
        int dir;
        int cost;

        Dir(int dir, int cost) {
            this.dir = dir;
            this.cost = cost;
        }

        public String toString() {
            return "dir: " + dir + " cost: " + cost;
        }
    }
}
