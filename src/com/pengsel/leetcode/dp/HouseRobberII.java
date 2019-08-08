package com.pengsel.leetcode.dp;

public class HouseRobberII {
    /**
     * 子问题分解:
     * dp[i]表示从前i个商店组成的环获取的最大价值
     * 递推:
     * 抢不抢第i家与之前抢劫的方法路径有关,不能这样定状态
     *
     * 子问题分解:
     * 第一个商店抢还是不抢
     * dp[i]=
     */


    public int rob(int[] num){
        if (num==null||num.length==0){
            return 0;
        }
        if (num.length==1){return num[0];}
        return Math.max(rob(num,0,num.length-2),rob(num,1,num.length-1));

    }
    public int rob(int[] num,int lo, int hi){
        int pre0=0,pre1=0;
        for (int i=lo;i<=hi;i++){
            int temp=pre1;
            pre1=Math.max(pre1,pre0+num[i]);
            pre0=temp;
        }
        return pre1;
    }
}
