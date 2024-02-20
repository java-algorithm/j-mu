package org.example._3week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Cabbage {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int testCaseCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCaseCount; i++) {
            test();
        }

        br.close();
    }

    private static void test() throws IOException {
        List<Position> positions = new ArrayList<>();
        int count = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        int colLength = Integer.parseInt(st.nextToken());
        int rowLength = Integer.parseInt(st.nextToken());
        int cabbageCount = Integer.parseInt(st.nextToken());
        boolean[][] visited = new boolean[rowLength][colLength];

        for (int i = 0; i < cabbageCount; i++) {
            st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());

            positions.add(new Position(row, col));
        }

        int[][] graph = new int[rowLength][colLength];
        for (Position position : positions) {
            graph[position.row][position.col] = 1;
        }

        Stack<Position> stack = new Stack<>();
        for (int i = 0; i < cabbageCount; i++) {
            Position position = positions.get(i);
            if (visited[position.row][position.col] || graph[position.row][position.col] == 0) {
                continue;
            }

            stack.push(position);
            while (!stack.isEmpty()) {
                Position target = stack.pop();
                if (visited[target.row][target.col] || graph[target.row][target.col] == 0) {
                    continue;
                }

                visited[target.row][target.col] = true;

                Position up = new Position(target.row - 1, target.col);
                Position down = new Position(target.row + 1, target.col);
                Position left = new Position(target.row, target.col - 1);
                Position right = new Position(target.row, target.col + 1);

                if (!isOutOfBounds(up, rowLength, colLength)) {
                    if (!visited[up.row][up.col] && graph[up.row][up.col] == 1) {
                        stack.push(up);
                    }
                }

                if (!isOutOfBounds(down, rowLength, colLength)) {
                    if (!visited[down.row][down.col] && graph[down.row][down.col] == 1) {
                        stack.push(down);
                    }
                }

                if (!isOutOfBounds(left, rowLength, colLength)) {
                    if (!visited[left.row][left.col] && graph[left.row][left.col] == 1) {
                        stack.push(left);
                    }
                }

                if (!isOutOfBounds(right, rowLength, colLength)) {
                    if (!visited[right.row][right.col] && graph[right.row][right.col] == 1) {
                        stack.push(right);
                    }
                }
            }

            count++;
        }

        System.out.println(count);
    }

    public static boolean isOutOfBounds(Position position, int rowLength, int colLength) {
        int row = position.row;
        int col = position.col;

        if (row < 0 || row >= rowLength) {
            return true;
        }

        if (col < 0 || col >= colLength) {
            return true;
        }

        return false;
    }

    static class Position {

        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
