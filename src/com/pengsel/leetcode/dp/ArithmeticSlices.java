package com.pengsel.leetcode.dp;

/**
 * 413. Arithmetic Slices (Medium)
 *注意是等差递增子区间
 * dp[i]表示以A[i]为结尾的等差递增子区间的个数;
 *
 * 当 A[i] - A[i-1] == A[i-1] - A[i-2]，才有机会构成一个子区间,[A[i-2], A[i-1], A[i]] 构成一个等差递增子区间
 * 递推式:dp[i]=dp[i-1]+1;
 */
public class ArithmeticSlices {


    public int num(int[] a){
        int last=0,sum=0;
        for (int i=2;i<a.length;i++){
            if (a[i]-a[i-1]==a[i-1]-a[i-2]){
                last= last+1;
                sum+=last;
            }else {
                last=0;
            }
        }
        return sum;
    }

}
