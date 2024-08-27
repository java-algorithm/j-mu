package org.example._29week;

import org.example._20week.PathSum3;

public class CountCompleteTreeNodes {

    public int countNodes(PathSum3.TreeNode root) {
        if(root==null){
            return 0;
        }

        return countNodes(root.right) + countNodes(root.left) + 1;
    }
}
