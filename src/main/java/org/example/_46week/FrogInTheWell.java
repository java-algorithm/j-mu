package org.example._46week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class FrogInTheWell {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int M;
    private static int[] weights;
//    private static boolean[][] relations;
    private static ArrayList<Integer>[] relations;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        weights = new int[N + 1];
        relations = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++) {
            relations[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a1 = Integer.parseInt(st.nextToken());
            int a2 = Integer.parseInt(st.nextToken());

            relations[a1].add(a2);
            relations[a2].add(a1);
        }

        int count = 0;
        for (int i = 1; i < N+1; i++) {
            boolean best = true;

            for (int j = 0; j < relations[i].size(); j++) {
                Integer adj = relations[i].get(j);
                if( weights[i] <= weights[adj]){
                    best = false;
                    break;
                }
            }

            if (best) {
                count++;
            }
        }

        System.out.println(count);
    }
}
