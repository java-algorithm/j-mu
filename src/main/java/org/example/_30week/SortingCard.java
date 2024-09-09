package org.example._30week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class SortingCard {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        if (N == 1) {
            System.out.println(0);
            return;
        }

        int sum = 0;
        while (pq.size() > 1) {
            Integer num1 = pq.poll();
            Integer num2 = pq.poll();

            sum += num1 + num2;
            pq.add(num1 + num2);
        }

        System.out.println(sum);
    }
}

