package com.pengsel.offer;

import com.pengsel.util.TreeNode;

/**
 *输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 */

public class HasSubtree {

    /**
     * 对于每一个节点(递归一次)，判断是不是子结构(递归一次)；
     * @param root1
     * @param root2
     * @return
     */
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if(root2==null)
            return false;
        return judgeSubTree(root1,root2);
    }

    private boolean judgeSubTree(TreeNode root1,TreeNode root2){
        if(isSubTree(root1,root2)){
            return true;
        }
        else if(root1!=null&& root2!=null){
            return judgeSubTree(root1.left,root2)||judgeSubTree(root1.right,root2);
        }
        return false;
    }
    private boolean isSubTree(TreeNode root1,TreeNode root2){
        if(root2==null)
            return true;
        if(root1!=null&&root1.val==root2.val)
            return isSubTree(root1.left,root2.left)&&isSubTree(root1.right,root2.right);
        return false;
    }
}
