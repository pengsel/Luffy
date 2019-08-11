package com.pengsel.leetcode.dp;

public class EditDistance {


    /**
     * dp[i][j]代表从word1前i个字符转换成word2所需要的步骤
     *
     * 递推:
     * if(word1.charAt(i)==word2.charAt(j)){
     *     dp[i][j]=dp[i-1][j-1];
     * }else{
     *     dp[i][j]=1+min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])
     * }
     *
     * 这里面注意为什么可以这样做:
     * 1.dp[i][j]可以在dp[i-1][j]基础上删除一个字符
     * 2.dp[i][j]可以在dp[i][j-1]基础上添加一个字符
     * 3.dp[i][j]可以在dp[i-1][j-1]基础上替换最后一个字符
     *
     *
     * 初始:
     * dp[0][k]=k;
     * dp[k][0]=k;
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {

        if (word1.length()==0)return word2.length();
        if (word2.length()==0)return word1.length();

        int[][] dp=new int[word1.length()+1][word2.length()+1];

        for (int k=0;k<dp.length;k++){
            dp[k][0]=k;
        }

        for (int k=0;k<dp[0].length;k++){
            dp[0][k]=k;
        }


        for (int i=1;i<dp.length;i++){
            for (int j=1;j<dp[0].length;j++){
                if (word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else {
                    dp[i][j]=1+Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]));
                }
            }
        }


        return dp[word1.length()][word2.length()];
    }

}
