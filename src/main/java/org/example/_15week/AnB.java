package org.example._15week;

import java.util.Scanner;

public class AnB {

    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final StringBuilder start = new StringBuilder(sc.nextLine());
        final StringBuilder target = new StringBuilder(sc.nextLine());

        final int targetLength = target.length() - 1;
        final int startLength = start.length() - 1;
        for (int i = targetLength; i > startLength; i--) {
            if (target.charAt(i) == 'A') {
                target.deleteCharAt(i);
            } else {
                target.deleteCharAt(i);
                target.reverse();
            }
        }

        for (int i = 0; i <= startLength; i++) {
            if (start.charAt(i) != target.charAt(i)) {
                System.out.println(0);
                return;
            }
        }

        System.out.println(1);
    }
}
