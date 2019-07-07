package com.pengsel.leetcode;

public class MaxProfit {
    /**
     * 更新历史最低价
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if(prices==null || prices.length==0)
            return 0;
        int recordLow=prices[0];
        int maxProfit=0;
        for (int i=1;i<prices.length;i++){
            if (prices[i]<recordLow)
                recordLow=prices[i];
            else {
                maxProfit=Math.max(maxProfit,prices[i]-recordLow);
            }
        }
        return maxProfit;

    }
}
