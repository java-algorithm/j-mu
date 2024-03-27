package org.example._6week;

public class Fibonacci {

    public static void main(String[] args) {
        int number = 105;

        int answer = fibonacci(number);
        System.out.println(answer);
    }

    private static int fibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }

        int middleAnswer = fibonacci(n - 1) + fibonacci(n - 2);
        System.out.println(middleAnswer);
        return middleAnswer;
    }
}
