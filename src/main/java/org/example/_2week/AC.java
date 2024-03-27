package org.example._2week;

// https://www.acmicpc.net/problem/5430

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class AC {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static boolean FORWARD = true;
    private static boolean REVERSE = false;

    public static void main(String[] args) throws IOException {
        final int testCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCount; i++) {
            test();
        }

        br.close();
        bw.flush();
        bw.close();
    }

    private static void test() throws IOException {
        final String operations = br.readLine();

        br.readLine(); //length필요없어서 버림.

        final String numbers = br.readLine();
        final String trimmedNumbers = numbers.substring(1, numbers.length() - 1);
        if (trimmedNumbers.isEmpty()) {
            if (operations.contains("D")) {
                System.out.println("error");
            } else {
                System.out.println("[]");
            }
            return;
        }

        Deque<Integer> parsedNumbers = Arrays.stream(trimmedNumbers.split(","))
            .map(Integer::parseInt)
            .collect(Collectors.toCollection(ArrayDeque::new));

        boolean direction = FORWARD;
        final int operationsLength = operations.length();
        for (int i = 0; i < operationsLength; i++) {
            char operation = operations.charAt(i);
            switch (operation) {
                case 'R':
                    direction = !direction;
                    break;
                case 'D':
                    if (parsedNumbers.isEmpty()) {
                        System.out.println("error");
                        return;
                    }

                    if (direction == FORWARD) {
                        parsedNumbers.pollFirst();
                    } else if (direction == REVERSE) {
                        parsedNumbers.pollLast();
                    }
            }
        }

        printArray(parsedNumbers, direction);
    }

    private static void printArray(Deque<Integer> parsedNumbers, boolean direction) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');

        Iterator<Integer> iterator;
        if (direction == FORWARD) {
            iterator = parsedNumbers.iterator();
        } else {
            iterator = parsedNumbers.descendingIterator();
        }

        while (iterator.hasNext()) {
            sb.append(iterator.next()).append(',');
        }

        if (sb.length() != 1) {
            sb.deleteCharAt(sb.length() - 1);
        }

        sb.append(']');
        System.out.println(sb);
    }
}
