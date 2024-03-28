package org.example._7week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 우선순위 큐를 사용하지 않고 다익스트라를 구현한 예시입니다.
// 설명을 위해 작성한 코드입니당. (실제론 시간초과납니다)
public class DistanceOfCity {

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

        // 다익스트라 알고리즘 시작
        for (int i = 0; i < cityCount; i++) {
            // 4-1 현재 거리 비용 중 최소인 지점을 선택한다.
            // 거리가 최소인 노드의 index
            int nodeValue = Integer.MAX_VALUE;
            int nodeIndex = 0;

            // 거리가 최소인 node를 찾는다.
            for (int j = 1; j < cityCount + 1; j++) {
                if (!visited[j] && distances[j] < nodeValue) {
                    nodeValue = distances[j];
                    nodeIndex = j;
                }
            }

            visited[nodeIndex] = true;

            final int adjacentSize = graph.get(nodeIndex).size();
            for (int j = 0; j < adjacentSize; j++) {
                int adjNode = graph.get(nodeIndex).get(j);

                if (distances[adjNode] > distances[nodeIndex] + 1) {
                    distances[adjNode] = distances[nodeIndex] + 1;
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
        }else{
            answer.forEach(System.out::println);
        }
    }
}
