package org.example._49week;

import java.util.Stack;

public class EditTable2 {

    private static final String UP = "U";
    private static final String DOWN = "D";
    private static final String cut = "C";
    private static final String ctrlZ = "Z";

    private static int[] up;
    private static int[] down;
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
                case UP:
                    jumpSize = Integer.parseInt(splits[1]);
                    cur = up(cur, jumpSize);
                    break;
                case DOWN:
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

        int upRow = up[curPos];
        int downRow = down[curPos];

        down[upRow] = downRow;
        if(downRow!=-1){
            up[downRow] = upRow;
        }

        return downRow == -1 ? upRow : downRow;
    }

    private static int up(int curPos, int jumpSize) {
        for (int cnt = 0; cnt < jumpSize; cnt++) {
            curPos = up[jumpSize];
        }

        return curPos;
    }

    private static int down(int curPos, int jumpSize) {
        for(int cnt=0; cnt< jumpSize; cnt++){
            curPos = down[jumpSize];
        }

        return curPos;
    }
}
