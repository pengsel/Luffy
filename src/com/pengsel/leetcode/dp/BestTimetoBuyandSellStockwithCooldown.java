package com.pengsel.leetcode.dp;

import java.util.Arrays;

import static java.lang.Math.max;

public class BestTimetoBuyandSellStockwithCooldown {

    /**
     * 本题的状态应为两个数组:
     * buy[n]表示第n天以buy(或者啥也不干)股票结束时的最大利润
     * sell[n]表示第n天以sell(或者啥也不干)股票时的最大利润
     *
     * 递推:
     * 中间有一天的冷却时间
     * buy[i]=max(sell[i-2]-prices[i], buy[i-1]);
     *
     * sell[i]=max(buy[i-1]+prices[i],sell[i-1]);
     *
     *
     * 进一步可以空间优化
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {

        int[] buy=new int[prices.length];
        int[] sell=new int[prices.length];

        buy[0]=-prices[0];
        sell[0]=Integer.MIN_VALUE;

        for (int i=1;i<prices.length;i++){
            buy[i]= max((i>2&&sell[i-2]>0?sell[i-2]:0)-prices[i],buy[i-1]);

            sell[i]= max(buy[i-1]+prices[i],sell[i-1]);
        }

        return sell[prices.length-1];
    }


    /**
     * with Transaction Fee
     *
     *
     * buy[i]=max(sell[i-1]-prices[i],buy[i-1]);
     *
     * @param prices
     */

    public int maxProfitWithFee(int[] prices,int fee){

        int[] buy=new int[prices.length];
        int[] sell=new int[prices.length];

        buy[0]=-prices[0];
//        sell[0]=Integer.MIN_VALUE;

        for (int i=1;i<prices.length;i++){
//            buy[i]=Math.max((i>2&&sell[i-2]>0?sell[i-2]:0)-prices[i],buy[i-1]);
//
//            sell[i]=Math.max(buy[i-1]+prices[i],sell[i-1]);

            buy[i]= max((sell[i-1]>0?sell[i-1]:0)-prices[i],buy[i-1]);
            sell[i]= max(buy[i-1]+prices[i]-fee,sell[i-1]);
        }

        return sell[prices.length-1];
    }


    /**
     * 只能交易两次
     *
     * 固定的buy[0],buy[1],sell[0],sell[1];
     *
     * sell[1]=max(sell[1],buy[1]+prices[i]);
     * buy[1]=max(hold[1],sell[0]-prices[i]);
     *
     * sell[0]=max(sell[0],buy[0]+prices[i]);
     * buy[0]=max(buy[0],-prices[i])
     * @param prices
     */

    public int maxProfitOf2Times(int[] prices){

        if(prices.length==0) return 0;
        int[] sell=new int[2];
        int[] buy=new int[2];

        //Integer.MIN_VALUE代表无效的状态,因为讨论前几次maxProfit是无效的
        Arrays.fill(sell,Integer.MIN_VALUE);

        buy[0]=-prices[0];
        buy[1]=Integer.MIN_VALUE;

        //这里的状态是必然发生了第二次交易时的情况,但是有时第二次是必然亏本的,甚至整个交易是亏本的,这时要选择不交易.
        //sell[0]是第一次交易最多获得多少利润
        for (int i=1;i<prices.length;i++) {

            if (buy[1]!=Integer.MIN_VALUE) {
                sell[1] = max(sell[1], buy[1] + prices[i]);
            }
            if (sell[0]!=Integer.MIN_VALUE) {
                buy[1] = max(buy[1], sell[0] - prices[i]);
            }

            sell[0] = max(sell[0], buy[0] + prices[i]);
            buy[0] = max(buy[0], -prices[i]);
        }

        return max(sell[0],sell[1])>0?max(sell[0],sell[1]):0;
    }

    /**
     * 自顶向下的做法
     * @param k
     * @param prices
     * @return
     */
    public int maxProfitOfKTimes(int k,int[] prices){

        if(prices.length==0|| k<1) return 0;

        if (k>prices.length/2){
            int profit = 0;
            for (int i = 1; i < prices.length; i++)
                // as long as there is a price gap, we gain a profit.
                if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
            return profit;
        }
        int[] sell=new int[k];
        int[] buy=new int[k];

        //Integer.MIN_VALUE代表无效的状态,因为讨论前几次maxProfit是无效的
        Arrays.fill(sell,Integer.MIN_VALUE);
        Arrays.fill(buy,Integer.MIN_VALUE);
        buy[0]=-prices[0];

        //这里的状态是必然发生了第二次交易时的情况,但是有时第二次是必然亏本的,甚至整个交易是亏本的,这时要选择不交易.
        //sell[0]是第一次交易最多获得多少利润
        for (int i=1;i<prices.length;i++) {

            for (int j=k-1;j>0;j--){
                if (buy[j]!=Integer.MIN_VALUE){
                    sell[j]=max(sell[j],buy[j]+prices[i]);
                }
                if (sell[j-1]!=Integer.MIN_VALUE){
                    buy[j]=max(buy[j],sell[j-1]-prices[i]);
                }
            }
            sell[0]=max(sell[0],buy[0]+prices[i]);
            buy[0]=max(buy[0],-prices[i]);
        }

        int max=0;
        for (int profit:sell){
            if (profit>max){
                max=profit;
            }
        }

        return max;
    }


    public static void main(String[] args) {
        int[] prices={1,2,3,4,5};
        System.out.println(new BestTimetoBuyandSellStockwithCooldown().maxProfitOf2Times(prices));
    }
}
