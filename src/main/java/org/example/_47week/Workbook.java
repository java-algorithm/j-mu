package org.example._47week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Workbook {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int[] inputs;
    private static List<Integer>[] graph;
    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i < N + 1; i++) {
            if (inputs[i] == 0) {
                pq.add(i);
            }
        }

        while (!pq.isEmpty()) {
            Integer value = pq.poll();

            answer.append(value + " ");
            for (int node : graph[value]) {
                inputs[node]--;
                if (inputs[node] == 0) {
                    pq.add(node);
                }
            }
        }

        answer.deleteCharAt(answer.length() - 1);
        System.out.println(answer);
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        inputs = new int[N + 1];
        graph = new List[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int prev = Integer.parseInt(st.nextToken());
            int post = Integer.parseInt(st.nextToken());

            graph[prev].add(post);
            inputs[post]++;
        }
    }
}
