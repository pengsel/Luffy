package com.pengsel.offer;

import com.pengsel.util.TreeNode;

/**
 * 题目描述
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 * 输入描述:
 * 二叉树的镜像定义：源二叉树
 *     	    8
 *     	   /  \
 *     	  6   10
 *     	 / \  / \
 *     	5  7 9 11
 *     	镜像二叉树
 *     	    8
 *     	   /  \
 *     	  10   6
 *     	 / \  / \
 *     	11 9 7  5
 */
public class Mirror {
    /**
     * 先置换二叉树的两个子节点，再递归调用
     * @param root
     */
    public void Mirror(TreeNode root) {
        if(root!=null){
            TreeNode tn=root.left;
            root.left=root.right;
            root.right=tn;
            if(root.left!=null) Mirror(root.left);
            if(root.right!=null) Mirror(root.right);
        }
    }
}
