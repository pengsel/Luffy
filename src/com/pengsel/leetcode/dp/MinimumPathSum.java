package com.pengsel.leetcode.dp;

public class MinimumPathSum {
    /**
     * dp[i][j]表示从i行j列的矩阵,最小的矩阵路径和
     * dp[i][j]=min(dp[i-1][j],dp[i][j-1])+grid[i][j];
     *
     */

    int pathSum(int[][] a){
        if (a==null&&a.length==0) return 0;
        int[] cur=new int[a[0].length];
        cur[0]=a[0][0];
        for (int i=1;i<a[0].length;i++){
            cur[i]=a[0][i]+cur[i-1];
        }
        for (int i=1;i<a.length;i++){
            cur[0]=cur[0]+a[i][0];
            for (int j=1;j<a[0].length;j++){
                cur[j]=Math.min(cur[j-1],cur[j])+a[i][j];
            }
        }
        return cur[cur.length-1];
    }

}
