package org.example._9week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Flip {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final boolean FRONT = true;
    private static final boolean BACK = false;
    private static final String SIG_FRONT = "0";

    private static boolean[][] arr;
    private static int count = 0;
    private static PriorityQueue<Coin> pq;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>();
        arr = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                arr[i][j] = (inputs[j].equals(SIG_FRONT)) ? FRONT : BACK;
                if (arr[i][j] == BACK) {
                    pq.add(new Coin(i, j));
                }
            }
        }
//        printArray(arr);

        while (!pq.isEmpty()) {
            Coin poll = pq.poll();
            swap(poll.x, poll.y);
        }

        System.out.println(count);
    }

    private static void swap(int x, int y) {
        if (arr[x][y] == FRONT) {
            return;
        }

        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                arr[i][j] = !arr[i][j];
                if (arr[i][j] == BACK) {
                    pq.add(new Coin(i, j));
                }
            }
        }
        count++;
//        printArray(arr);
    }

    private static class Coin implements Comparable<Coin> {
        int x;
        int y;

        public Coin(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int sum() {
            return x+y;
        }

        @Override
        public int compareTo(Coin o) {
            return Integer.compare(o.sum(),this.sum());
        }
    }

    private static void printArray(boolean[][] arr) {
        System.out.println("<<<< count : " + count + " >>>>>>");
        for (boolean[] booleans : arr) {
            for (boolean aBoolean : booleans) {
                System.out.print(aBoolean == FRONT ? "front " : "back ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();
    }
}
