//package org.example._20week;
//
//import java.util.*;
//
//public class FillPuzzle {
//
//    private static final int EMPTY = 0;
//    private static final int PIECE = 1;
//    private static final int BLOCK = 1;
//
//    public static final int DIRECTION_SIZE = 4;
//
//    private static int gameBoardMaxRow;
//    private static int gameBoardMaxCol;
//    private static boolean[][] visited;
//
//    private static int[] dr = {-1, 0, 1, 0};
//    private static int[] dc = {0, -1, 0, 1};
//
//    private static int[] scores = {1, 2, 3, 4};
//    private static final int PIVOT = 5;
//
//    public static void main(String[] args) {
//        int[][] gameBoard = {
//            {1, 1, 0, 0, 1, 0},
//            {0, 0, 1, 0, 1, 0},
//            {0, 1, 1, 0, 0, 1},
//            {1, 1, 0, 1, 1, 1},
//            {1, 0, 0, 0, 1, 0},
//            {0, 1, 1, 1, 0, 0}
//        };
//        int[][] table = {
//            {1, 0, 0, 1, 1, 0},
//            {1, 0, 1, 0, 1, 0},
//            {0, 1, 1, 0, 1, 1},
//            {0, 0, 1, 0, 0, 0},
//            {1, 1, 0, 1, 1, 0},
//            {0, 1, 0, 0, 0, 0}
//        };
//
////        int[][] gameBoard ={{0,0,0},{1,1,0},{1,1,1}};
////        int[][] table = {{1,1,1},{1,0,0},{0,0,0}};
//
//        int answer = solution(gameBoard, table);
//        System.out.println(answer);
//    }
//
//    public static int solution(int[][] gameBoard, int[][] table) {
//        gameBoardMaxRow = gameBoard.length;
//        gameBoardMaxCol = gameBoard[0].length;
//        visited = new boolean[gameBoardMaxRow][gameBoardMaxCol];
//
//        List<Shape> tableShapes = scanTable(table);
//        tableShapes.forEach(e -> System.out.print(e + " "));
//        System.out.println();
//
//        visited = new boolean[gameBoardMaxRow][gameBoardMaxCol];
//        int answer = scanBoards(gameBoard, tableShapes);
//        return answer;
//    }
//
//    private static int scanBoards(int[][] gameBoard, List<Shape> tableShapes) {
//        int answer = 0;
//        Set<Position> visitedPositions = new HashSet<>();
//        for (int i = 0; i < gameBoard.length; i++) {
//            for (int j = 0; j < gameBoard[0].length; j++) {
//                if (gameBoard[i][j] == EMPTY && !visited[i][j]) {
//                    final Position startingPoint = new Position(i, j);
//                    Shape emptySpace = bfsGameBoards(gameBoard, startingPoint, visitedPositions);
//
//                    Iterator<Shape> tableShapesIterator = tableShapes.iterator();
//                    while (tableShapesIterator.hasNext()) {
//                        Shape tableShape = tableShapesIterator.next();
//
//                        if (emptySpace.equals(tableShape)) {
//                            answer++;
//                            tableShapesIterator.remove();
//
//                            for (Position visitedPosition : visitedPositions) {
//                                int row = visitedPosition.row;
//                                int col = visitedPosition.col;
//
//                                visited[row][col] = true;
//                            }
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//
//        return answer;
//    }
//
//    private static List<Position> bfsGameBoards(int[][] gameBoard, Position startingPoint, Set<Position> visitedPositions) {
//        List<Position> shape = new ArrayList<>();
//        visitedPositions.add(startingPoint);
//        shape.add(startingPoint);
//
//        Queue<Step> queue = new LinkedList<>();
//        queue.offer(new Step(startingPoint, 0));
//
//        while (!queue.isEmpty()) {
//            Step poll = queue.poll();
//            Position curPosition = poll.position;
//            int curRow = curPosition.row;
//            int curCol = curPosition.col;
//            int curStep = poll.step;
//
//            for (int i = 0; i < DIRECTION_SIZE; i++) {
//                int nextRow = curRow + dr[i];
//                int nextCol = curCol + dc[i];
//
//                if (nextRow < 0 || nextRow >= gameBoardMaxRow || nextCol < 0 || nextCol >= gameBoardMaxCol) {
//                    continue;
//                }
//
//                final Position nextPosition = new Position(nextRow, nextCol);
//                if (visitedPositions.contains(nextPosition)) {
//                    continue;
//                }
//
//                if (gameBoard[nextRow][nextCol] == BLOCK) {
//                    continue;
//                }
//
//                visitedPositions.add(nextPosition);
//                shape.add(nextPosition);
//
//                Step next = new Step(nextPosition, curStep + 1);
//                queue.offer(next);
//            }
//        }
//
//        return shape;
//    }
//
//    public static List<Shape> scanTable(int[][] table) {
//        List<Shape> shapes = new LinkedList<>();
//
//        for (int i = 0; i < table.length; i++) {
//            for (int j = 0; j < table[0].length; j++) {
//                if (table[i][j] == PIECE && !visited[i][j]) {
//                    final Position startingPoint = new Position(i, j);
//                    Shape emptySpace = bfsTable(table, startingPoint);
//                    shapes.add(emptySpace);
//                }
//            }
//        }
//
//        return shapes;
//    }
//
//    public static Shape bfsTable(int[][] table, Position startingPoint) {
//        int score = 0;
//
//        int startingPointRow = startingPoint.row;
//        int startingPointCol = startingPoint.col;
//        visited[startingPointRow][startingPointCol] = true;
//
//        Queue<Step> queue = new LinkedList<>();
//        queue.offer(new Step(startingPoint, 0));
//
//        while (!queue.isEmpty()) {
//            Step poll = queue.poll();
//            Position curPosition = poll.position;
//            int curRow = curPosition.row;
//            int curCol = curPosition.col;
//            int curStep = poll.step;
//
//            for (int i = 0; i < DIRECTION_SIZE; i++) {
//                int nextRow = curRow + dr[i];
//                int nextCol = curCol + dc[i];
//
//                if (nextRow < 0 || nextRow >= gameBoardMaxRow || nextCol < 0 || nextCol >= gameBoardMaxCol) {
//                    continue;
//                }
//
//                if (visited[nextRow][nextCol]) {
//                    continue;
//                }
//
//                if (table[nextRow][nextCol] == EMPTY) {
//                    continue;
//                }
//
//                visited[nextRow][nextCol] = true;
//                score += scores[i] + (curStep * PIVOT);
//                final Position nextPosition = new Position(nextRow, nextCol);
//                Step next = new Step(nextPosition, curStep + 1);
//                queue.offer(next);
//            }
//        }
//
//        return new Shape(score);
//    }
//
//    private static class Position {
//        int row;
//        int col;
//
//        public Position(final int row, final int col) {
//            this.row = row;
//            this.col = col;
//        }
//
//        @Override
//        public boolean equals(final Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            final Position position = (Position) o;
//            return row == position.row && col == position.col;
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(row, col);
//        }
//    }
//
//    private static class Step {
//        Position position;
//        int step;
//
//        public Step(Position position, int step) {
//            this.position = position;
//            this.step = step;
//        }
//    }
//
//    private static class Shape {
//        int score;
//
//        public Shape(int score) {
//            this.score = score;
//        }
//
//        @Override
//        public String toString() {
//            return "score=" + score;
//
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Shape shape = (Shape) o;
//            return score == shape.score;
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(score);
//        }
//    }
//}
