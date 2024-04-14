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

    public static void main(String[] args) throws IOException {
        List<List<Integer>> blackRows = new LinkedList<>();
        for (int i = 0; i < boardSize + 1; i++) {
            blackRows.add(new ArrayList<>());
        }
        List<List<Integer>> blackCols = new LinkedList<>();
        for (int i = 0; i < boardSize + 1; i++) {
            blackCols.add(new ArrayList<>());
        }
        HashMap<Integer, List<Integer>> blackSlash = new HashMap<>();
        HashMap<Integer, List<Integer>> blackBackSlash = new HashMap<>();


        List<List<Integer>> whiteRows = new LinkedList<>();
        for (int i = 0; i < boardSize + 1; i++) {
            whiteRows.add(new ArrayList<>());
        }
        List<List<Integer>> whiteCols = new LinkedList<>();
        for (int i = 0; i < boardSize + 1; i++) {
            whiteCols.add(new ArrayList<>());
        }
        HashMap<Integer, List<Integer>> whiteSlash = new HashMap<>();
        HashMap<Integer, List<Integer>> whiteBackSlash = new HashMap<>();

        for (int i = 0; i < boardSize; i++) {
            final int[] row = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

            for (int j = 0; j < boardSize; j++) {
                board[i][j] = row[j];

                if (board[i][j] == BLACK) {
                    blackRows.get(i + 1).add(j + 1);
                    blackCols.get(j + 1).add(i + 1);

                    final List<Integer> slash = blackSlash.getOrDefault(i + j + 2, new ArrayList<>());
                    slash.add(i + 1);
                    blackSlash.put(i + j + 2, slash);

                    final List<Integer> backSlash = blackBackSlash.getOrDefault(i - j, new ArrayList<>());
                    backSlash.add(i + 1);
                    blackBackSlash.put(i - j, backSlash);
                } else if (board[i][j] == WHITE) {
                    whiteRows.get(i + 1).add(j + 1);
                    whiteCols.get(j + 1).add(i + 1);

                    final List<Integer> slash = whiteSlash.getOrDefault(i + j + 2, new ArrayList<>());
                    slash.add(i + 1);
                    whiteSlash.put(i + j + 2, slash);

                    final List<Integer> backSlash = whiteBackSlash.getOrDefault(i - j, new ArrayList<>());
                    backSlash.add(i + 1);
                    whiteBackSlash.put(i - 2, backSlash);
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

    private static boolean checkBackSlash(final HashMap<Integer, List<Integer>> slashes, final int color) {
        final Set<Integer> keys = slashes.keySet();
        for (final Integer key : keys) {
            final List<Integer> slash = slashes.get(key);

            if (slash.size() < 5) {
                continue;
            }

            Integer prev = 0;
            int continuousCount = 0;

            final int size = slash.size();
            for (int i = 0; i < size; i++) {
                final Integer cur = slash.get(i);
                if (continuousCount == 0) {
                    prev = cur;
                    continuousCount++;
                } else if (prev + 1 == cur) {
                    prev = cur;
                    continuousCount++;
                } else {
                    continuousCount = 0;
                    i--;
                }

                if (continuousCount == 5) {
                    boolean isExceed = false;

                    for (int j = i + 1; j < slash.size(); j++) {
                        final Integer next = slash.get(j);
                        if (cur + 1 == next) {
                            isExceed = true;
                            continuousCount = 0;
                        }
                    }

                    if (isExceed) {
                        continue;
                    }
                    System.out.println(color);
                    System.out.println((cur - 4) + " " + (cur - 4 - key));
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkSlash(final HashMap<Integer, List<Integer>> slashes, final int color) {
        final Set<Integer> keys = slashes.keySet();
        for (final Integer key : keys) {
            final List<Integer> slash = (List<Integer>) slashes.get(key);

            if (slash.size() < 5) {
                continue;
            }

            Integer prev = 0;
            int continuousCount = 0;

            final int size = slash.size();
            for (int i = 0; i < size; i++) {
                final Integer cur = slash.get(i);
                if (continuousCount == 0) {
                    prev = cur;
                    continuousCount++;
                } else if (prev + 1 == cur) {
                    prev = cur;
                    continuousCount++;
                } else {
                    continuousCount = 0;
                    i--;
                }

                if (continuousCount == 5) {
                    boolean isExceed = false;

                    for (int j = i + 1; j < slash.size(); j++) {
                        final Integer next = slash.get(j);
                        if (cur + 1 == next) {
                            isExceed = true;
                            continuousCount = 0;
                        }
                    }

                    if (isExceed) {
                        continue;
                    }
                    System.out.println(color);
                    System.out.println(cur + " " + (key - cur));
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkCol(final List<List<Integer>> cols, final int color) {
        final int size = cols.size();
        for (int i = 0; i < size; i++) {
            final List<Integer> col = cols.get(i);
            if (col.size() < 5) {
                continue;
            }


            Integer prev = 0;
            int continuousCount = 0;
            for (int j = 0; j < col.size(); j++) {
                final Integer cur = col.get(j);
                if (continuousCount == 0) {
                    prev = cur;
                    continuousCount++;
                } else if (prev + 1 == cur) {
                    prev = cur;
                    continuousCount++;
                } else {
                    continuousCount = 0;
                    i--;
                }

                if (continuousCount == 5) {
                    boolean isExceed = false;

                    for (int k = j + 1; j < col.size(); j++) {
                        final Integer next = col.get(k);
                        if (cur + 1 == next) {
                            isExceed = true;
                            continuousCount = 0;
                        }
                    }

                    if (isExceed) {
                        continue;
                    }
                    System.out.println(color);
                    System.out.println((cur - 4) + " " + (i));
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean checkRow(final List<List<Integer>> rows, int color) {
        final int size = rows.size();
        for (int i = 0; i < size; i++) {
            final List<Integer> row = rows.get(i);
            if (row.size() < 5) {
                continue;
            }


            Integer prev = 0;
            int continuousCount = 0;
            for (int j = 0; j < row.size(); j++) {
                final Integer cur = row.get(j);
                if (continuousCount == 0) {
                    prev = cur;
                    continuousCount++;
                } else if (prev + 1 == cur) {
                    prev = cur;
                    continuousCount++;
                } else {
                    continuousCount = 0;
                    i--;
                }

                if (continuousCount == 5) {
                    boolean isExceed = false;

                    for (int k = j + 1; j < row.size(); j++) {
                        final Integer next = row.get(k);
                        if (cur + 1 == next) {
                            isExceed = true;
                            continuousCount = 0;
                        }
                    }

                    if (isExceed) {
                        continue;
                    }
                    System.out.println(color);
                    System.out.println((i) + " " + (cur - 4));
                    return true;
                }
            }
        }

        return false;
    }
}
