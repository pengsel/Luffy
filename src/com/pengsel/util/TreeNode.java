package com.pengsel.util;

import java.util.PriorityQueue;

public class TreeNode {
    public int val = 0;
    public TreeNode left = null;
    public TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

    public static TreeNode create(int[] array){
        if(array==null||array.length==0){
            System.out.println("error：输入数组为空！");
            return null;
        }
        TreeNode head=new TreeNode(array[0]);
        PriorityQueue<TreeNode> queue=new PriorityQueue<TreeNode>();
        queue.offer(head);
        while (queue.size()>0){
            TreeNode node=queue.poll();
//            if ()
        }
        return head;
    }

}
