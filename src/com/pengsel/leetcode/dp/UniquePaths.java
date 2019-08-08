package com.pengsel.leetcode.dp;

/**
 * 62. Unique Paths
 * dp[i][j]表示i行j列的矩阵,机器人能走的距离
 * dp[i][j]=dp[i-1][j]+dp[i][j-1];
 */
public class UniquePaths {

    public int paths(int m,int n){
        int[] cur=new int[n];
        for (int j=0;j<n;j++){
            cur[j]=1;
        }

        for (int i=1;i<m;i++){
            cur[0]=1;
            for (int j=0;j<m;j++){
                cur[i]=cur[i-1]+cur[i];
            }
        }
        return cur[cur.length-1];
    }
}
