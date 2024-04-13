package org.example._9week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

//https://www.acmicpc.net/problem/15903
public class MergingCard {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        final StringTokenizer st = new StringTokenizer(br.readLine());
        final int cardCount = Integer.parseInt(st.nextToken());
        final int mergingCount = Integer.parseInt(st.nextToken());

        final List<Long> cards
            = Arrays.stream(br.readLine().split(" "))
            .map(Long::parseLong)
            .collect(Collectors.toList());

        final PriorityQueue<Long> queue = new PriorityQueue<>(cards);

        for (int i = 0; i < mergingCount; i++) {
            final Long smallCard1 = queue.poll();
            final Long smallCard2 = queue.poll();

            // n>=2이므로 poll했을 때, 원소가 없는 경우는 걱정하지 않아도됨.
            final long sum = smallCard1 + smallCard2;
            queue.offer(sum);
            queue.offer(sum);
        }

        final long sum = queue.stream().mapToLong(Long::longValue).sum();
        System.out.println(sum);
    }
}
