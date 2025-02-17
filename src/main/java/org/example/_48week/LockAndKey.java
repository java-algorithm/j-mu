package org.example._48week;

class LockAndKey {
    private static int N = 0;
    private static int M = 0;

    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};

        boolean solution = solution(key, lock);
        System.out.println(solution);
    }
    public static boolean solution(int[][] key, int[][] lock) {
        N = lock.length;
        M = key.length;

        int[][][] keys = makeRotationKeys(key);
        int[][] newLock = makeExtensionLock(lock);

        for (int row = 0; row < newLock.length; row++) {
            for (int col = 0; col < newLock.length; col++) {
                for (int rotation = 0; rotation < 4; rotation++) {
                    boolean isMatch = isMatch(keys[rotation], newLock, row, col);
                    if (isMatch) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static int[][][] makeRotationKeys(int[][] key) {
        int[][][] keys = new int[4][M][M];
        keys[0] = key;

        for (int i = 1; i < 4; i++) {
            for (int row = 0; row < key.length; row++) {
                for (int col = 0; col < key[0].length; col++) {
                    keys[i][col][key.length-1-row] = keys[i - 1][row][col];
                }
            }
        }

        return keys;
    }

    private static int[][] makeExtensionLock(int[][] lock) {
        int newLength = N + 2 * M - 2;
        int[][] newLock = new int[newLength][newLength];

        for (int row = 0; row < newLength; row++) {
            for (int col = 0; col < newLength; col++) {
                newLock[row][col] = 3;
            }
        }

        for (int row = M - 1, originalRow = 0; row < M - 1 + N; row++, originalRow++) {
            for (int col = M - 1, originalCol = 0; col < M - 1 + N; col++, originalCol++) {
                newLock[row][col] = lock[originalRow][originalCol];
            }
        }

        return newLock;
    }

    public static boolean isMatch(int[][] key, int[][] lock, int rowIndex, int colIndex) {
        for (int row = M - 1; row < M - 1 + N; row++) {
            for (int col = M - 1; col < M - 1 + N; col++) {
                if (((row - rowIndex) < 0 || (row - rowIndex) >= M) || ((col - colIndex) < 0 || (col - colIndex) >= M)){
                    if(lock[row][col]==0){
                        return false;
                    }

                    continue;
                }

                if (lock[row][col] == 0 && key[row - rowIndex][col - colIndex] == 0) {
                    return false;
                }

                if (lock[row][col] == 1 && key[row - rowIndex][col - colIndex] == 1) {
                    return false;
                }
            }
        }

        return true;
    }
}
