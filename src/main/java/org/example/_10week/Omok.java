package org.example._10week;

import javax.swing.text.Position;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Omok {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] board = new int[19][19];
    private static final int boardSize = 19;

    private static int BLACK = 1;
    private static int WHITE = 2;

    private static int INITIAL_CAPACITY = 20;
    private static int INITIAL_MAP_CAPACITY = 30;

    public static void main(String[] args) throws IOException {
        Map<Integer, List<Integer>> blackRows = new HashMap<>(INITIAL_MAP_CAPACITY);
        Map<Integer, List<Integer>> blackCols = new HashMap<>(INITIAL_MAP_CAPACITY);
        Map<Integer, List<Integer>> blackSlash = new HashMap<>(INITIAL_MAP_CAPACITY);
        Map<Integer, List<Integer>> blackBackSlash = new HashMap<>(INITIAL_MAP_CAPACITY);

        Map<Integer, List<Integer>> whiteRows = new HashMap<>(INITIAL_MAP_CAPACITY);
        Map<Integer, List<Integer>> whiteCols = new HashMap<>(INITIAL_MAP_CAPACITY);
        Map<Integer, List<Integer>> whiteSlash = new HashMap<>(INITIAL_MAP_CAPACITY);
        Map<Integer, List<Integer>> whiteBackSlash = new HashMap<>(INITIAL_MAP_CAPACITY);

        for (int i = 0; i < boardSize; i++) {
            final int[] row = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

            for (int j = 0; j < boardSize; j++) {
                board[i][j] = row[j];

                if (board[i][j] == BLACK) {
                    initializeRows(blackRows, i, j);
                    initializeCols(blackCols, i, j);
                    initializeSlash(blackSlash, i, j);
                    initializeBackSlash(blackBackSlash, i, j);
                } else if (board[i][j] == WHITE) {
                    initializeRows(whiteRows, i, j);
                    initializeCols(whiteCols, i, j);
                    initializeSlash(whiteSlash, i, j);
                    initializeBackSlash(whiteBackSlash, i, j);
                }
            }
        }

        // 일단 초기화 했으니 하나하나씩...
        if (
            checkRow(blackRows, BLACK) ||
                checkRow(whiteRows, WHITE) ||
                checkCol(blackCols, BLACK) ||
                checkCol(whiteCols, WHITE) ||
                checkSlash(blackSlash, BLACK) ||
                checkSlash(whiteSlash, WHITE) ||
                checkBackSlash(blackBackSlash, BLACK) ||
                checkBackSlash(whiteBackSlash, WHITE)
        ) {
            return;
        }

        System.out.println(0);
    }

    private static void initializeRows(final Map<Integer, List<Integer>> rows, int i, int j) {
        final List<Integer> row = rows.getOrDefault(i + 1, new ArrayList<>(INITIAL_CAPACITY));
        row.add(j + 1);
        rows.put(i + 1, row);
    }

    private static void initializeCols(final Map<Integer, List<Integer>> cols, final int i, final int j) {
        final List<Integer> col = cols.getOrDefault(j + 1, new ArrayList<>(INITIAL_CAPACITY));
        col.add(i + 1);
        cols.put(j + 1, col);
    }

    private static void initializeSlash(final Map<Integer, List<Integer>> slashes, final int i, final int j) {
        final List<Integer> slash = slashes.getOrDefault(i + j + 2, new ArrayList<>(INITIAL_CAPACITY));
        slash.add(i + 1);
        slashes.put(i + j + 2, slash);
    }

    private static void initializeBackSlash(final Map<Integer, List<Integer>> backSlashes, final int i, final int j) {
        final List<Integer> backSlash = backSlashes.getOrDefault(i - j, new ArrayList<>(INITIAL_CAPACITY));
        backSlash.add(i + 1);
        backSlashes.put(i - j, backSlash);
    }

    private static boolean checkRow(final Map<Integer, List<Integer>> rows, int color) {
        final Set<Integer> keys = rows.keySet();
        for (Integer key : keys) {
            final List<Integer> row = rows.get(key);
            final int rowSize = row.size();

            if (rowSize < 5) {
                continue;
            }

            Integer prev = 0;
            Integer cur = -1;
            int continuousCount = 0;

            for (int j = 0; j < rowSize;) {
                cur = row.get(j);
                if (prev + 1 == cur || continuousCount == 0) {
                    prev = cur;
                    continuousCount++;
                    j++;
                } else if (continuousCount == 5) {
                    System.out.println(color);
                    System.out.println((key) + " " + (prev - 4));
                    return true;
                } else {
                    continuousCount = 0;
                }
            }

            if (continuousCount == 5) {
                System.out.println(color);
                System.out.println((key) + " " + (cur - 4));
                return true;
            }
        }
        return false;
    }

    private static boolean checkCol(final Map<Integer, List<Integer>> cols, final int color) {
        final Set<Integer> keys = cols.keySet();
        for (Integer key : keys) {
            final List<Integer> col = cols.get(key);
            final int colSize = col.size();

            if (colSize < 5) {
                continue;
            }


            int continuousCount = 0;
            Integer prev = 0;
            Integer cur = -1;

            for (int j = 0; j < colSize;) {
                cur = col.get(j);
                if (prev + 1 == cur || continuousCount == 0) {
                    prev = cur;
                    continuousCount++;
                    j++;
                } else if (continuousCount == 5) {
                    System.out.println(color);
                    System.out.println((prev - 4) + " " + (key));
                    return true;
                } else {
                    continuousCount = 0;
                }
            }

            if (continuousCount == 5) {
                System.out.println(color);
                System.out.println((cur - 4) + " " + (key));
                return true;
            }
        }

        return false;
    }

    private static boolean checkSlash(final Map<Integer, List<Integer>> slashes, final int color) {
        final Set<Integer> keys = slashes.keySet();
        for (final Integer key : keys) {
            final List<Integer> slash = slashes.get(key);
            final int slashSize = slash.size();

            if (slashSize < 5) {
                continue;
            }

            Integer prev = 0;
            Integer cur = -1;
            int continuousCount = 0;

            for (int i = 0; i < slashSize;) {
                cur = slash.get(i);
                if (prev + 1 == cur || continuousCount == 0) {
                    prev = cur;
                    continuousCount++;
                    i++;
                } else if (continuousCount == 5) {
                    System.out.println(color);
                    System.out.println(prev + " " + (key - prev));
                    return true;
                } else {
                    continuousCount = 0;
                }
            }

            if (continuousCount == 5) {
                System.out.println(color);
                System.out.println(cur + " " + (key - cur));
                return true;
            }
        }
        return false;
    }

    private static boolean checkBackSlash(final Map<Integer, List<Integer>> backSlashes, final int color) {
        final Set<Integer> keys = backSlashes.keySet();
        for (final Integer key : keys) {
            final List<Integer> slash = backSlashes.get(key);

            final int backSlashSize = slash.size();
            if (backSlashSize < 5) {
                continue;
            }

            Integer prev = 0;
            int continuousCount = 0;
            Integer cur = -1;

            for (int i = 0; i < backSlashSize;) {
                cur = slash.get(i);

                if (prev + 1 == cur || continuousCount == 0) {
                    prev = cur;
                    continuousCount++;
                    i++;
                } else if (continuousCount == 5) {
                    System.out.println(color);
                    System.out.println((prev - 4) + " " + (prev - 4 - key));
                    return true;
                } else {
                    continuousCount = 0;
                }
            }

            if (continuousCount == 5) {
                System.out.println(color);
                System.out.println((cur - 4) + " " + (cur - 4 - key));
                return true;
            }
        }
        return false;
    }
}
