package org.example._10week;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Josephus {

    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final StringTokenizer st = new StringTokenizer(sc.nextLine());

        final int N = Integer.parseInt(st.nextToken());
        final int K = Integer.parseInt(st.nextToken());

        LinkedList<Integer> list = new LinkedList();
        // index연산 편하게 하기 위해 처음에 0삽입.
        for (int i = 0; i <= N; i++) {
            list.add(i);
        }

        StringBuilder answer = new StringBuilder("<");

        int index = 0;
        int listSize = N;
        while (list.size() > 1) {
            index += K;
            if (index > listSize) {
                index = index % listSize;
            }
            if (index == 0) {
                index = listSize;
            }
            final Integer removed = list.remove(index);
            listSize--;
            index--;
            answer.append(removed + ", ");
        }

        answer.deleteCharAt(answer.length() - 1); //" "
        answer.deleteCharAt(answer.length() - 1); //","

        answer.append(">");

        System.out.println(answer);
    }
}
