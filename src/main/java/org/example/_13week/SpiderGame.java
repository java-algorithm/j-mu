package org.example._13week;

import java.util.Arrays;
import java.util.Scanner;

public class SpiderGame {

    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final int X = sc.nextInt();
        final int Y = sc.nextInt();


        final long Z = (Y * 100L / X);
        if (Z >= 99) {
            System.out.println(-1);
            return;
        }
        long right = Integer.MAX_VALUE;
        long left = 1;

        while (left <= right) {
            if (left == right) {
                System.out.println(left);
                return;
            }
            long mid = (left + right) / 2;

            final long newZ = ((Y + mid) * 100L) / (X + mid);
            if (newZ > Z) {
                right = mid;
            } else if (newZ == Z) {
                left = mid + 1;
            }
        }
    }
}
