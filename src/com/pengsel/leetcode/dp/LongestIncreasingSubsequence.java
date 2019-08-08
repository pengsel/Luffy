package com.pengsel.leetcode.dp;

/**
 * 子问题,以a[n]为结尾的序列的最长递增子序列
 * 状态: d[n]为以a[n]为结尾的序列的最长递增子序列
 * 递归:
 * d[n]可以根据前面的额数组得到
 * for(int i=0;i<n;i++){
 *     if(a[n]>a[i]){
 *         d[n]=max(d[n],d[i]+1);
 *     }
 * }
 *
 * 最后输出d[n]的最大值
 *
 */
public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {

        if(nums==null||nums.length==0)return 0;
        int[] d=new int[nums.length];
        for (int i=0;i<d.length;i++){
            d[i]=1;
            for (int j=0;j<i;j++){
                if (nums[i]>nums[j]){
                    d[i]=Math.max(d[i],d[j]+1);
                }
            }
        }
        int max=0;
        for(int n:d){
            max=Math.max(max,n);
        }

        return max;

    }
}
