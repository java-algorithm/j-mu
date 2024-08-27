package org.example._29week;

class BinaryTreeMaximumPathSum {
    private static int max;

    public int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        int lastValue = calculate(root);
        max = Math.max(max,lastValue);

        return max;
    }

    private int calculate(TreeNode root){
        if(root == null){
            return 0;
        }

        int leftSum = calculate(root.left);
        leftSum = leftSum > 0 ? leftSum : 0;

        int rightSum = calculate(root.right);
        rightSum = rightSum > 0 ? rightSum : 0;

        int value = leftSum + rightSum + root.val;
        max = Math.max(max,value);

        return Math.max(leftSum,rightSum) + root.val;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}