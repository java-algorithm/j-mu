package org.example._52week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CardGame {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int totalCnt;
    private static int selectCnt;
    private static int gameCnt;
    private static int[] selectedNums;
    private static int[] games;
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        input();


    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        totalCnt = Integer.parseInt(st.nextToken());
        selectCnt = Integer.parseInt(st.nextToken());
        gameCnt = Integer.parseInt(st.nextToken());

        selectedNums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        games = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(selectedNums);
    }
}
