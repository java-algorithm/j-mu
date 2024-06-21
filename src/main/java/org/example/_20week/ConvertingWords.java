package org.example._20week;

import java.util.*;

public class ConvertingWords {
    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
//        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        String[] words = {"hot", "dot", "dog", "lot", "log"};

        final int solution = solution(begin, target, words);
        System.out.println(solution);
    }

    private static int solution(final String begin, final String target, final String[] words) {
        // 그래프 선언
        List<List<Integer>> graph = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        // 맨 마지막 index를 begin이라고 하자.
        for (int i = 0; i < words.length; i++) {
            graph.add(new ArrayList<>());
            map.put(words[i], i);
        }
        graph.add(new ArrayList<>());
        map.put(begin, words.length);

        if (!map.containsKey(target)) {
            return 0;
        }

        // 그래프 초기화
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j <= words.length; j++) {
                if (j == words.length) {
                    if (isConnected(words[i], begin)) {
                        final Integer word1Index = map.get(words[i]);
                        final Integer word2Index = map.get(begin);

                        graph.get(word1Index).add(words.length);
                        graph.get(words.length).add(word1Index);
                    }

                } else if (isConnected(words[i], words[j])) {
                    final Integer word1Index = map.get(words[i]);
                    final Integer word2Index = map.get(words[j]);

                    graph.get(word1Index).add(word2Index);
                    graph.get(word2Index).add(word1Index);
                }
            }
        }

        for (int i = 0; i < graph.size(); i++) {
            final String word = i == words.length ? begin : words[i];
            System.out.print(word + " :");

            final List<Integer> integers = graph.get(i);
            for (int j = 0; j < integers.size(); j++) {
                if (integers.get(j) == words.length) {
                    System.out.print(begin + " ");
                } else {
                    System.out.print(words[integers.get(j)] + " ");
                }
            }
            System.out.println();
        }

        // bfs이용해서 최소값 구하기.
        boolean[] visited = new boolean[words.length + 1];
        final Integer beginIndex = map.get(begin);
        final Integer targetIndex = map.get(target);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(beginIndex, 0));
        visited[words.length] = true;

        while (!queue.isEmpty()) {
            final Node poll = queue.poll();

            if (poll.value == targetIndex) {
                return poll.step;
            }

            for (final Integer adjacent : graph.get(poll.value)) {
                if (!visited[adjacent]) {
                    visited[adjacent] = true;
                    queue.offer(new Node(adjacent, poll.step + 1));
                }
            }
        }

        return 0;
    }

    private static class Node {
        int value;
        int step;

        public Node(final int value, final int step) {
            this.value = value;
            this.step = step;
        }
    }

    private static boolean isConnected(String str1, String str2) {
        int count = 0;
        final int length = str1.length();
        for (int i = 0; i < length; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                count++;
                if (count >= 2) {
                    return false;
                }
            }
        }

        return true;
    }
}
