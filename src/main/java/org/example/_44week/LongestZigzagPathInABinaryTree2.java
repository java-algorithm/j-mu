package org.example._44week;

import org.example._20week.PathSum3.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestZigzagPathInABinaryTree2 {
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
        if (root == null) {
            return 0;
        }

        Deque<Step> dequeue = new ArrayDeque<>();
        dequeue.addLast(new Step(root, 0, LEFT));
        dequeue.addLast(new Step(root, 0, RIGHT));

        while (!dequeue.isEmpty()) {
            Step poll = dequeue.pollFirst();

            if (poll.node == null) {
                continue;
            }

            answer = Math.max(answer, poll.depth);

            if (poll.direction == LEFT) {
                dequeue.addLast(new Step(poll.node.left, 0, LEFT));
                dequeue.addLast(new Step(poll.node.left, 0, RIGHT));
                dequeue.addLast(new Step(poll.node.right, poll.depth + 1, RIGHT));
            }else{
                dequeue.addLast(new Step(poll.node.right, 0, LEFT));
                dequeue.addLast(new Step(poll.node.right, 0, RIGHT));
                dequeue.addLast(new Step(poll.node.left, poll.depth + 1, LEFT));
            }
        }

        return answer;
    }

    private static class Step {
        public TreeNode node;
        public int depth;
        public int direction;

        public Step(
                TreeNode node,
                int depth,
                int direction
        ) {
            this.node = node;
            this.depth = depth;
            this.direction = direction;
        }
    }

    public int reverse(int direction) {
        if (direction == LEFT) {
            return RIGHT;
        } else {
            return LEFT;
        }
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
