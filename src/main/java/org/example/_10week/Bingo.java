package org.example._10week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Bingo {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        // 숫자 : 좌표
        Map<Integer, Position> boards = new HashMap<>();
        int[] rows = new int[6];
        int[] cols = new int[6];
        int diagonal1 = 0;
        int diagonal2 = 0;

        // 빙고판 만들기
        StringTokenizer st;
        for (int i = 1; i <= 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 5; j++) {
                final int num = Integer.parseInt(st.nextToken());
                boards.put(num, new Position(i, j));
            }
        }

        List<Integer> calledNums = new LinkedList();
        for (int i = 0; i < 5; i++) {
            final List<Integer> nums = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            calledNums.addAll(nums);
        }

        int lineCount=0;
        int times = 0;
        for (final Integer num : calledNums) {
            times++;
            final Position position = boards.get(num);

            final int row = position.row;
            rows[row]+=1;
            if (rows[row] == 5) {
                lineCount++;
                if (lineCount == 3) {
                    break;
                }
            }

            final int col = position.col;
            cols[col] += 1;
            if (cols[col] == 5) {
                lineCount++;
                if (lineCount == 3) {
                    break;
                }
            }

            if (position.containsDiagonal1()) {
                diagonal1 += 1;
                if (diagonal1 == 5) {
                    lineCount++;
                    if (lineCount == 3) {
                        break;
                    }
                }
            }

            if (position.containsDiagonal2()) {
                diagonal2 += 1;
                if (diagonal2 == 5) {
                    lineCount++;
                    if (lineCount == 3) {
                        break;
                    }
                }
            }
        }

        System.out.println(times);
    }

    private static class Position{
        int row;
        int col;

        public Position(final int row, final int col) {
            this.row = row;
            this.col = col;
        }

        public boolean containsDiagonal1() {
            return row == col;
        }

        public boolean containsDiagonal2() {
            return row + col == 6;
        }
    }
}
