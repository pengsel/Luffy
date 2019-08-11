package com.pengsel.leetcode.dp;

public class DeleteOperationforTwoStrings {


    /**
     * 按照最长公共子序列
     *
     *
     * dp[i][j]表示以word1.charAt(i)和word2.charAt(j)为终点时的最长公共子序列
     *
     * 递推:
     *
     * if(word1.charAt(i)==word2.charAt(j)){
     *     dp[i][j]=max(dp[i-1][j],dp[i][j-1])+1;
     * }else{
     *     dp[i][j]=max(dp[i-1][j],dp[i][j-1]);
     * }
     *
     * 初始:
     * dp[0][j]=0;
     * dp[i][0]=0;
     *
     *
     *
     *
     */

    public int minDistance(String word1, String word2) {
        int dp[][] = new int[word1.length()+1][word2.length()+1];
        for(int i = 0; i <= word1.length(); i++) {
            for(int j = 0; j <= word2.length(); j++) {
                if(i == 0 || j == 0) dp[i][j] = 0;
                else dp[i][j] = (word1.charAt(i-1) == word2.charAt(j-1)) ? dp[i-1][j-1] + 1
                        : Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        int val =  dp[word1.length()][word2.length()];
        return word1.length() - val + word2.length() - val;
    }

}
