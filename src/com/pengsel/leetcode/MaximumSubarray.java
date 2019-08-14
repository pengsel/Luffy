package com.pengsel.leetcode;

public class MaximumSubarray {

    public int maxSubArray(int[] nums) {

        int sum=nums[0];
        int max=sum;
        for (int i=1;i<nums.length;i++){
            if (sum<=0){
                sum=Math.max(sum,nums[i]);
            }else {
                sum+=nums[i];
            }

            if (sum>max){
                max=sum;
            }
        }

        return max;
    }


    /**
     * 采用动态规划
     *
     * 子问题:
     * dp[i] 以A[i]结尾的maxSubArray
     *
     * 递归:
     * dp[i]=dp[i-1]>0?dp[i-1]+A[i]:A[i];
     */

    public int maxSubArray2(int[] A) {
        int n = A.length;
        int[] dp = new int[n];//dp[i] means the maximum subarray ending with A[i];
        dp[0] = A[0];
        int max = dp[0];

        for(int i = 1; i < n; i++){
            dp[i] = A[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(max, dp[i]);
        }

        return max;
    }

}
