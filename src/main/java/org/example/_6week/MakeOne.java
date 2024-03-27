package org.example._6week;

import java.util.Scanner;

public class MakeOne {

    // 해당 값까지 오기위한 count를 caching해놓음.
    private static int[] cache = new int[1_000_001];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        cache[1] = 0;

        makeN(n);

        System.out.println(cache[n]);
    }

    private static void makeN(final int n) {
        for (int i = 1; i <= n; i++) {
            // 3을 곱하기
            if (i * 3 <= n) {
                int i3 = cache[i * 3];

                if (i3 == 0 || i3 > cache[i] + 1) {
                    cache[i * 3] = cache[i] + 1;
                }
            }

            // 2를 곱하기
            if(i*2 <= n){
                int i2 = cache[i * 2];

                if (i2 == 0 || i2 > cache[i] + 1) {
                    cache[i * 2] = cache[i] + 1;
                }
            }

            // 1을 더하기
            if (i + 1 <= n) {
                int i1 = cache[i + 1];

                if (i1 == 0 || i1 > cache[i] + 1) {
                    cache[i + 1] = cache[i] + 1;
                }
            }
        }
    }
}
