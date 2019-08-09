package com.pengsel.leetcode;

import java.util.Arrays;

public class PartitionEqualSubsetSum {

    /**
     * 看成是体积为sum/2的0-1背包问题
     *
     *
     * dp[i][j]表示前i个数范围内，最大和不超过j的
     * 当dp[i][j]==sum/2时，表示这i个数的范围内选取一些数可以和为sum/2，返回true
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum=0;
        for (int n:nums){
            sum+=n;
        }
        if (sum%2!=0) return false;
        int w=sum/2;
        int[][] dp=new int[nums.length+1][w+1];
//        int[] dp=new int[w+1];

        for (int i=1;i<nums.length;i++){
            for (int j=1;j<=w;j++){
                if (j>=nums[i]){
                    dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-nums[i]]+nums[i]);
                }else {
                    dp[i][j]=dp[i-1][j];
                }
                if (dp[i][j]==w)return true;
            }
        }
        return false;
    }


    /**
     * 空间优化
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        int sum=0;
        for (int n:nums){
            sum+=n;
        }
        if (sum%2!=0) return false;
        int w=sum/2;
        int[] dp=new int[w+1];

        for (int i=1;i<nums.length;i++){
            for (int j=w;j>0;j--){
                if (j>=nums[i]){
                    dp[j]=Math.max(dp[j],dp[j-nums[i]]+nums[i]);
                }else {
                    dp[j]=dp[j];
                }
                if (dp[j]==w)return true;
            }
        }
        return false;
    }


    /**
     * 494. Target Sum
     *
     *
     * 现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。
     *
     * 你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。

     *
     * 该问题可以转换为 Subset Sum 问题，从而使用 0-1 背包的方法来求解。
     *
     * 可以将这组数看成两部分，P 和 N，其中 P 使用正号，N 使用负号，有以下推导：
     *
     *                   sum(P) - sum(N) = target
     * sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
     *                        2 * sum(P) = target + sum(nums)
     * 因此只要找到一个子集，令它们都取正号，并且和等于 (target + sum(nums))/2，就证明存在解。
     */

    public int findTargetSumWays(int[] nums, int S) {
        int sum = computeArraySum(nums);
        if (sum < S || (sum + S) % 2 == 1) {
            return 0;
        }
        int W = (sum + S) / 2;
        int[] dp = new int[W + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int i = W; i >= num; i--) {
                dp[i] = dp[i] + dp[i - num];
            }
        }
        return dp[W];
    }

    private int computeArraySum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }


    /**
     * 01 字符构成最多的字符串
     *
     * dp[i][j][k]在前i个字符串范围中，由j个0和k个1最多能构成strs字符串的数量
     *
     * 递归：
     * if(j>=str[i]中0的数量且k>=str[i]中1的数量){
     *     dp[i][j][k]=max(dp[i-1][j][k],dp[i-1][j-str[i]中0的数量][k-str[i]中1的数量]+1);
     * }else{
     *     dp[i][j][k]=dp[i-1][j][k];
     * }
     *
     *
     * 可以进行空间优化，使用二维数组
     *
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int l = strs.length;
        int[][][] dp = new int[l+1][m+1][n+1];

        for (int i = 0; i < l+1; i++) {
            int[] nums = new int[]{0,0};
            if (i > 0) {
                nums = calculate(strs[i-1]);
            }
            for (int j = 0; j < m+1; j++) {
                for (int k = 0; k < n+1; k++) {
                    if (i == 0) {
                        dp[i][j][k] = 0;
                    } else if (j>=nums[0] && k>=nums[1]) {
                        dp[i][j][k] = Math.max(dp[i-1][j][k], dp[i-1][j-nums[0]][k-nums[1]]+1);
                    } else {
                        dp[i][j][k] = dp[i-1][j][k];
                    }
                }
            }
        }

        return dp[l][m][n];
    }

    private int[] calculate(String str) {
        int[] res = new int[2];
        Arrays.fill(res, 0);

        for (char ch : str.toCharArray()) {
            if (ch == '0') {
                res[0]++;
            } else if (ch == '1') {
                res[1]++;
            }
        }

        return res;
    }









}
