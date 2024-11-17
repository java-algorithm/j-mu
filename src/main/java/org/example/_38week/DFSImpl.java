package org.example._38week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DFSImpl {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int M;
    private static final List<List<Integer>> graph = new ArrayList<>();
    private static boolean[] visited;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        initGraph();

//        printGraph();
        dfs(1);

        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
    }

    private static void dfs(int n) {
        visited[n] = true;
        sb.append(n+" ");

        List<Integer> adjacents = graph.get(n);
        for (int i = 0; i < adjacents.size(); i++) {
            if (!visited[adjacents.get(i)]) {
                dfs(adjacents.get(i));
            }
        }

    }

    private static void initGraph() throws IOException {
        StringTokenizer st;
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(A).add(B);
            graph.get(B).add(A);
        }

        for (int i = 1; i <= N; i++) {
            graph.get(i).sort(Integer::compareTo);
        }
    }

    private static void printGraph() {
        for (int i = 1; i < graph.size(); i++) {
            for (int j = 0; j < graph.get(i).size(); j++) {
                System.out.print(graph.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
