package org.example._11week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DSLR {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Set<Integer> visited;

    private static int A;
    private static int B;

    private static class Process {
        int value;
        String operations;

        public Process(int value, String operations) {
            this.value = value;
            this.operations = operations;
        }
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            visited = new HashSet<>();
            Process process = new Process(A, "");
            bfs(process);
        }
    }

    private static void bfs(Process process) {
        Queue<Process> queue = new LinkedList<>();
        queue.offer(process);
        visited.add(process.value);

        while (!queue.isEmpty()) {
            Process poll = queue.poll();
            int value = poll.value;
            if (value == B) {
                System.out.println(poll.operations);
                break;
            }

            int d = D(value);
            if (!visited.contains(d)) {
                Process nextProcess = new Process(d, poll.operations + "D");
                queue.offer(nextProcess);
                visited.add(d);
            }

            int s = S(value);
            if (!visited.contains(s)) {
                Process nextProcess = new Process(s, poll.operations + "S");
                queue.offer(nextProcess);
                visited.add(s);
            }

            int l = L(value);
            if (!visited.contains(l)) {
                Process nextProcess = new Process(l, poll.operations + "L");
                queue.offer(nextProcess);
                visited.add(l);
            }

            int r = R(value);
            if (!visited.contains(r)) {
                Process nextProcess = new Process(r, poll.operations + "R");
                queue.offer(nextProcess);
                visited.add(r);
            }
        }
    }

    public static int D(int value) {
        return (value * 2) % 10000;
    }

    private static int S(int value) {
        return value == 0 ? 9999 : value - 1;
    }

    private static int L(int value) {
        int d1 = value / 1000;
        int remain = value % 1000;

        int d2 = remain / 100;
        remain = remain % 100;

        int d3 = remain / 10;
        remain = remain %10;

        int d4 = remain;

        return d2 * 1000 + d3 * 100 + d4 * 10 + d1;
    }

    private static int R(int value) {
        int d1 = value / 1000;
        int remain = value % 1000;

        int d2 = remain / 100;
        remain = remain % 100;

        int d3 = remain / 10;
        remain = remain %10;

        int d4 = remain;

        return d4 * 1000 + d1 * 100 + d2 * 10 + d3;
    }

    private static String convertToFormat(String sValue) {
        int length = sValue.length();

        StringBuilder st = new StringBuilder();
        for (int i = 0; i < 4 - length; i++) {
            st.append("0");
        }
        st.append(sValue);

        return st.toString();
    }
}
