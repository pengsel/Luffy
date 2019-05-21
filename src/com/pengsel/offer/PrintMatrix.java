package com.pengsel.offer;

import java.util.ArrayList;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
 */
public class PrintMatrix {
    /**
     * 一圈一个单位，先考虑一圈如何处理。
     * @param matrix
     * @return
     */

    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> ret=new ArrayList<>();
        int r0=0,r1=matrix.length-1,c0=0,c1=matrix[0].length-1;
        while(r0<=r1&&c0<=c1){
            for(int i=c0;i<=c1;i++)
                ret.add(matrix[r0][i]);
            for(int j=r0+1;j<=r1;j++)
                ret.add(matrix[j][c1]);
            if (r1>r0) {
                for (int i = c1 - 1; i >= c0; i--)
                    ret.add(matrix[r1][i]);
            }
            if (c1>c0) {
                for (int j = r1 - 1; j > r0; j--)
                    ret.add(matrix[j][c0]);
            }
            r0++;r1--;
            c0++;c1--;
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] ma={{1},{2},{3},{4},{5}};
        new PrintMatrix().printMatrix(ma);
    }
}
