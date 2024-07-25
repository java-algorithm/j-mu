package org.example._25week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Game2048 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int mapSize;
    private static final int EMPTY = 0;
    private static int maxValue = 0;
    private static int[] maxPath = new int[5];
    private static int UP = 0;
    private static int DOWN = 1;
    private static int RIGHT = 2;
    private static int LEFT = 3;

    public static void main(String[] args) throws IOException {
        mapSize = Integer.parseInt(br.readLine());
        final int[][] map = new int[mapSize][mapSize];

        for (int i = 0; i < mapSize; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxValue = Math.max(maxValue, map[i][j]);
            }
        }

        if (mapSize == 1) {
            System.out.println(maxValue);
            return;
        }

        int[] path = new int[5];
        dfs(0, map, path);
        System.out.println(maxValue);
        for (int dir : maxPath) {
            System.out.print(dir+" ");
        }
        System.out.println();
    }

    private static void dfs(int depth, int[][] map, int[] path) {
        if (depth == 5) {
            return;
        }

//        if (depth == 0) {
//            int[][] upSwipeCloneMap = upSwipe(map);
//            dfs(depth + 1, upSwipeCloneMap);
//        }
//
//        if (depth == 1) {
//            int[][] rightSwipeCloneMap = rightSwipe(map);
//            dfs(depth + 1, rightSwipeCloneMap);
//        }

        // left swipe
        path[depth] = LEFT;
        int[][] leftSwipeCloneMap = leftSwipe(map, path);
        dfs(depth + 1, leftSwipeCloneMap, path);

        // right swipe
        path[depth] = RIGHT;
        int[][] rightSwipeCloneMap = rightSwipe(map, path);
        dfs(depth + 1, rightSwipeCloneMap, path);

        // up swipe
        path[depth] = UP;
        int[][] upSwipeCloneMap = upSwipe(map, path);
        dfs(depth + 1, upSwipeCloneMap, path);

//         down swipe
        path[depth] = DOWN;
        int[][] downSwipeCloneMap = downSwipe(map, path);
        dfs(depth + 1, downSwipeCloneMap, path);
    }

    private static int[][] leftSwipe(final int[][] map, int[] path) {
        int[][] cloneMap = clone(map);

        for (int row = 0; row < mapSize; row++) {
            int pivot = 0;
            int startPoint = 0;
            for (int col = pivot; col < mapSize; col++) {
                if (cloneMap[row][col] != EMPTY) {
                    startPoint = col + 1;
                    cloneMap[row][pivot] = cloneMap[row][col];
                    if (col != pivot) {
                        cloneMap[row][col] = 0;
                    }
                    break;
                }
            }

            for (int col = startPoint; col < mapSize; col++) {
                if (cloneMap[row][col] == EMPTY) {
                    continue;
                }

                if (pivot == col) {
                    continue;
                }

                if (cloneMap[row][pivot] == cloneMap[row][col]) {
                    cloneMap[row][pivot] *= 2;
                    maxValue = Math.max(maxValue, cloneMap[row][pivot]);
                    maxPath = path.clone();
                    cloneMap[row][col] = 0;
                    pivot++;
                    continue;
                }

                if (cloneMap[row][pivot] == 0) {
                    cloneMap[row][pivot] = cloneMap[row][col];
                    cloneMap[row][col] = 0;
                } else {
                    col--;
                    pivot++;
                }
            }
        }

        return cloneMap;
    }

    private static int[][] upSwipe(final int[][] map, int[] path) {
        int[][] cloneMap = clone(map);
        for (int col = 0; col < mapSize; col++) {
            int pivot = 0;
            int startPoint = 0;
            for (int row = pivot; row < mapSize; row++) {
                if (cloneMap[row][col] != EMPTY) {
                    startPoint = row + 1;
                    cloneMap[pivot][col] = cloneMap[row][col];
                    if (row != pivot) {
                        cloneMap[row][col] = 0;
                    }
                    break;
                }
            }

            for (int row = startPoint; row < mapSize; row++) {
                if (cloneMap[row][col] == EMPTY) {
                    continue;
                }

                if (pivot == row) {
                    continue;
                }

                if (cloneMap[pivot][col] == cloneMap[row][col]) {
                    cloneMap[pivot][col] *= 2;
                    maxValue = Math.max(maxValue, cloneMap[pivot][col]);
                    maxPath = path.clone();

                    cloneMap[row][col] = 0;
                    pivot++;
                    continue;
                }

                if (cloneMap[pivot][col] == 0) {
                    cloneMap[pivot][col] = cloneMap[row][col];
                    cloneMap[row][col] = 0;
                } else {
                    row--;
                    pivot++;
                }
            }
        }

        return cloneMap;
    }

    private static int[][] rightSwipe(final int[][] map, int[] path) {
        int[][] cloneMap = clone(map);
        for (int row = 0; row < mapSize; row++) {
            int pivot = mapSize - 1;
            int startPoint = mapSize - 1;
            for (int col = pivot; col >= 0; col--) {
                if (cloneMap[row][col] != EMPTY) {
                    startPoint = col - 1;
                    cloneMap[row][pivot] = cloneMap[row][col];
                    if (col != pivot) {
                        cloneMap[row][col] = 0;
                    }
                    break;
                }
            }

            for (int col = startPoint; col >= 0; col--) {
                if (cloneMap[row][col] == EMPTY) {
                    continue;
                }

                if (pivot == col) {
                    continue;
                }

                if (cloneMap[row][pivot] == cloneMap[row][col]) {
                    cloneMap[row][pivot] *= 2;
                    maxValue = Math.max(maxValue, cloneMap[row][pivot]);
                    maxPath = path.clone();

                    cloneMap[row][col] = 0;
                    pivot--;
                    continue;
                }

                if (cloneMap[row][pivot] == 0) {
                    cloneMap[row][pivot] = cloneMap[row][col];
                    cloneMap[row][col] = 0;
                } else {
                    col++;
                    pivot--;
                }
            }
        }

        return cloneMap;
    }

    private static int[][] downSwipe(final int[][] map, int[] path) {
        int[][] cloneMap = clone(map);
        for (int col = 0; col < mapSize; col++) {
            int pivot = mapSize - 1;
            int startPoint = mapSize - 1;
            for (int row = pivot; row >= 0; row--) {
                if (cloneMap[row][col] != EMPTY) {
                    startPoint = row - 1;
                    cloneMap[pivot][col] = cloneMap[row][col];
                    if (row != pivot) {
                        cloneMap[row][col] = 0;
                    }
                    break;
                }
            }

            for (int row = startPoint; row >= 0; row--) {
                if (cloneMap[row][col] == EMPTY) {
                    continue;
                }

                if (pivot == row) {
                    continue;
                }

                if (cloneMap[pivot][col] == cloneMap[row][col]) {
                    cloneMap[pivot][col] *= 2;
                    maxValue = Math.max(maxValue, cloneMap[pivot][col]);
                    maxPath = path.clone();

                    cloneMap[row][col] = 0;
                    pivot--;
                    continue;
                }

                if (cloneMap[pivot][col] == 0) {
                    cloneMap[pivot][col] = cloneMap[row][col];
                    cloneMap[row][col] = 0;
                } else {
                    row++;
                    pivot--;
                }
            }
        }

        return cloneMap;
    }

    private static int[][] clone(int[][] map) {
        int[][] cloneMap = new int[mapSize][mapSize];

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                cloneMap[i][j] = map[i][j];
            }
        }

        return cloneMap;
    }
}
