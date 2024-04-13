package org.example._9week;

import java.util.Scanner;

import static java.lang.Math.pow;

public class EatingChocolate {

    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int N = 0;
        int chocolateSize;
        for (int i = 1; ; i++) {
            final int pow = (int) pow(2, i);
            if (pow >= K) {
                N = i;
                chocolateSize = pow;
                break;
            }
        }


        int minValue = 0;
        for (int i = N; i >= 0; i--) {
            final int pow = (int) pow(2, i);
            if (K >= pow) {
                K -= pow;

                if (K == 0) {
                    minValue = i;
                    break;
                }
            }
        }

        int divideCount = N - minValue;
        System.out.println(chocolateSize + " " + divideCount);
    }
}
