package com.pengsel.leetcode.dp;

public class TwoKeysKeyboard {

    /**
     * dp[n]代表了n长的需要多少次复制粘贴
     *
     * dp[k] k<n都是已知的
     * 那么递归过程可以是:
     * for(int i=n/2;i>0;i--){
     *     if(n%i==0){
     *          //把i个复制一次, 粘贴n/i-1次
     *         dp[n]=dp[i]+n/i;
     *         break;
     *     }
     * }
     *
     * 初始情况
     * @param n
     * @return
     */
    public int minSteps(int n) {
        if (n<2) return 0;
        int[] dp=new int[n+1];
        for (int i=2;i<dp.length;i++){
            for (int j=i/2;j>0;j--){
                if (i%j==0){
                    dp[i]=dp[j]+i/j;
                    break;
                }
            }
        }

        return dp[n];

    }
}
