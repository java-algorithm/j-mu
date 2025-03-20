package org.example._49week;

import java.util.Stack;

public class EditTable {

    private static final String up = "U";
    private static final String down = "D";
    private static final String cut = "C";
    private static final String ctrlZ = "Z";

    private static boolean[] deleted;
    private static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {
        int n = 8;
        int k = 2;
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};

        System.out.println(solution(n, k, cmd));
    }

    public static String solution(int n, int k, String[] cmd) {
        deleted = new boolean[n];
        int cur = k;


        int jumpSize = 0;
        for (String c : cmd) {
            String[] splits = c.split(" ");
            switch (splits[0]) {
                case up:
                    jumpSize = Integer.parseInt(splits[1]);
                    cur = up(cur, jumpSize);
                    break;
                case down:
                    jumpSize = Integer.parseInt(splits[1]);
                    cur = down(cur, jumpSize);
                    break;
                case cut:
                    cur = cut(cur);
                    break;
                case ctrlZ:
                    ctrlZ();
                    break;
                default:
                    return "error";
            }
        }

        StringBuilder answer = new StringBuilder();
        for (boolean d : deleted) {
            answer.append(d ? "X" : "O");
        }

        return answer.toString();
    }

    private static void ctrlZ() {
        Integer lastPos = stack.pop();
        deleted[lastPos] = false;
    }

    private static int cut(int curPos) {
        deleted[curPos] = true;
        stack.push(curPos);

        int nextRow = down(curPos, 1);
        if (nextRow == -1) {// curPos 가 마지막 행이였던 경우
            nextRow = up(curPos, 1);
        }

        return nextRow;
    }

    private static int up(int curPos, int jumpSize) {
        for (curPos = curPos - 1; curPos >= 0; curPos--) {
            if (!deleted[curPos]) {
                jumpSize--;
                if (jumpSize == 0) {
                    return curPos;
                }
            }
        }

        return -1;
    }

    private static int down(int curPos, int jumpSize) {
        for (curPos = curPos + 1; curPos < deleted.length; curPos++) {
            if (!deleted[curPos]) {
                jumpSize--;
                if (jumpSize == 0) {
                    return curPos;
                }
            }
        }

        return -1;
    }
}
