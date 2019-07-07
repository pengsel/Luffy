package com.pengsel.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
public class FindMinArrowShots {
    /**
     * 以起始位置排序，找不直接相交(相交区间内的所有区间都要相交)的区间个数
     * 两区间不相交，count++，更新end
     * 相交，更新end
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        if(points.length<2)
            return points.length;
        Arrays.sort(points,(p1,p2)->p1[0]-p2[0]);
        int count=1;
        int end=points[0][1];
        for (int i=0;i<points.length;i++){
            if (points[i][0]<=end){
                end=Math.min(end,points[i][1]);
            }
            else {
                end=points[i][1];
                count++;
            }
        }
        return count;
    }

    class BalloonComparator implements Comparator<int []>{
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[0]-o2[0];
        }
    }

}
