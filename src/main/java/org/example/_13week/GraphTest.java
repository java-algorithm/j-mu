package org.example._13week;

import java.util.*;

public class GraphTest {

    private static int count = 0;

    // 그래프를 나타내는 클래스
    static class Graph {
        private int V; // 노드의 수
        private LinkedList<Integer>[] adj; // 인접 리스트

        // 생성자
        Graph(int v) {
            V = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; ++i)
                adj[i] = new LinkedList();
        }

        static Graph generateGraph(int nodes, int edges) {
            Graph graph = new Graph(nodes);

            // 중복 간선을 피하기 위한 Set
            Set<String> uniqueEdges = new HashSet<>();

            // 간선 개수에 맞게 무작위로 연결
            Random rand = new Random();
            int edgeCount = 0;
            while (edgeCount < edges) {
                int v = rand.nextInt(nodes); // 임의의 노드 선택
                int w = rand.nextInt(nodes);
                if (v != w && uniqueEdges.add(v + "," + w)) { // 중복 체크
                    graph.addEdge(v, w);
                    edgeCount++;
                }
            }

            return graph;
        }

        // 간선 추가
        void addEdge(int v, int w) {
            adj[v].add(w);
        }

        // DFS
        void DFS(int v) {
            boolean[] visited = new boolean[V];
            Stack<Integer> stack = new Stack<>();

            visited[v] = true;
            stack.push(v);

            while (!stack.isEmpty()) {
                v = stack.pop();
                System.out.print(v + " ");
                count++;

                Iterator<Integer> i = adj[v].listIterator();
                while (i.hasNext()) {
                    int n = i.next();
                    if (!visited[n]) {
                        visited[n] = true;
                        stack.push(n);
                    }
                }
            }
        }

        // BFS
        void BFS(int s) {
            boolean[] visited = new boolean[V];
            LinkedList<Integer> queue = new LinkedList<>();

            visited[s] = true;
            queue.add(s);

            while (queue.size() != 0) {
                s = queue.poll();
                System.out.print(s + " ");
                count++;

                Iterator<Integer> i = adj[s].listIterator();
                while (i.hasNext()) {
                    int n = i.next();
                    if (!visited[n]) {
                        visited[n] = true;
                        queue.add(n);
                    }
                }
            }
        }

        // 재귀를 통한 DFS
        void recursiveDFS(int v) {
            boolean[] visited = new boolean[V];
            recursiveDFSUtil(v, visited);
        }

        void recursiveDFSUtil(int v, boolean[] visited) {
            visited[v] = true;
            System.out.print(v + " ");
            count++;

            Iterator<Integer> i = adj[v].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    recursiveDFSUtil(n, visited);
                }
            }
        }
    }

    public static void main(String args[]) {
        int nodes = 10000;
        int edges = 20000;

        Graph g = Graph.generateGraph(nodes, edges);

        // DFS 성능 테스트
        count=0;
        long startTime = System.currentTimeMillis();
        g.DFS(0); // 시작 노드를 0으로 설정
        long endTime = System.currentTimeMillis();
        System.out.println("\nDFS 수행 시간: " + (endTime - startTime) + "ms");
        System.out.println("node count ="+ count);

        // BFS 성능 테스트
        count=0;
        startTime = System.currentTimeMillis();
        g.BFS(0); // 시작 노드를 0으로 설정
        endTime = System.currentTimeMillis();
        System.out.println("\nBFS 수행 시간: " + (endTime - startTime) + "ms");
        System.out.println("node count ="+ count);

        // 재귀를 통한 DFS 성능 테스트
        count=0;
        startTime = System.currentTimeMillis();
        g.recursiveDFS(0); // 시작 노드를 0으로 설정
        endTime = System.currentTimeMillis();
        System.out.println("\n재귀를 통한 DFS 수행 시간: " + (endTime - startTime) + "ms");
        System.out.println("node count ="+ count);

    }
}

