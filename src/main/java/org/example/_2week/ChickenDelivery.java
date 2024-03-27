package org.example._2week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class ChickenDelivery {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int EMPTY = 0;
    private static int HOUSE = 1;
    private static int CHICKEN = 2;
    private static List<Position> houses = new ArrayList<>();
    private static List<Position> chickens = new ArrayList<>();
    private static int index = 0;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == HOUSE) {
                    houses.add(new Position(i, j));
                } else if (map[i][j] == CHICKEN) {
                    chickens.add(new Position(i, j));
                }
            }
        }

        int houseCount = houses.size();
        int chickenCount = chickens.size();
        int[][] distances = new int[houseCount][chickenCount];

        for (int i = 0; i < houseCount; i++) {
            for (int j = 0; j < chickenCount; j++) {
                distances[i][j] = calculdateDistance(houses.get(i), chickens.get(j));
            }
        }

        List<List<Integer>> combinations = combinationTotal(chickenCount, M);

        int minTotalDistance = Integer.MAX_VALUE;
        for (int i = 0; i < combinations.size(); i++) {
            int[] rowMinDistances = new int[houseCount];
            List<Integer> combination = combinations.get(i);
            for (int j = 0; j < houseCount; j++) {
                int rowMin = Integer.MAX_VALUE;
                for (int k = 0; k < chickenCount; k++) {
                    if (combination.contains(k)) {
                        int distance = distances[j][k];
                        if (rowMin > distance) {
                            rowMin = distance;
                        }
                    }
                }

                rowMinDistances[j] = rowMin;
            }

            int sum = Arrays.stream(rowMinDistances).sum();
            if (minTotalDistance > sum) {
                minTotalDistance = sum;
            }
        }

        System.out.println(minTotalDistance);
    }

    public static List<List<Integer>> combinationTotal(int size, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (size == 0) {
            return result;
        }
        List<Integer> combination = new ArrayList<>();
        combination(size, k, 0, combination, result);
        return result;
    }

    private static void combination(int size, int k, int start, List<Integer> combination, List<List<Integer>> result) {
        if (combination.size() == k) {
            result.add(new ArrayList<>(combination));
            return;
        }

        for (int i = start; i < size; i++) {
            combination.add(i);
            combination(size, k, i + 1, combination, result);
            combination.remove(combination.size() - 1);
        }
    }

    private static int calculdateDistance(Position house, Position chicken) {
        int yDistance = Math.abs(house.y - chicken.y);
        int xDistance = Math.abs(house.x - chicken.x);

        return yDistance + xDistance;
    }

    static class Position {

        int y;
        int x;

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            return "Position{" +
                "y=" + y +
                ", x=" + x +
                '}';
        }
    }
}
