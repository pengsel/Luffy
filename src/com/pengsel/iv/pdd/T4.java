package com.pengsel.iv.pdd;

import java.util.*;

/**
 * 输入:
 * N
 * N个积木的长度
 * N个积木的重量
 *
 * 累积木.上面的要比下面的长;
 * 积木承受的重量不得高于它本身重量的7倍,最高搭多少层.
 */
public class T4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            String line1=in.nextLine();
            String l2=in.nextLine();
            String l3=in.nextLine();
            int n=Integer.valueOf(line1);
            String[] lengths=l2.split(" ");
            String[] weights=l3.split(" ");
            int[] ls=new int[n];
            int[] ws=new int[n];

            Jimu[] js=new Jimu[n];

            for (int i=0;i<n;i++){
                ls[i]=Integer.valueOf(lengths[i]);
                ws[i]=Integer.valueOf(weights[i]);
                Jimu j=new Jimu(ls[i],ws[i]);
                js[i]=j;
            }

            Arrays.sort(js, Comparator.comparingInt(a -> a.length));
            Set<Integer> set=new HashSet<>();
            for (int i:ls){
                set.add(i);
            }

            System.out.println();
        }

    }

    static class Jimu{
        int length;
        int weight;

        public Jimu(int length, int weight) {
            this.length = length;
            this.weight = weight;
        }
    }
}
