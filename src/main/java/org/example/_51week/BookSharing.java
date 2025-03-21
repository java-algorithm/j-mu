package org.example._51week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BookSharing {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int testCnt = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < testCnt; i++) {
            solution();
        }
    }

    private static void solution() throws IOException {
        // input
        StringTokenizer st;
        PriorityQueue<int[]> students = new PriorityQueue<>((e1, e2) -> {
            int sCompare = Integer.compare(e1[0], e2[0]);
            if (sCompare == 0) {
                return Integer.compare(e1[1], e2[1]);
            }

            return sCompare;
        });

        st = new StringTokenizer(br.readLine());
        int bookCnt = Integer.parseInt(st.nextToken());
        int studentCnt = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        students.add(new int[]{s, e});

        // solution
        int cnt = 0;
        for (int sIdx = 1; sIdx <= bookCnt; sIdx++) {
            int[] student = new int[1];

            while (!students.isEmpty()) {
                student = students.poll();
                int start = student[0];
                int end = student[1];

                if (start >= sIdx) {
                    sIdx = start;
                    cnt++;
                    break;
                }
            }
        }

        System.out.println(cnt);
    }
}
