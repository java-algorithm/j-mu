package org.example._1week;

import java.util.Arrays;
import java.util.Scanner;

// https://www.acmicpc.net/problem/2012
public class Ranking {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] expectedRank = new int[N];
        int i = 0;
        while (sc.hasNextInt()) {
            expectedRank[i++] = sc.nextInt();
        }

        expectedRank = Arrays.stream(expectedRank).sorted().toArray();
        System.out.println(expectedRank);

    }
}
