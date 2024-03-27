package org.example._5week;

import java.util.Arrays;
import java.util.Scanner;

public class kthNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine());
        int K = Integer.parseInt(scanner.nextLine());

        int[][] array = new int[N][N];
        int[] counts = new int[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                array[i][j] = i * j;
                if (array[i][j] >= K) {
                    counts[i]++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();

        Arrays.stream(counts).forEach(System.out::println);
    }
}
