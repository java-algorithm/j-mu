package org.example._19week;

public class Network {

    private static boolean[] visited;

    public static void main(String[] args) {
        int n = 3;
        int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
//        int[][] computers = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};

        System.out.println(solution(n, computers));
    }

    public static int solution(int n, int[][] computers) {
        int count = 0;
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }

            dfs(i, computers);
            count++;
        }

        return count;
    }

    public static void dfs(int node, int[][] computers) {
        visited[node] = true;

        for (int i = 0; i < computers[node].length; i++) {
            if (computers[node][i] == 0) {
                continue;
            }

            if (!visited[i]) {
                dfs(i, computers);
            }
        }
    }
}
