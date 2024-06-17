package org.example._20week;

import java.util.*;

public class FillPuzzle {

    private static final int ROW = 0;
    private static final int COL = 1;

    private static final int EMPTY = 0;
    private static final int PIECE = 1;
    private static final int BLOCK = 1;

    public static final int DIRECTION_SIZE = 4;

    private static int gameBoardMaxRow;
    private static int gameBoardMaxCol;
    private static boolean[][] visited;

    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, -1, 0, 1};

    private static int[] scores = {1, 2, 3, 4};
    private static final int PIVOT = 5;

    public static void main(String[] args) {
        int[][] gameBoard = {
                {1, 1, 0, 0, 1, 0},
                {0, 0, 1, 0, 1, 0},
                {0, 1, 1, 0, 0, 1},
                {1, 1, 0, 1, 1, 1},
                {1, 0, 0, 0, 1, 0},
                {0, 1, 1, 1, 0, 0}
        };
        int[][] table = {
                {1, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 0},
                {0, 1, 1, 0, 1, 1},
                {0, 0, 1, 0, 0, 0},
                {1, 1, 0, 1, 1, 0},
                {0, 1, 0, 0, 0, 0}
        };

//        int[][] gameBoard ={{0,0,0},{1,1,0},{1,1,1}};
//        int[][] table = {{1,1,1},{1,0,0},{0,0,0}};

        int answer = solution(gameBoard, table);
        System.out.println(answer);
    }

    public static int solution(int[][] gameBoard, int[][] table) {
        gameBoardMaxRow = gameBoard.length;
        gameBoardMaxCol = gameBoard[0].length;
        visited = new boolean[gameBoardMaxRow][gameBoardMaxCol];

        List<Shape> tableShapes = scanTable(table);
        tableShapes.forEach(e -> System.out.print(e + " "));


        int answer = scanBoards(gameBoard, tableShapes);
        return answer;
    }

    private static int scanBoards(int[][] gameBoard, List<Shape> tableShapes) {
        int answer = 0;
        Set<int[]> visitedPositions = new HashSet<>();
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                if (gameBoard[i][j] == EMPTY && !visited[i][j]) {
                    Shape emptySpace = bfsGameBoards(gameBoard, new int[]{i, j}, visitedPositions);

                    Iterator<Shape> tableShapesIterator = tableShapes.iterator();
                    while (tableShapesIterator.hasNext()) {
                        Shape tableShape = tableShapesIterator.next();

                        if (emptySpace.equals(tableShape)) {
                            answer++;
                            tableShapesIterator.remove();

                            for (int[] visitedPosition : visitedPositions) {
                                int row = visitedPosition[ROW];
                                int col = visitedPosition[COL];

                                visited[row][col] = true;
                            }
                            break;
                        }
                    }
                }
            }
        }

        return answer;
    }

    private static Shape bfsGameBoards(int[][] gameBoard, int[] startingPoint, Set<int[]> visitedPositions) {
        int score = 0;

        int startingPointRow = startingPoint[ROW];
        int startingPointCol = startingPoint[COL];
        visitedPositions.add(new int[]{startingPointRow, startingPointCol});

        Queue<Step> queue = new LinkedList<>();
        queue.offer(new Step(startingPoint, 0));

        while (!queue.isEmpty()) {
            Step poll = queue.poll();
            int[] curPosition = poll.position;
            int curRow = curPosition[ROW];
            int curCol = curPosition[COL];
            int curStep = poll.step;

            for (int i = 0; i < DIRECTION_SIZE; i++) {
                int nextRow = curRow + dr[i];
                int nextCol = curCol + dc[i];

                if (nextRow < 0 || nextRow >= gameBoardMaxRow || nextCol < 0 || nextCol >= gameBoardMaxCol) {
                    continue;
                }

                int[] nextPosition = {nextRow, nextCol};
                if (visitedPositions.contains(nextPosition)) {
                    continue;
                }

                if (gameBoard[nextRow][nextCol] == BLOCK) {
                    continue;
                }

                visitedPositions.add(nextPosition);
                score += scores[i] + (curStep * PIVOT);
                Step next = new Step(nextPosition, curStep + 1);
                queue.offer(next);
            }
        }

        return new Shape(score);
    }

    public static List<Shape> scanTable(int[][] table) {
        List<Shape> shapes = new LinkedList<>();

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if (table[i][j] == PIECE && !visited[i][j]) {
                    Shape emptySpace = bfsTable(table, new int[]{i, j});
                    shapes.add(emptySpace);
                }
            }
        }

        return shapes;
    }

    public static Shape bfsTable(int[][] table, int[] startingPoint) {
        int score = 0;

        int startingPointRow = startingPoint[ROW];
        int startingPointCol = startingPoint[COL];
        visited[startingPointRow][startingPointCol] = true;

        Queue<Step> queue = new LinkedList<>();
        queue.offer(new Step(startingPoint, 0));

        while (!queue.isEmpty()) {
            Step poll = queue.poll();
            int[] curPosition = poll.position;
            int curRow = curPosition[ROW];
            int curCol = curPosition[COL];
            int curStep = poll.step;

            for (int i = 0; i < DIRECTION_SIZE; i++) {
                int nextRow = curRow + dr[i];
                int nextCol = curCol + dc[i];

                if (nextRow < 0 || nextRow >= gameBoardMaxRow || nextCol < 0 || nextCol >= gameBoardMaxCol) {
                    continue;
                }

                if (visited[nextRow][nextCol]) {
                    continue;
                }

                if (table[nextRow][nextCol] == EMPTY) {
                    continue;
                }

                visited[nextRow][nextCol] = true;
                score += scores[i] + (curStep * PIVOT);
                Step next = new Step(new int[]{nextRow, nextCol}, curStep + 1);
                queue.offer(next);
            }
        }

        return new Shape(score);
    }

    private static class Step {
        int[] position;
        int step;

        public Step(int[] position, int step) {
            this.position = position;
            this.step = step;
        }
    }

    private static class Shape {
        int score;

        public Shape(int score) {
            this.score = score;
        }

        @Override
        public String toString() {
            return "score=" + score;

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Shape shape = (Shape) o;
            return score == shape.score;
        }

        @Override
        public int hashCode() {
            return Objects.hash(score);
        }
    }
}
