package org.example._32week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DoubleEndededPriorityQueueProblem {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCase; i++) {
            DEPQueue queue = new DEPQueue();
            int operationCount = Integer.parseInt(br.readLine());

            for (int j = 0; j < operationCount; j++) {
                queue.input(br.readLine());
            }

            if (queue.size() == 0) {
                System.out.println("EMPTY");
            } else if (queue.size() == 1) {
                int value = queue.removeMaximum();
                System.out.println(value + " " + value);
            } else {
                System.out.println(queue.removeMaximum() + " " + queue.removeMinimum());
            }
        }
    }

    private static class DEPQueue {
        // 오름차순. 0: 최소 Size-1 : 최대
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        Map<Integer, Integer> valueCountMap = new HashMap<>();

        public void input(String command) {
            StringTokenizer st = new StringTokenizer(command);
            char op = st.nextToken().charAt(0);
            int value = Integer.parseInt(st.nextToken());

            if (op == 'D' && value == 1) {
                remove(this.maxQueue);
            } else if (op == 'D' && value == -1) {
                remove(this.minQueue);
            } else if (op == 'I') {
                insert(value);
            }
        }

        public int removeMaximum() {
            return remove(this.maxQueue);
        }

        public int removeMinimum() {
            return remove(this.minQueue);
        }


        private int remove(Queue<Integer> queue) {
            int value = 0;

            if (valueCountMap.isEmpty()) {
                return 0;
            }

            while (!queue.isEmpty()) {
                value = queue.poll();
                int cnt = valueCountMap.getOrDefault(value, 0);

                if (cnt == 0) continue;
                if (cnt == 1) valueCountMap.remove(value);
                else valueCountMap.put(value, cnt - 1);

                break;
            }

            return value;
        }

        private void insert(int value) {
            minQueue.add(value);
            maxQueue.add(value);
            Integer valueCount = valueCountMap.getOrDefault(value, 0);
            valueCountMap.put(value, valueCount + 1);
        }

        private int size() {
            return valueCountMap.size();
        }
    }
}
