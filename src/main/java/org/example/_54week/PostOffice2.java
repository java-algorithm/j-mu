package org.example._54week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PostOffice2 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] positions;
    private static int[] people;
    private static Village[] villages;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        villages = new Village[N];

        long total = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int position = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());

            villages[i] = new Village(position, cnt);
            total += cnt;
        }

        Arrays.sort(villages);
        long sum = 0;
        for (Village village : villages) {
            sum += village.cnt;

            if (total - 2 * sum <= 0) {
                System.out.println(village.position);
                return;
            }
        }
    }

    private static class Village implements Comparable<Village> {
        int position;
        int cnt;

        public Village(int position, int cnt) {
            this.position = position;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Village o) {
            return Integer.compare(this.position, o.position);
        }
    }
}
