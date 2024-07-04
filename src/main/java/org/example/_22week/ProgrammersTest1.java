package org.example._22week;

public class ProgrammersTest1 {

    public static void main(String[] args) {
//        int[] a = {1, 2, 3, 4};
        int[] a = {-1, 0, 1};

//        int[] b = {-3, -1, 0, 2};
        int[] b = {1, 0, -1};
        final int answer = solution(a, b);
        System.out.println(answer);
    }

    public static int solution(int[] a, int[] b) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }

        return sum;
    }
}
