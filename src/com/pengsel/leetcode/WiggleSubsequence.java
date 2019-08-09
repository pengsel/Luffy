package com.pengsel.leetcode;


import java.util.Arrays;

public class WiggleSubsequence {


    /**
     * 复杂度 O(n平方)
     * up[n]以a[n]结束(rising wiggle)的最长的子序列
     * down[n]以a[n]结束(falling wiggle)的最长子序列
     * for(int i=0;i<n;i++){
     *     if(a[n]>a[i]){
     *         up[n]=Math.max(up[n],down[i]+1);
     *     }
     *     if(a[n]<a[i]){
     *         down[n]=Math.max(down[n],up[i]+1);
     *     }
     * }
     * if(a[n]>a[])
     */
    public int wiggleMaxLength(int[] nums) {
        if(nums.length<2)return nums.length;
        int[] up=new int[nums.length];
        int[] down=new int[nums.length];
        Arrays.fill(up,1);
        Arrays.fill(down,1);

        for (int i=1;i<nums.length;i++){
            for (int j=0;j<i;j++){
                if (nums[i]>nums[j]){
                    up[i]=Math.max(up[i],down[j]+1);
                }
                if (nums[i]<nums[j]){
                    down[i]=Math.max(down[i],up[j]+1);
                }
            }
        }

        return Math.max(down[nums.length - 1], up[nums.length - 1]);
    }


    /**
     * 线性动态规划，复杂度O(n)，空间复杂度可以缩小至O(1)
     * Any element in the array could correspond to only one of the three possible states:
     *
     * up position, it means nums[i] > nums[i-1]nums[i]>nums[i−1]
     * down position, it means nums[i] < nums[i-1]nums[i]<nums[i−1]
     * equals to position, nums[i] == nums[i-1]nums[i]==nums[i−1]
     * @param nums
     * @return
     */
    public int wiggleMaxLength2(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        up[0] = down[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                down[i] = up[i - 1] + 1;
                up[i] = up[i - 1];
            } else {
                down[i] = down[i - 1];
                up[i] = up[i - 1];
            }
        }
        return Math.max(down[nums.length - 1], up[nums.length - 1]);
    }


    /**
     * 最长公共子序列：
     * 对于两个子序列 S1 和 S2，找出它们最长的公共子序列。
     *
     * 定义一个二维数组 dp 用来存储最长公共子序列的长度，其中 dp[i][j] 表示 S1 的前 i 个字符与 S2 的前 j 个字符最长公共子序列的长度。考虑 S1i 与 S2j 值是否相等，分为两种情况：
     *
     * 当 S1i==S2j 时，那么就能在 S1 的前 i-1 个字符与 S2 的前 j-1 个字符最长公共子序列的基础上再加上 S1i 这个值，最长公共子序列长度加 1，即 dp[i][j] = dp[i-1][j-1] + 1。
     * 当 S1i != S2j 时，此时最长公共子序列为 S1 的前 i-1 个字符和 S2 的前 j 个字符最长公共子序列，或者 S1 的前 i 个字符和 S2 的前 j-1 个字符最长公共子序列，取它们的最大者，即 dp[i][j] = max{ dp[i-1][j], dp[i][j-1] }
     * @param nums1
     * @param nums2
     * @return
     */
    public int lengthOfLCS(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n1][n2];
    }


    /**
     * 0-1背包
     *有一个容量为 N 的背包，要用这个背包装下物品的价值最大，这些物品有两个属性：体积 w 和价值 v。
     *
     * 定义一个二维数组 dp 存储最大价值，其中 dp[i][j] 表示前 i 件物品体积不超过 j 的情况下能达到的最大价值。设第 i 件物品体积为 w，价值为 v，根据第 i 件物品是否添加到背包中，可以分两种情况讨论：
     *
     * 第 i 件物品没添加到背包，总体积不超过 j 的前 i 件物品的最大价值就是总体积不超过 j 的前 i-1 件物品的最大价值，dp[i][j] = dp[i-1][j]。
     * 第 i 件物品添加到背包中，dp[i][j] = dp[i-1][j-w] + v。
     *
     *
     *
     * dp[i][j]为范围为前i件商品的情况下，总体积不超过j的最大价值。
     *
     * 递推：
     * dp[i][j]=max(dp[i-1][j],dp[i-1][j-w]+v);
     *
     *
     * 可以进行空间优化：
     * 前i件物品的状态只和前i-1件物品的状态有关，可以将dp定义成一维数组，
     * 但是注意更新的时候应该从右侧开始更新，防止需要用到的dp[i-1][j-w]被覆盖
     *
     * @param w 背包容量
     * @param weights
     * @param values
     * @return
     */
    public int pack(int w, int[] weights, int[] values){
        int[][] dp=new int[weights.length+1][w+1];
        for (int i=1;i<=weights.length;i++){
            for (int j=1;j<=w;j++){
                if (j>=weights[i]){
                    dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-weights[i-1]]+values[i-1]);
                }else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }

        return dp[weights.length][w];
    }

    /**
     * 空间优化
     * @param W
     * @param N
     * @param weights
     * @param values
     * @return
     */

    public int knapsack(int W, int N, int[] weights, int[] values) {
        int[] dp = new int[W + 1];
        for (int i = 1; i <= N; i++) {
            int w = weights[i - 1], v = values[i - 1];
            for (int j = W; j >= 1; j--) {
                if (j >= w) {
                    dp[j] = Math.max(dp[j], dp[j - w] + v);
                }
            }
        }
        return dp[W];
    }

}
