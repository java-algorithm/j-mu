package org.example._33week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class NewGame2 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final int[] dr = {0, 0, 0, -1, 1};
    private static final int[] dc = {0, 1, -1, 0, 0};

    private static final int WHITE = 0;
    private static final int RED = 1;
    private static final int BLUE = 2;

    private static int mapSize;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        mapSize = Integer.parseInt(st.nextToken()) + 1;
        int tokenCount = Integer.parseInt(st.nextToken());

        List<Token>[][] tokenMap = new List[mapSize][mapSize];
        int[][] map = new int[mapSize][mapSize];
        for (int row = 1; row < mapSize; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col < mapSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
                tokenMap[row][col] = new ArrayList<>();
            }
        }

        List<Token> tokens = new ArrayList<>();
        for (int i = 0; i < tokenCount; i++) {
            st = new StringTokenizer(br.readLine());

            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            Token token = new Token(row, col, dir);
            tokens.add(token);
            tokenMap[row][col].add(token);
        }

        // Game Start
        for (int depth = 1; depth <= 1000; depth++) {
            for (Token token : tokens) {
                int currentTokenRow = token.row;
                int currentTokenCol = token.col;
                int nextRow = currentTokenRow + dr[token.dir];
                int nextCol = currentTokenCol + dc[token.dir];

                if (!isInMap(nextRow, nextCol) || map[nextRow][nextCol] == BLUE) {
                    token.reverseDir();
                    nextRow = currentTokenRow + dr[token.dir];
                    nextCol = currentTokenCol + dc[token.dir];

                    if (!isInMap(nextRow, nextCol) || map[nextRow][nextCol] == BLUE) {
                        continue;
                    }
                }

                if (map[nextRow][nextCol] == WHITE) {
                    // 1 step : tokenMap에서 몇번째 인덱스인지를 찾는다.
                    int currentTokenIndex = token.index;
                    int currentMapSize = tokenMap[currentTokenRow][currentTokenCol].size();

                    // 2 step : 다 이동시킨다.
                    for (int idx = currentTokenIndex; idx < currentMapSize; idx++) {
                        Token shouldMoveToken = tokenMap[currentTokenRow][currentTokenCol].get(idx);
                        int nextIndex = tokenMap[nextRow][nextCol].size();
                        tokenMap[nextRow][nextCol].add(shouldMoveToken);
                        shouldMoveToken.row = nextRow;
                        shouldMoveToken.col = nextCol;
                        shouldMoveToken.index = nextIndex;
                    }

                    // 3 step : 기존 데이터 삭제한다.
                    for (int i = currentMapSize - 1; i >= currentTokenIndex; i--) {
                        tokenMap[currentTokenRow][currentTokenCol].remove(i);
                    }

                    // 4 step : 한 cell에 token이 4개 이상이면 게임 종료.
                    if (tokenMap[nextRow][nextCol].size() >= 4) {
                        System.out.println(depth);
                        return;
                    }

                    continue;
                }

                if (map[nextRow][nextCol] == RED) {
// 1 step : tokenMap에서 몇번째 인덱스인지를 찾는다.
                    int currentTokenIndex = token.index;
                    int currentMapSize = tokenMap[currentTokenRow][currentTokenCol].size();

                    // 2 step : 다 이동시킨다.
                    for (int idx = currentMapSize - 1; idx >= currentTokenIndex; idx--) {
                        Token shouldMoveToken = tokenMap[currentTokenRow][currentTokenCol].get(idx);
                        int nextIndex = tokenMap[nextRow][nextCol].size();
                        tokenMap[nextRow][nextCol].add(shouldMoveToken);
                        shouldMoveToken.row = nextRow;
                        shouldMoveToken.col = nextCol;
                        shouldMoveToken.index = nextIndex;
                    }

                    // 3 step : 기존 데이터 삭제한다.
                    for (int i = currentMapSize - 1; i >= currentTokenIndex; i--) {
                        tokenMap[currentTokenRow][currentTokenCol].remove(i);
                    }

                    // 4 step : 한 cell에 token이 4개 이상이면 게임 종료.
                    if (tokenMap[nextRow][nextCol].size() >= 4) {
                        System.out.println(depth);
                        return;
                    }
                }
            }
        }

        System.out.println(-1);
    }

    private static boolean isInMap(int nextRow, int nextCol) {
        return !(nextRow >= mapSize || nextRow <= 0 || nextCol >= mapSize || nextCol <= 0);
    }

    private static class Token {
        int row;
        int col;
        int dir;
        int index;

        public Token(int row, int col, int dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.index = 0;
        }

        public void reverseDir() {
            switch (dir) {
                case 1:
                    dir = 2;
                    break;
                case 2:
                    dir = 1;
                    break;
                case 3:
                    dir = 4;
                    break;
                case 4:
                    dir = 3;
                    break;
            }
        }
    }
}
