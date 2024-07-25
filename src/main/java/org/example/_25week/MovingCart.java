package org.example._25week;

public class MovingCart {

    private static final int ROW = 0;
    private static final int COL = 1;

    private static final int EMPTY = 0;
    private static final int RED_CART_STARTING_POINT = 1;
    private static final int BLUE_CART_STARTING_POINT = 2;
    private static final int RED_CART_TARGET_POINT = 3;
    private static final int BLUE_CART_TARGET_POINT = 4;
    private static final int WALL = 5;

    private static int mapRowSize;
    private static int mapColSize;

    private static int maxValue = 0;

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    private static int[][] map;

    public static void main(String[] args) {
        int[][] map = {{1, 4}, {0, 0}, {2, 3}};
        int solution = solution(map);
        System.out.println(solution);
    }

    private static int solution(int[][] _map) {
        map = _map;
        mapRowSize = map.length;
        mapColSize = map[0].length;

        int[] redCart = new int[2];
        int[] blueCart = new int[2];
        int[] redCartTargetPoint = new int[2];
        int[] blueCartTargetPoint = new int[2];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
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
        CartPosition carts = new CartPosition(redCart, blueCart);
        dfs(carts, 0, redCartVisited, blueCartVisited);

        return maxValue;
    }

    private static void dfs(CartPosition carts, int depth, boolean[][] redCartVisited, boolean[][] blueCartVisited) {
        int[] redCurPosition = carts.redCart;
        int[] blueCurPosition = carts.blueCart;

        for (int i = 0; i < 4; i++) {
            int nextRedRow = redCurPosition[ROW] + dr[i];
            int nextRedCol = redCurPosition[COL] + dc[i];

            if (nextRedRow < 0 || nextRedRow >= mapRowSize || nextRedCol < 0 || nextRedCol >= mapColSize) {
                continue;
            }

            // 잠만 이건 아닌데?
            if (nextRedRow == blueCurPosition[ROW] && nextRedCol == blueCurPosition[COL]) {
                continue;
            }


        }
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
