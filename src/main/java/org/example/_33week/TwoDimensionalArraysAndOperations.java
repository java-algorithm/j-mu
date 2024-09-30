package org.example._33week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TwoDimensionalArraysAndOperations {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int targetRow;
    private static int targetCol;
    private static int targetValue;

    public static void main(String[] args) throws IOException {
        int[][] A = new int[100][100];
        StringTokenizer st = new StringTokenizer(br.readLine());
        targetRow = Integer.parseInt(st.nextToken()) - 1;
        targetCol = Integer.parseInt(st.nextToken()) - 1;
        targetValue = Integer.parseInt(st.nextToken());

        // A 초기화 끝.
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        operation(A);
    }

    private static void operation(int[][] a) {
        int maxRowSize = 3;
        int maxColSize = 3;

        for (int depth = 0; depth <= 100; depth++) {
            if (a[targetRow][targetCol] == targetValue) {
                System.out.println(depth);
                return;
            }

            int[][] newA = new int[100][100];
            if (maxRowSize >= maxColSize) {
                for (int row = 0; row < maxRowSize; row++) {
                    Map<Integer, Integer> frequencyMap = new HashMap<>();
                    for (int col = 0; col < maxColSize; col++) {
                        if (a[row][col] == 0) {
                            continue;
                        }

                        Integer currentCount = frequencyMap.getOrDefault(a[row][col], 0);
                        frequencyMap.put(a[row][col], currentCount + 1);
                    }


                    PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(entryComparator());
                    queue.addAll(frequencyMap.entrySet());

                    for (int idx = 0; idx < 50 && !queue.isEmpty(); idx += 2) {
                        Map.Entry<Integer, Integer> poll = queue.poll();
                        newA[row][idx] = poll.getKey();
                        newA[row][idx + 1] = poll.getValue();
                        maxColSize = Math.max((idx + 1) * 2, maxColSize);
                    }
                }
            } else {
                for (int col = 0; col < maxRowSize; col++) {
                    Map<Integer, Integer> frequencyMap = new HashMap<>();
                    for (int row = 0; row < maxColSize; row++) {
                        if (a[row][col] == 0) {
                            continue;
                        }

                        Integer currentCount = frequencyMap.getOrDefault(a[row][col], 0);
                        frequencyMap.put(a[row][col], currentCount + 1);
                    }


                    PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(entryComparator());
                    queue.addAll(frequencyMap.entrySet());

                    for (int idx = 0; idx < 50 && !queue.isEmpty(); idx += 2) {
                        Map.Entry<Integer, Integer> poll = queue.poll();
                        newA[idx][col] = poll.getKey();
                        newA[idx + 1][col] = poll.getValue();
                        maxRowSize = Math.max((idx + 1) * 2, maxRowSize);
                    }
                }
            }

            a = newA;
        }

        System.out.println(-1);
    }

    private static Comparator<Map.Entry<Integer, Integer>> entryComparator() {
        return (e1, e2) -> {
            int valueCompare = Integer.compare(e1.getValue(), e2.getValue());
            if (valueCompare != 0) {
                return valueCompare;
            } else {
                return Integer.compare(e1.getKey(), e2.getKey());
            }
        };
    }


}
