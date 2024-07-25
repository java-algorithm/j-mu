package org.example._25week;

import java.util.ArrayList;
import java.util.List;

public class MovingCart {

    private static final int ROW = 0;
    private static final int COL = 1;

    private static final int EMPTY = 0;
    private static final int RED_CART_STARTING_POINT = 1;
    private static final int BLUE_CART_STARTING_POINT = 2;
    private static final int RED_CART_TARGET_POINT = 3;
    private static final int BLUE_CART_TARGET_POINT = 4;
    private static final int WALL = 5;

    private static final int RED = 0;
    private static final int BLUE = 1;

    private static int mapRowSize;
    private static int mapColSize;

    private static int maxValue = Integer.MAX_VALUE;

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    private static int[][] map;

    public static void main(String[] args) {
//        int[][] map = {{1, 4}, {0, 0}, {2, 3}};
        int[][] map = {{1, 0, 2}, {0, 0, 0}, {5, 0, 5}, {4, 0, 3}};

        int solution = solution(map);
        System.out.println(solution);
    }

    private static int solution(int[][] maze) {
        map = maze;
        mapRowSize = map.length;
        mapColSize = map[0].length;

        int[] redCart = new int[2];
        int[] blueCart = new int[2];
        int[] redCartTargetPoint = new int[2];
        int[] blueCartTargetPoint = new int[2];

        for (int i = 0; i < mapRowSize; i++) {
            for (int j = 0; j < mapColSize; j++) {
                int value = map[i][j];
                switch (value) {
                    case RED_CART_STARTING_POINT:
                        redCart = new int[]{i, j};
                        break;
                    case RED_CART_TARGET_POINT:
                        redCartTargetPoint = new int[]{i, j};
                        break;
                    case BLUE_CART_STARTING_POINT:
                        blueCart = new int[]{i, j};
                        break;
                    case BLUE_CART_TARGET_POINT:
                        blueCartTargetPoint = new int[]{i, j};
                        break;
                    default:
                        break;
                }
            }
        }

        boolean[][] redCartVisited = new boolean[mapRowSize][mapColSize];
        boolean[][] blueCartVisited = new boolean[mapRowSize][mapColSize];
        redCartVisited[redCart[ROW]][redCart[COL]] = true;
        blueCartVisited[blueCart[ROW]][blueCart[COL]] = true;

        CartPosition carts = new CartPosition(redCart, blueCart);
        dfs(carts, 0, redCartVisited, blueCartVisited, redCartTargetPoint, blueCartTargetPoint);

        return maxValue == Integer.MAX_VALUE ? 0 : maxValue;
    }

    private static void dfs(
            CartPosition carts,
            int depth,
            boolean[][] redCartVisited,
            boolean[][] blueCartVisited,
            int[] redCartTargetPoint,
            int[] blueCartTargetPoint
    ) {
        int[] redCurPosition = carts.redCart;
        int[] blueCurPosition = carts.blueCart;

        if (
                isSamePosition(redCurPosition, redCartTargetPoint) &&
                        isSamePosition(blueCurPosition, blueCartTargetPoint)
        ) {
            maxValue = Math.min(maxValue, depth);
            return;
        }

        // 둘 중 하나 도착한 경우 처리해줘야함.
        List<int[]> validNextRedPositions = createNextPosition(redCartVisited, redCurPosition, redCartTargetPoint);
        List<int[]> validNextBluePositions = createNextPosition(blueCartVisited, blueCurPosition, blueCartTargetPoint);

        List<CartPosition> validPositionCombinations = createPositionCombinations(validNextRedPositions, validNextBluePositions, redCurPosition, blueCurPosition);

        for (CartPosition validPositionCombination : validPositionCombinations) {
            int[] redCart = validPositionCombination.redCart;
            int[] blueCart = validPositionCombination.blueCart;

            redCartVisited[redCart[ROW]][redCart[COL]] = true;
            blueCartVisited[blueCart[ROW]][blueCart[COL]] = true;

            dfs(validPositionCombination, depth + 1, redCartVisited, blueCartVisited, redCartTargetPoint, blueCartTargetPoint);

            redCartVisited[redCart[ROW]][redCart[COL]] = false;
            blueCartVisited[blueCart[ROW]][blueCart[COL]] = false;
        }
    }

    private static boolean isSamePosition(int[] position1, int[] position2) {
        return position1[ROW] == position2[ROW] && position1[COL] == position2[COL];
    }

    // 둘 중 하나 비어있는 경우 처리해줘야함.
    private static List<CartPosition> createPositionCombinations(
            List<int[]> validNextRedPositions,
            List<int[]> validNextBluePositions,
            int[] prevRedPosition,
            int[] prevBluePosition
    ) {
        List<CartPosition> validPositionCombinations = new ArrayList<>();

        for (int[] validNextRedPosition : validNextRedPositions) {
            for (int[] validNextBluePosition : validNextBluePositions) {
                if (isSamePosition(validNextRedPosition, validNextBluePosition)) {
                    continue;
                }

                if (isSamePosition(validNextRedPosition, prevBluePosition) && isSamePosition(validNextBluePosition, prevRedPosition)) {
                    continue;
                }

                CartPosition combination = new CartPosition(validNextRedPosition, validNextBluePosition);
                validPositionCombinations.add(combination);
            }
        }

        return validPositionCombinations;
    }

    private static List<int[]> createNextPosition(boolean[][] cartVisited, int[] curPosition, int[] targetPosition) {
        List<int[]> validNextPosition = new ArrayList<>();
        if (isSamePosition(curPosition, targetPosition)) {
            validNextPosition.add(curPosition);
            return validNextPosition;
        }

        for (int i = 0; i < 4; i++) {
            int nextRow = curPosition[ROW] + dr[i];
            int nextCol = curPosition[COL] + dc[i];

            if (nextRow < 0 || nextRow >= mapRowSize || nextCol < 0 || nextCol >= mapColSize) {
                continue;
            }

            if (cartVisited[nextRow][nextCol]) {
                continue;
            }

            if (map[nextRow][nextCol] == WALL) {
                continue;
            }

            validNextPosition.add(new int[]{nextRow, nextCol});
        }

        return validNextPosition;
    }

    private static class CartPosition {
        private int[] redCart;
        private int[] blueCart;

        public CartPosition(int[] redCart, int[] blueCart) {
            this.redCart = redCart;
            this.blueCart = blueCart;
        }
    }
}
