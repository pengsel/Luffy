package com.pengsel.iv.tencent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.min;

public class T1 {

    /**
     * dp[i][j]代表前i栋楼房，小Q在j栋楼能看到楼的数量
     * 递推关系：
     *
     * dp[i-1][j]前i-1栋楼，第j栋能看到的数量
     * 联系：增加了一栋楼，能不能看见它，取决于a[i-1]到a[j]之间的最大值有没有超过a[i]
     * 可以用一个数组存储从i到j的最高楼。
     * i>j的时候
     * if(a[i]>maxH(j,i-1)){
     *     dp[i][j]=dp[i-1][j]+1;
     * }else{
     *     dp[i][j]=dp[i-1][j];
     * }
     *
     * i=j

     *
     *
     * 初始
     */

//    public static void main(String[] args) {
//        Scanner scanner=new Scanner(System.in);
//        while (scanner.hasNext()){
//
//            int n= scanner.nextInt();
//            int[] heights=new int[n];
//
//            for (int i=0;i<n;i++){
//                heights[i]=scanner.nextInt();
//            }
//
//            int[][] maxH=new int[n][n];
//
//            maxH[0][0]=heights[0];
//            for (int i=1;i<n;i++){
//                maxH[0][i]=maxH[0][i-1]>heights[i]?maxH[0][i-1]:heights[i];
//                maxH[i][i]=heights[i];
//            }
//
//
//            for (int i=1;i<n;i++){
//
//                for (int j=i+1;j<n;j++){
//                    if (heights[j]>maxH[i][j-1]){
//                        maxH[i][j]=heights[j];
//                    }else {
//                        maxH[i][j]=maxH[i][j-1];
//                    }
//                }
//            }
//
//            int[][] dp=new int[n][n];
//            dp[0][0]=1;
//            dp[1][0]=2;
//            dp[1][1]=2;
//
//            for (int i=2;i<n;i++){
//
//                for (int j=0;j<=i;j++){
//
////                    if (i==j){
////                        dp[i-1][j-1]+1;
////                    }
//                    if (i==j+1){
//                        dp[i][j]=dp[i-1][j]+1;
//                    }
//
//                    if (heights[i]>maxH[j][i-1]){
//                        dp[i][j]=dp[i-1][j]+1;
//                    }else {
//                        dp[i][j]=dp[i-1][j];
//                    }
//                }
//            }
//            StringBuilder ret=new StringBuilder();
//            for (int i=0;i<n;i++){
//                ret.append(dp[n-1][i]).append(" ");
//            }
//
//            System.out.println(ret);
//
//        }
//    }


    public static int[] canSeeHowManyBuildeing(int[] heights){

        int[] amounts=new int[heights.length];
        for (int i=0;i<heights.length;i++){
            //可以看到它本身
            amounts[i]=1;
            //左边能看多少
            amounts[i]+=leftBuildings(heights,i);
            //右边能看多少
            amounts[i]+=rightBuildings(heights,i);
        }
        System.out.println(Arrays.toString(amounts));
        return amounts;
    }

    private static int leftBuildings(int[] heights, int index){
        //左边只有一栋或者没有的时候，不存在遮挡
        if (index<2) {
            return index;
        }
        //必然能看到左边的一栋
        int count=1;
        //记录当前距离heights[index]这栋楼最高的楼，如果低于它将会被遮挡。
        int maxHeight=heights[index-1];
        //多余两栋以上时，存在遮挡
        //遮挡发生在左边靠近i的位置高度要比远离i的位置要高
        for (int i=index-2;i>=0;i--){
            if (heights[i]>maxHeight){
                count++;
                maxHeight=heights[i];
            }
        }

        return count;

    }

    private static int rightBuildings(int[] heights, int index){
        //左边只有一栋或者没有的时候，不存在遮挡
        if (index>heights.length-3) {
            return heights.length-1-index;
        }
        //必然能看到左边的一栋
        int count=1;
        //记录当前距离heights[index]这栋楼最高的楼，如果低于它将会被遮挡。
        int maxHeight=heights[index+1];
        //多余两栋以上时，存在遮挡
        //遮挡发生在右边靠近i的位置高度要比远离i的位置要高
        for (int i=index+2;i<heights.length;i++){
            if (heights[i]>maxHeight){
                count++;
                maxHeight=heights[i];
            }
        }

        return count;

    }


    public static void main(String[] args) {
//        int[] a1=new int[]{5,3,8,3,2,5};
//
//        canSeeHowManyBuildeing(a1);

        int[] a1=new int[]{1,1,0,0};
        int[] a2=new int[]{0,1,1,0};

        System.out.println(leastDayRest(a1,a2));
    }


    /**
     * w[i]代表第i天工作或休息時 最少的休息天數
     * e[i]代表第i天锻炼或休息时 最少的休息天数
     * r[i]代表第i天休息时 最少的休息天数
     * if(work[i]==1&&exercise[i]==1){
     *      w[i]=min(e[i-1],r[i-1]+1);
     *      e[i]=min(w[i-1],r[i-1]+1);
     *      r[i]=min(e[i-1],w[i-1],r[i-1])+1;
     * }
     * 
     * 
     *
     *
     *
     * e[i]=max(w[i-1],e[i-2]+1)
     *
     *
     * 初始值：
     *
     * @param work
     * @param exercise
     * @return
     */

    public static int leastDayRest(int[] work,int[] exercise){

        int[] w=new int[work.length];
        int[] e=new int[work.length];
        int[] r=new int[work.length];
        

        w[0]=work[0]>0?0:1;
        e[0]=exercise[0]>0?0:1;
        r[0]=1;

        for (int i=1;i<work.length;i++){
            //休息
            r[i] = min(min(e[i - 1], w[i - 1]), r[i - 1]) + 1;
            //工作
            int workDay = min(e[i - 1], r[i - 1] + 1);
            //锻炼
            int exerciseDay = min(w[i - 1], r[i - 1] + 1);
            if(work[i]==1&&exercise[i]==1) {
                w[i] = workDay;
                e[i] = exerciseDay;
            }
            if (work[i]==1&&exercise[i]==0){
                w[i]= workDay;
                e[i]=r[i];
            }
            if (work[i]==0&&exercise[i]==1){
                w[i]=r[i];
                e[i]= exerciseDay;
            }
            
            if (work[i]==0&&exercise[i]==0){

                w[i]=r[i];
                e[i]=r[i];
            }
        }

        return min(w[work.length-1],min(e[work.length-1],r[work.length-1]));

    }
}