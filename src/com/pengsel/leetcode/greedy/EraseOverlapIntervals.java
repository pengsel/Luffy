package com.pengsel.leetcode.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 *
 * Note:
 *
 * You may assume the interval's end point is always bigger than its start point.
 * Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
 *
 *
 * Example 1:
 *
 * Input: [ [1,2], [2,3], [3,4], [1,3] ]
 *
 * Output: 1
 *
 * Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
 */
public class EraseOverlapIntervals {
    /**
     * 用一个优先队列，排序规则按照区间的结束位置；
     * 出队列时，若新区间和老区间重合，则count++，该区间会被去掉；
     * 否则，将老区间指向当前的新区间。
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {

        PriorityQueue<int[]> queue =new PriorityQueue<>(new IntervalComparator());
        for (int[] interval : intervals) {
            queue.offer(interval);
        }
        int count=0;
        int[] last=queue.poll();
        while (queue.size()>0){
            int[] current=queue.poll();
            if(current[0]<last[1]){
                count++;
            }else {
                last=current;
            }
        }
        return count;


    }

    public class IntervalComparator implements Comparator<int[]> {

        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[1]-o2[1];
        }
    }
}
