package org.example._51week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sticker {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static boolean[][] map;
    private static int nr;
    private static int nc;
    private static int stickerCnt;

    private static boolean[][][] stickers;

    private static final int EMPTY = 0;
    private static final int STIKCER = 1;


    public static void main(String[] args) throws IOException {
        input();

//        printSticker();

        for (int stickerNo = 0; stickerNo < stickerCnt; stickerNo++) {
            boolean[][] sticker = stickers[stickerNo];

            for (int rotation = 0; rotation < 4; rotation++) {
                boolean isMatch = fullScan(sticker);

                if (isMatch) {
                    break;
                }

//                printSticker(stickerNo, sticker);
                sticker = rotation(sticker);
            }
        }

        int count = calculate();
        System.out.println(count);
    }

    private static boolean[][] rotation(boolean[][] sticker) {
        boolean[][] temp = new boolean[sticker[0].length][sticker.length];

        for (int row = 0; row < sticker.length; row++) {
            for (int col = 0; col < sticker[row].length; col++) {
                temp[col][sticker.length - row - 1] = sticker[row][col];
            }
        }

        return temp;
    }

    private static boolean fullScan(boolean[][] sticker) {
        for (int row = 0; row < nr; row++) {
            for (int col = 0; col < nc; col++) {
                if (row + sticker.length > nr || col + sticker[0].length > nc) {
                    continue;
                }

                boolean match = isMatch(row, col, sticker);
                if (match) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isMatch(int dr, int dc, boolean[][] sticker) {
        boolean[][] temp = new boolean[map.length][map[0].length];

        for (int row = 0; row < sticker.length; row++) {
            for (int col = 0; col < sticker[row].length; col++) {
                // sticker(T) and map(F)
                // sticker(F) and map(T | F )
                if (sticker[row][col] && map[row + dr][col + dc]) {
                    return false;
                }

                if (sticker[row][col]) {
                    temp[row+dr][col+dc] = true;
                }
            }
        }

        for (int row = 0; row < nr; row++) {
            for (int col = 0; col < nc; col++) {
                if(temp[row][col]){
                    map[row][col] = true;
                }
            }
        }

        return true;
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        nr = Integer.parseInt(st.nextToken());
        nc = Integer.parseInt(st.nextToken());
        map = new boolean[nr][nc];

        stickerCnt = Integer.parseInt(st.nextToken());
        stickers = new boolean[stickerCnt][][];

        for (int stickerNo = 0; stickerNo < stickerCnt; stickerNo++) {
            st = new StringTokenizer(br.readLine());

            int sr = Integer.parseInt(st.nextToken());
            int sc = Integer.parseInt(st.nextToken());

            stickers[stickerNo] = new boolean[sr][sc];
            for (int stickerRow = 0; stickerRow < sr; stickerRow++) {
                st = new StringTokenizer(br.readLine());
                for (int stickerCol = 0; stickerCol < sc; stickerCol++) {
                    stickers[stickerNo][stickerRow][stickerCol] = Integer.parseInt(st.nextToken()) == 1;
                }
            }
        }
    }

    private static int calculate() {
        int cnt = 0;
        for (int row = 0; row < nr; row++) {
            for (int col = 0; col < nc; col++) {
                if (map[row][col]) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private static void printSticker() {
        for (int i = 0; i < stickerCnt; i++) {
            printSticker(i, stickers[i]);
        }
    }

    private static void printSticker(int i, boolean[][] sticker) {
        System.out.println("sticker " + i + "입니다.");
        System.out.println();
        for (int row = 0; row < sticker.length; row++) {
            for (int col = 0; col < sticker[row].length; col++) {
                System.out.print((sticker[row][col] ? 1 : 0) + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}
