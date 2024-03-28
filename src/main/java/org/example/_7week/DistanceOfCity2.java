package org.example._7week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DistanceOfCity2 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int cityCount = Integer.parseInt(st.nextToken());
        int edgeCount = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int startingPoint = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < cityCount + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(br.readLine());
            final int from = Integer.parseInt(st.nextToken());
            final int to = Integer.parseInt(st.nextToken());

            graph.get(from).add(to);
        }

        boolean[] visited = new boolean[cityCount + 1];
        int[] distances = new int[cityCount + 1];

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startingPoint] = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(startingPoint);

        // 다익스트라 알고리즘 시작
        while (!queue.isEmpty()) {
            int curIndex = queue.poll();

            final int adjacentSize = graph.get(curIndex).size();
            for (int j = 0; j < adjacentSize; j++) {
                int adjNode = graph.get(curIndex).get(j);

                if (distances[adjNode] > distances[curIndex] + 1) {
                    distances[adjNode] = distances[curIndex] + 1;
                    queue.offer(adjNode);
                }
            }
        }

        List<Integer> answer = new ArrayList<>();
        for (int i = 1; i < distances.length; i++) {
            if (distances[i] == K) {
                answer.add(i);
            }
        }

        if (answer.isEmpty()) {
            System.out.println(-1);
        } else {
            answer.forEach(System.out::println);
        }
    }
}
