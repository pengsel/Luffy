package com.pengsel.leetcode.dp;

/**
 * 递推关系:
 * 对于每一个第2个开始的字符
 * if(该字符在1到9之内){
 *     dp[i]+=dp[i-1];
 * }
 * if(该字符和它前面的字符在10到26之间){
 *     dp[i]+=dp[i-2];
 * }
 */
public class DecodeWays {
    public int numDecodings(String s) {
        if (s==null||s.length()==0||s.charAt(0)=='0')return 0;
        int[] dp=new int[s.length()];
        dp[0]=1;
        for (int i=1;i<s.length();i++){
            if ((s.charAt(i)-'0')>=1&&(s.charAt(i)-'0')<=9){
                dp[i]+=dp[i-1];
            }
            if ((Integer.valueOf(s.substring(i-1,i+1))<=26)
                &&Integer.valueOf(s.substring(i-1,i+1))>=10
            ){
                if (i!=1) {
                    dp[i] += dp[i - 2];
                }else {
                    dp[i]++;
                }
            }
        }
        return dp[s.length()-1];

    }
}
