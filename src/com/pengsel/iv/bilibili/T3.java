package com.pengsel.iv.bilibili;

import java.util.Scanner;

public class T3 {
    /**
     * 背包问题
     *
     * dp[i][j]代表前i件物品正好体积为j的最大价值总和
     * dp[i][j]=max(dp[i-1][j],dp[i][j-wi]+vi)
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            String line1=in.nextLine();
            String line2=in.nextLine();
            String line3=in.nextLine();
            String line4=in.nextLine();
            int n=Integer.valueOf(line1);
            int m=Integer.valueOf(line2);

            int[] w=new int[n];
            String[] ws=line3.split(" ");
            for (int i=0;i<n;i++){
                w[i]=Integer.valueOf(ws[i]);
            }

            int[] v=new int[n];
            String[] vs=line4.split(" ");
            for (int i=0;i<n;i++){
                v[i]=Integer.valueOf(vs[i]);
            }

            int[][] dp=new int[n+1][m+1];


            for (int i=1;i<n+1;i++){
                for (int j=w[i-1];j<m+1;j++){
                    dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-w[i-1]]+v[i-1]);
                }
            }
String


            System.out.println(dp[n][m]);


        }
    }
}
