package com.pengsel.leetcode.greedy;

import java.util.Arrays;

/**
 * 455. Assign Cookies (Easy)
 *
 * Input: [1,2], [1,2,3]
 * Output: 2
 *
 * Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2.
 * You have 3 cookies and their sizes are big enough to gratify all of the children,
 * You need to output 2.
 */

class AssignCookies {
    /**
     * 从小到大排序，先满足胃口小的
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        if (g==null||g.length==0||s==null||s.length==0)
            return 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int count=0;
        int i=0,j=0;
        while (i<g.length&&j<s.length){
            if(g[i]<=s[j]){
                i++;
                count++;
            }
            j++;

        }
        return count;
    }
}