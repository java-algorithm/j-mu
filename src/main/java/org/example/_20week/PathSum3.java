package org.example._20week;

import java.util.*;

public class PathSum3 {


    public static void main(String[] args) {

        final TreeNode root = initialize();
        pathSum(root, 22);
    }

    private static int pathSum(final TreeNode root, final int targetSum) {
        int count = 0;

        if (root == null) {
            return count;
        }

        Queue<Param> queue = new LinkedList<>();
        final Param param = new Param(root, List.of((long) root.val));
        queue.offer(param);

        while (!queue.isEmpty()) {
            final Param cur = queue.poll();
            final TreeNode curNode = cur.node;

            final List<Long> sumArray = cur.sumArray;
            // sumArray 돌면서 여기까지 부분합으로 targetSum 나올수 있는지 확인
            count += calculateTargetSum(sumArray, targetSum);

            final TreeNode left = curNode.left;
            final TreeNode right = curNode.right;

            if (left != null) {
                final ArrayList<Long> newSumArray = new ArrayList<>(sumArray);
                final Long lastElement = newSumArray.get(newSumArray.size() - 1);
                newSumArray.add(lastElement + left.val);
                final Param nextParam = new Param(left, newSumArray);
                queue.offer(nextParam);
            }

            if (right != null) {
                final ArrayList<Long> newSumArray = new ArrayList<>(sumArray);
                final Long lastElement = newSumArray.get(newSumArray.size() - 1);
                newSumArray.add(lastElement + right.val);
                final Param nextParam = new Param(right, newSumArray);
                queue.offer(nextParam);
            }
        }

        return count;
    }

    private static int calculateTargetSum(final List<Long> sumArray, final int targetSum) {
        int count = 0;
        final Long lastElement = sumArray.get(sumArray.size() - 1);

        if (lastElement == targetSum) {
            count++;
        }

        for (int i = 0; i < sumArray.size() - 1; i++) {
            final long sum = lastElement - sumArray.get(i);
            if (sum == targetSum) {
                count++;
            }
        }

        return count;
    }

    private static class Param {
        TreeNode node;
        List<Long> sumArray;

        public Param(final TreeNode node, final List<Long> sumArray) {
            this.node = node;
            this.sumArray = sumArray;
        }
    }

//    private static TreeNode initialize() {
//        final TreeNode treeNode7 = new TreeNode(3);
//        final TreeNode treeNode8 = new TreeNode(-2);
//        final TreeNode treeNode9 = null;
//        final TreeNode treeNode10 = new TreeNode(1);
//
//        final TreeNode treeNode3 = new TreeNode(3, treeNode7, treeNode8);
//        final TreeNode treeNode4 = new TreeNode(2, null, treeNode10);
//
//        final TreeNode treeNode1 = new TreeNode(5, treeNode3, treeNode4);
//
//        final TreeNode treeNode5 = null;
//        final TreeNode treeNode6 = new TreeNode(11);
//        final TreeNode treeNode2 = new TreeNode(-3, null, treeNode6);
//
//        final TreeNode treeNode = new TreeNode(10, treeNode1, treeNode2);
//
//        return treeNode;
//    }

    private static TreeNode initialize() {
        final TreeNode treeNode8 = new TreeNode(5);
        final TreeNode treeNode9 = new TreeNode(1);

        final TreeNode treeNode5 = new TreeNode(4, treeNode8, treeNode9);
        final TreeNode treeNode4 = new TreeNode(13);

        final TreeNode treeNode2 = new TreeNode(8, treeNode4, treeNode5);

        final TreeNode treeNode6 = new TreeNode(7);
        final TreeNode treeNode7 = new TreeNode(2);

        final TreeNode treeNode3 = new TreeNode(11, treeNode6, treeNode7);

        final TreeNode treeNode1 = new TreeNode(4, treeNode3, null);

        final TreeNode root = new TreeNode(5, treeNode1, treeNode2);
        return root;
    }

    public static class TreeNode {
        int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public int hashCode() {
            return Objects.hash(val, left, right);
        }

        public void setLeft(final TreeNode left) {
            this.left = left;
        }

        public void setRight(final TreeNode right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return
                "val=" + val +
                    ", left=" + (left == null ? "null" : left.val) +
                    ", right=" + (right == null ? "null" : right.val);
        }
    }

}

