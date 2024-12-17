package org.example._41week;

import java.util.*;

public class DoublyPriorityQueue {

    private static final PriorityQueue<Integer> maxQueue = new PriorityQueue<>();
    private static final PriorityQueue<Integer> minQueue = new PriorityQueue<>();
    private static final Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
//        String[] operations = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        String[] operations = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};

        int[] solution = solution(operations);

        for (int value : solution) {
            System.out.print(value + " ");
        }
    }

    public static int[] solution(String[] operations) {
        for (String operation : operations) {
            String[] ops = operation.split(" ");

            if (ops[0].equals("I")) {
                int value = Integer.parseInt(ops[1]);
                maxQueue.add(value);
                minQueue.add(value);
                map.put(value, map.getOrDefault(value, 0) + 1);
            } else if (ops[1].equals("1")) {
                poll(maxQueue);
            } else if (ops[1].equals("-1")) {
                poll(minQueue);
            }
        }

        List<Integer> values = new ArrayList<>();
        while (!maxQueue.isEmpty()) {
            Integer poll = poll(maxQueue);
            if (poll == null) {
                break;
            }
            values.add(poll);
        }

        return values.stream().mapToInt(Integer::intValue).toArray();
    }

    private static Integer poll(PriorityQueue<Integer> queue) {
        while (!queue.isEmpty()) {
            Integer value = queue.poll();

            Integer count = map.getOrDefault(value, 0);
            if (count == 0) {
                continue;
            }

            map.remove(value, count == 0 ? 0 : count - 1);
            return value;
        }

        return null;
    }

}
