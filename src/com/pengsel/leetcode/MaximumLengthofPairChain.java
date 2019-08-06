package com.pengsel.leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class MaximumLengthofPairChain {

    /**
     * 动态规划
     *
     * dp[n]为以pairs[n]结尾的最长PairChain
     *
     * 递推：
     * dp[n]=1;
     * for(int i=0;i<n;i++){
     *      if(pairs[n][0]>pairs[i][1]){
     *          dp[n]=Math.max(dp[n],dp[i]+1);
     *      }
     * }
     * @param pairs
     * @return
     */
    public int findLongestChain(int[][] pairs) {


        Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));

        int[] dp = new int[pairs.length];
        for (int n = 0; n < dp.length; n++) {
            dp[n] = 1;
            for (int i = 0; i < n; i++) {
                if (pairs[n][0] > pairs[i][1]) {
                    dp[n] = Math.max(dp[n], dp[i] + 1);
                }
            }
        }
        int max=-1;
        for (int i=0;i<dp.length;i++){
            max=Math.max(max,dp[i]);
        }

        return max;
    }
}
