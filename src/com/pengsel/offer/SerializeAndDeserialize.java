package com.pengsel.offer;

import com.pengsel.util.TreeNode;

import java.util.ArrayList;

public class SerializeAndDeserialize {
    String Serialize(TreeNode root) {

        if(root==null)
            return "";
        ArrayList<Integer> preOrder=new ArrayList<>();
        ArrayList<Integer> inOrder=new ArrayList<>();
        preOrderTraverse(root,preOrder);
        inOrderTraverse(root,inOrder);

        StringBuilder sb=new StringBuilder();
        for (Integer i:preOrder){
            sb.append(i+"-");
        }
        for (Integer i:inOrder){
            sb.append(i+"-");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    private void preOrderTraverse(TreeNode root,ArrayList<Integer> arrayList){
        if (root!=null){
            arrayList.add(root.val);
            preOrderTraverse(root.left,arrayList);
            preOrderTraverse(root.right,arrayList);
        }
    }

    private void inOrderTraverse(TreeNode root,ArrayList<Integer> arrayList){
        if (root!=null){
            inOrderTraverse(root.left,arrayList);
            arrayList.add(root.val);
            inOrderTraverse(root.right,arrayList);
        }
    }
    TreeNode Deserialize(String str) {

        String[] strs=str.split("-");
        int[] pre=new int[strs.length/2];
        int[] in=new int[strs.length/2];
        for (int i=0;i<pre.length;i++){
            pre[i]=Integer.valueOf(strs[i]);
        }
        for (int i=0;i<in.length;i++){
            in[i]=Integer.valueOf(strs[i+strs.length/2]);
        }

        TreeNode root=reConstructBinaryTree(pre,0,pre.length-1,in,0,in.length-1);
        return root;

    }

    //前序遍历{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
    private TreeNode reConstructBinaryTree(int [] pre,int startPre,int endPre,int [] in,int startIn,int endIn) {

        if(startPre>endPre||startIn>endIn)
            return null;
        TreeNode root=new TreeNode(pre[startPre]);

        for(int i=startIn;i<=endIn;i++)
            if(in[i]==pre[startPre]){
                root.left=reConstructBinaryTree(pre,startPre+1,startPre+i-startIn,in,startIn,i-1);
                root.right=reConstructBinaryTree(pre,i-startIn+startPre+1,endPre,in,i+1,endIn);
                break;
            }

        return root;
    }
}
