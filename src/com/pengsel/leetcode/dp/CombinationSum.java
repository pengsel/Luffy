package com.pengsel.leetcode.dp;

import java.util.Arrays;

public class CombinationSum {

    /**
     * dp[n]表示和为n时,有多少种方式
     *
     * 递归:
     * 人人为我
     * 直接从最大的值开始,先从dp数组中查询,遇见没计算的就用递归进行计算,并且把数据存到dp[];
     *
     *
     * 我为人人
     * 从小到大往上算
     * @param nums
     * @param target
     * @return
     */


    private int[] dp;
    public int combinationSum4(int[] nums, int target) {

        dp=new int[target+1];
        Arrays.fill(dp,-1);
        dp[0]=1;
        return recursive(nums,target);
    }

    private int recursive(int[] nums, int target){
        if (dp[target]!=-1){
            return dp[target];
        }
        int sum=0;
        for(int i=0;i<nums.length;i++){
            if (target>=nums[i]){
                int temp=recursive(nums,target-nums[i]);
                if (temp!=-1) {
                    sum += temp;
                }
            }
        }

        dp[target]=sum;

        return sum;
    }


    public int combinationSum4_2(int[] nums, int target) {
        int[] dp=new int[target+1];
        dp[0]=1;

        for (int i=1;i<=target;i++){
            for (int j=0;j<nums.length;j++){
                if (nums[j]<=i){
                    //如果当前i小于nums[j],dp[i-nums[j]]加上一个nums[j]是可行的方案
                    dp[i]+=dp[i-nums[j]];
                }
            }
        }

        return dp[target];
    }



}
