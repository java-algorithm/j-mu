package org.example._44week;

import org.example._20week.PathSum3.TreeNode;

import java.sql.SQLOutput;

public class LongestZigzagPathInABinaryTree {
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static int answer = 0;
    private static TreeNode realRoot;

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.left = null;
        root.right = new TreeNode();

        TreeNode right1 = root.right;
        right1.left = new TreeNode();
        right1.right = new TreeNode();

        TreeNode right2 = right1.right;
        right2.left = new TreeNode();
        right2.right = new TreeNode();

        TreeNode left1 = right2.left;
        left1.right = new TreeNode();

        TreeNode right3 = left1.right;
        right3.right = new TreeNode();

        realRoot = root;

        int i = longestZigZag(root);
        System.out.println(i);
    }

    public static int longestZigZag(TreeNode root) {
        answer = 0;

        zigzag(root, 0, LEFT);
        zigzag(root, 0, RIGHT);

        return answer;
    }

    public static void zigzag(TreeNode root, int depth, int direction) {
        if (root == null) {
            return;
        }

        answer = Math.max(answer, depth);

        if (direction == LEFT) {
            zigzag(root.left, 0, LEFT);
            zigzag(root.left, 0, RIGHT);
            zigzag(root.right, depth + 1, RIGHT);
        }

        if (direction == RIGHT) {
            zigzag(root.right, 0, RIGHT);
            zigzag(root.right, 0, LEFT);
            zigzag(root.left, depth + 1, LEFT);
        }
    }
}
