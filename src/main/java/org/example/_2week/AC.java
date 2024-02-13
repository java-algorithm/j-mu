package org.example._2week;

// https://www.acmicpc.net/problem/5430

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class AC {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

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

        int numbersLength = Integer.parseInt(br.readLine());

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

        int startIndex = 0;
        final int operationsLength = operations.length();
        for (int i = 0; i < operationsLength; i++) {
            char operation = operations.charAt(i);
            switch (operation) {
                case 'R':
                    parsedNumbers = reverseArray(parsedNumbers, startIndex, numbersLength);
                    startIndex = 0;
                    numbersLength = parsedNumbers.length;
                    break;
                case 'D':
                    if (startIndex == numbersLength) {
                        System.out.println("error");
                        return;
                    }
                    startIndex++;
                    break;
            }
        }

        printArray(parsedNumbers, startIndex, numbersLength);
    }

    private static void printArray(final int[] parsedNumbers, final int startIndex, final int numbersLength) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = startIndex; i < numbersLength; i++) {
            sb.append(parsedNumbers[i]).append(",");
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append("]\n");

        System.out.println(sb);
    }

    private static int[] reverseArray(Deque<Integer> parsedNumbers, int startIndex, int numbersLength) {
        final int newLength = numbersLength - startIndex;
        final int[] newNumbers = new int[newLength];

        int newNumbersIndex = 0;
        for (int i = startIndex; i < newLength; i++) {
            newNumbers[newLength - (newNumbersIndex++) - 1] = parsedNumbers[i];
        }

        return newNumbers;
    }

}
