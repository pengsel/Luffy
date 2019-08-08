package com.pengsel.leetcode.dp;

/**
 * 279.完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * dp[0] = 0
 * dp[1] = dp[0]+1 = 1
 * dp[2] = dp[1]+1 = 2
 * dp[3] = dp[2]+1 = 3
 * dp[4] = Min{ dp[4-1*1]+1, dp[4-2*2]+1 }
 *       = Min{ dp[3]+1, dp[0]+1 }
 *       = 1
 * dp[5] = Min{ dp[5-1*1]+1, dp[5-2*2]+1 }
 *       = Min{ dp[4]+1, dp[1]+1 }
 *       = 2
 * 						.
 * 						.
 * 						.
 * dp[13] = Min{ dp[13-1*1]+1, dp[13-2*2]+1, dp[13-3*3]+1 }
 *        = Min{ dp[12]+1, dp[9]+1, dp[4]+1 }
 *        = 2
 * 						.
 * 						.
 * 						.
 * dp[n] = Min{ dp[n - i*i] + 1 },  n - i*i >=0 && i >= 1
 *
 *
 * dp[i]=min{dp[i-j*j]+1},j*j<=i
 */
public class NumSquares {
    public static int numSquares(int n) {
        int[] dp=new int[n+1];
        for (int i=0;i<=n;i++){
            for (int j=1;j*j<=i;j++){
                if (dp[i]!=0) {
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                }else {
                    dp[i]=dp[i-j*j]+1;
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(numSquares(13));
    }
}
