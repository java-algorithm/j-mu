package org.example._32week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TreeInvestment {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int mapSize;
    private static final int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        mapSize = Integer.parseInt(st.nextToken());
        int treeCount = Integer.parseInt(st.nextToken());
        int targetYear = Integer.parseInt(st.nextToken());

        int[][] foods = new int[mapSize + 1][mapSize + 1];
        int[][] addFoods = new int[mapSize + 1][mapSize + 1];

        // 최초 영양분은 5
        // 영양분 배열 초기화
        for (int row = 1; row < mapSize + 1; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col < mapSize + 1; col++) {
                foods[row][col] = 5;
                addFoods[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 나무 초기화
        List<Tree> trees = new ArrayList<>();
        for (int i = 0; i < treeCount; i++) {
            st = new StringTokenizer(br.readLine());

            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            trees.add(new Tree(row, col, age));
        }

        // 최초 입력은 한칸에 나무가 여러개 있는 경우는 x
        // targetYear동안 나무 키우기.
        for (int year = 0; year < targetYear; year++) {
            // 봄.
            List<Tree> shouldRemoveTrees = new ArrayList<>();
            Iterator<Tree> iterator = trees.iterator();
            while (iterator.hasNext()) {
                Tree tree = iterator.next();

                // 자신의 나이만큼 양분을 먹기.
                if (foods[tree.row][tree.col] >= tree.age) {
                    foods[tree.row][tree.col] -= tree.age;
                    tree.age++;
                    continue;
                }

                // 양분이 부족하면 나무 죽이기.
                shouldRemoveTrees.add(tree);
                tree.isDeleted = true;
            }

            // 여름
            // 죽은 나무를 양분으로 바꾸기.
            int shouldRemoveTreeSize = shouldRemoveTrees.size();
            for (int i = 0; i < shouldRemoveTreeSize; i++) {
                Tree tree = shouldRemoveTrees.get(i);
                foods[tree.row][tree.col] += tree.age / 2;
            }

            // 가을
            List<Tree> newTrees = new ArrayList<>();
            iterator = trees.iterator();
            while (iterator.hasNext()) {
                Tree tree = iterator.next();

                if (tree.isDeleted) {
                    continue;
                }

                // 나이가 5의 배수인 나무만 번식한다.
                if (tree.age % 5 != 0) {
                    continue;
                }

                // 인접 땅에다가 번식하기.
                for (int j = 0; j < 8; j++) {
                    int nextRow = tree.row + dr[j];
                    int nextCol = tree.col + dc[j];

                    // 상도 땅 벗어나면 번식안하기.
                    if (nextRow <= 0 || nextRow > mapSize || nextCol <= 0 || nextCol > mapSize) {
                        continue;
                    }

                    newTrees.add(new Tree(nextRow, nextCol, 1));
                }
            }

            for (Tree tree : trees) {
                if (!tree.isDeleted) {
                    newTrees.add(tree);
                }
            }
            trees = newTrees;

            // 겨울
            // 로봇이 영양분을 주입한다.
            for (int row = 1; row < mapSize + 1; row++) {
                for (int col = 1; col < mapSize + 1; col++) {
                    foods[row][col] += addFoods[row][col];
                }
            }
        }

        System.out.println(trees.size());
    }

    private static class Tree implements Comparable<Tree> {
        int row;
        int col;
        int age;
        boolean isDeleted = false;

        public Tree(int row, int col, int age) {
            this.row = row;
            this.col = col;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return Integer.compare(this.age, o.age);
        }
    }
}
