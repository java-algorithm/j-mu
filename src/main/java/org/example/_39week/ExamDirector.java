package org.example._39week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ExamDirector {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] rooms = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int mainDirectorCapacity = Integer.parseInt(st.nextToken());
        int subDirectorCapacity = Integer.parseInt(st.nextToken());

        long answer = 0;

        for (int i = 0; i < N; i++) {
            int rest = rooms[i] - mainDirectorCapacity;
            answer++;

            if (rest > 0) {
                answer += rest % subDirectorCapacity == 0 ? rest / subDirectorCapacity : rest / subDirectorCapacity + 1;
            }
        }

        System.out.println(answer);
    }
}
