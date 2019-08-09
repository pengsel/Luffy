package com.pengsel.leetcode;

import java.util.Arrays;

public class CoinChange {


    /**
     * 自顶向下，人人为我
     * dp[n]表示达到总数量为n时最少的硬币数
     *
     * 假设最后一个硬币是coin：
     *     dp[n]=min(dp[n-coin])+1，coin 为coins中的一个
     *     dp[0]=0
     *     dp[S]=-1,当可coins.length==0;
     *
     *
     *
     * getDP()
     *
     *
     *
     * 自底向上，我为人人
     * @param coins
     * @param amount
     * @return
     */


    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange(coins, amount, new int[amount]);
    }

    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (count[rem - 1] != 0) return count[rem - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, count);
            if (res >= 0 && res < min)
                min = 1 + res;
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }


    /**
     * dp[amount+1]从小到大往上计算。
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange2(int[] coins, int amount) {
        //max代表初始值，也是一个不可能达到的值
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    /**
     * 518. Coin Change 2
     *
     * You are given coins of different denominations and a total amount of money.
     * Write a function to compute the number of combinations that make up that amount.
     * You may assume that you have infinite number of each kind of coin.
     *
     *
     * dp[i][j],前i种coin组合成j的数量；
     * 递归：
     * 求的是组合的数量：
     * 前一部分是不包含coins[i-1]时的组合数量；
     * 后一部分是包含coinsp[i-1]时的组合数量
     * dp[i][j]=dp[i-1]dp[j]+dp[i][j-coins[i-1]];
     *
     * dp[i][0]所代表的的是一种可行的方案，此时减去一个coins[m]中的一个时，刚好满足；
     * dp[i][0]=1;
     */


    public int change(int amount, int[] coins) {

        //可以优化成一维数组
//        int[][] dp=new int[coins.length+1][amount+1];

        int[] dp=new int[amount+1];
//        dp[0][0]=1;
        for (int i=1;i<=coins.length;i++){
//            dp[i][0]=1;

            dp[0]=1;
            for (int j=0;j<=amount;j++){
//                dp[i][j] = dp[i-1][j] + (j >= coins[i-1] ? dp[i][j-coins[i-1]] : 0);

                dp[j]=dp[j]+(j>=coins[i-1]? dp[j-coins[i-1]]:0);
            }

        }

//        return dp[coins.length][amount];

        return dp[amount];
    }
}
