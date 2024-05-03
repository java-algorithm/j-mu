package org.example._12week;

import java.io.BufferedReader;
import java.math.BigInteger;
import java.util.Scanner;

public class FactorialZeroCount {

    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final int value = sc.nextInt();

        BigInteger answer = new BigInteger("1");
        for (int i = 1; i <= value; i++) {
            final BigInteger index = new BigInteger(i + "");
            answer = answer.multiply(index);
        }

        int count = 0;
        final String answerStr = String.valueOf(answer);
        for (int i = answerStr.length() - 1; i > 0; i--) {
            final char c = answerStr.charAt(i);
            if (c == '0') {
                count++;
            } else {
                break;
            }
        }

        System.out.println(count);
    }
}
