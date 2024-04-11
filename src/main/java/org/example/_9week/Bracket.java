package org.example._9week;

import java.util.Arrays;
import java.util.Scanner;

public class Bracket {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        int answer = Arrays.stream(line.split("-"))
                .mapToInt(Bracket::calculatePlus)
                .reduce((x, y) -> x - y)
                .getAsInt();

        System.out.println(answer);
    }

    private static int calculatePlus(String expression) {
        String[] nums = expression.split("\\+");
        return Arrays.stream(nums).mapToInt(Integer::parseInt).sum();
    }
}
