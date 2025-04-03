package org.example._52week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Mutalisk {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int[] nums;
    private static boolean[][][] visited;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        nums = new int[3]; // 3 0 0
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        visited = new boolean[61][61][61];

        dfs(nums[0], nums[1], nums[2], 0);
        System.out.println(answer);
    }

    private static void dfs(int a, int b, int c, int depth) {
        a = Math.max(0, a);
        b = Math.max(0, b);
        c = Math.max(0, c);

        if (a == 0 && b == 0 && c == 0) {
            answer = Math.min(answer, depth);
            return;
        }

        int[] arr = new int[]{a, b, c};
        Arrays.sort(arr); // 0 0 60
        a = arr[2];
        b = arr[1];
        c = arr[0];

        if (visited[a][b][c]) {
            return;
        }

        if (depth > answer) {
            return;
        }

        visited[a][b][c] = true;
        dfs(a - 9, b - 3, c - 1, depth + 1);
        dfs(a - 9, b - 1, c - 3, depth + 1);
        dfs(a - 3, b - 9, c - 1, depth + 1);
        dfs(a - 3, b - 1, c - 9, depth + 1);
        dfs(a - 1, b - 3, c - 9, depth + 1);
        dfs(a - 1, b - 9, c - 3, depth + 1);
    }
}
