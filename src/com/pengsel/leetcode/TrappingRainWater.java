package com.pengsel.leetcode;

public class TrappingRainWater {

    /**
     * max_left[i]记录从0到i-1最大的柱子高度
     * max_right[i]记录从i+1到length-1最高的柱子高度
     *
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {

        int[] max_left=new int[height.length];
        int[] max_right=new int[height.length];

        for (int i=1;i<height.length;i++){
            max_left[i]=Math.max(max_left[i-1],height[i-1]);
        }

        for (int i=height.length-2;i>0;i--){
            max_right[i]=Math.max(max_right[i+1],height[i+1]);
        }


        int ans=0;
        for (int i=1;i<height.length-1;i++){
            int water=Math.min(max_left[i],max_right[i])-height[i];
            ans+=water>0?water:0;
        }

        return ans;
    }
}
