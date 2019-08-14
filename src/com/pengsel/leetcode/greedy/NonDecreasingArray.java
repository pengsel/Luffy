package com.pengsel.leetcode.greedy;

public class NonDecreasingArray {

    public boolean checkPossibility(int[] nums) {

        if (nums.length<=1)return true;

        int index=0;

        //判断有多少个异常点
        for (int i=1;i<nums.length;i++){

            if (nums[i]<nums[i-1]){
                if (index!=0){
                    return false;
                }
                index=i;
                continue;
            }
        }

        //0-无异常点;1-异常点在起始位置;length-1  异常点在末尾   这些情况均可以直接改
        //其他-异常点在中间 此时判断改nums[i-1]还是nums[i]两种情况
        return index==0 || index==1 || index==nums.length-1
                || nums[index+1]>=nums[index-1]|| nums[index-2]<=nums[index];

    }
}
