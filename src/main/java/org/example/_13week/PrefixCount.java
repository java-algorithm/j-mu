package org.example._13week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class PrefixCount {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        List<String> stringSet = new ArrayList<>();

        // input 집합
        for (int i = 0; i < N; i++) {
            final String line = br.readLine();
            stringSet.add(line);
        }
        Collections.sort(stringSet);

        for (int i = 0; i < M; i++) {
            final String testCase = br.readLine(); //aa

            int left = 0;
            int right = stringSet.size() - 1;

            while (left <= right) {
                int mid = (left + right) / 2;

                final String foundString = stringSet.get(mid);
                if (foundString.startsWith(testCase)) {
                    answer++;
                    break;
                }

                final int result = foundString.compareTo(testCase);
                if (result > 0) {
                    right = mid - 1;
                } else {// result < 0
                    left = mid + 1;
                }
            }
        }

        System.out.println(answer);
    }
}
