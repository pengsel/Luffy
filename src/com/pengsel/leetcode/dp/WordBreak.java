package com.pengsel.leetcode.dp;

import java.util.List;

public class WordBreak {


    /**
     * dp[i]表示s.substring(0,i)可以由wordDict中的字符串组成
     *
     * 递推:
     * for(字典终点额每个字符串str){
     *     if(dp[i] && s.substring(i,str.length()+i))
     * }
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {


        boolean[] dp=new boolean[s.length()+1];
        dp[0]=true;
        for (int i=1;i<dp.length;i++){

            for (String str:wordDict){
                //必须在当前str长度小于等于构成的字符串长度
                if (str.length()<=i){
                    //如果前面的为true,并且尾部的字符串相等,那么说明当前字符串s.substring(0,i)是可以由字典构成的.
                    if (dp[i-str.length()]  &&  str.equals(s.substring(i-str.length(),i))){
                        dp[i]=true;
                    }
                }
            }
        }

        return dp[s.length()];
    }
}
