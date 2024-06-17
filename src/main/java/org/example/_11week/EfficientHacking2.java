//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;
//
//public class Main {
//    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    private static List<Integer>[] graph;
//    private static int maxCount = Integer.MIN_VALUE;
//    private static boolean[][] visited;
//    private static int[] dp;
//
//    public static void main(String[] args) throws IOException {
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//        visited = new boolean[N + 1][N + 1];
//        dp = new int[N + 1];
//        graph = new ArrayList[N + 1];
//        for (int i = 0; i < N + 1; i++) {
//            graph[i] = new ArrayList<>();
//        }
//
//        for (int i = 0; i < M; i++) {
//            st = new StringTokenizer(br.readLine());
//            int to = Integer.parseInt(st.nextToken());
//            int from = Integer.parseInt(st.nextToken());
//
//            graph[from].add(to);
//        }
//
//        for (int vertex = 1; vertex < N + 1; vertex++) {
//            dfs(vertex);
//
//            if (dp[vertex] > maxCount) {
//                maxCount = dp[vertex];
//            }
//        }
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 1; i < N + 1; i++) {
//            if (dp[i] == maxCount) {
//                sb.append(i + " ");
//            }
//        }
//        sb.deleteCharAt(sb.length() - 1);
//        sb.append("\n");
//        System.out.println(sb);
//    }
//
//    private static int dfs(int vertex) {
//        visited[vertex] = true;
//        if (dp[vertex] != 0) {
//            return dp[vertex];
//        }
//
//        int childCount = 0;
//        for (Integer adjacent : graph[vertex]) {
//            if (visited[adjacent]) {
//                continue;
//            }
//
//            if (visited[adjacent][vertex]) {
//                childCount += dfs(adjacent) - 1;
//            } else (!visited[adjacent]) {
//                childCount += dfs(adjacent);
//            }
//        }
//
//        dp[vertex] = childCount + 1;
//        return childCount + 1;
//    }
//}