package com.pengsel.iv.pdd;

import java.util.Arrays;
import java.util.Scanner;

public class T1 {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()){
            String a=scanner.nextLine();
            String b=scanner.nextLine();
            String[] as=a.split(" ");
            String[] bs=b.split(" ");
            int[] ai=new int[as.length];
            int[] bi=new int[bs.length];
            for (int i=0;i<as.length;i++){
                ai[i]=Integer.valueOf(as[i]);
            }
            for (int i=0;i<bs.length;i++){
                bi[i]=Integer.valueOf(bs[i]);
            }
            get(ai,bi);
        }
    }

    /**
     * 遍历a,找到递增序列的异常点.前后即为范围,x(下限),y(上限)
     * 如果为最后一个,则取b的最大值 并且必须大于x,
     * 如果为最前面一个,取值小于y的最大值
     * 如果在中间,取在x,y区间的最大值
     *
     * 找出异常点;
     * 异常点特征是:在一个严格递增序列里面,忽然有一个点大于等于它后面的数
     *
     * b应该是有序的;
     * 有序情况下,再遍历
     * @param a
     * @param b
     */
    private static void get(int[] a,int[] b){
        int x=-1,y=-1;
        for (int i=0;i<a.length-1;i++){
            if (a[i]>=a[i+1]){
                x=i-1;
                y=i+1;
                break;
            }
        }
        Arrays.sort(b);

        for (int i=b.length-1;i>=0;i--){
            if (y==1){
                if (b[i]<a[y]) {
                    a[0]=b[i];
                    print(a);
                    return;
                }
                if (b[i]>a[0]&&b[i]<a[2]){
                    a[1]=b[2];
                    print(a);
                    return;
                }
            }else if (x==a.length-2){
                if (b[i]>a[x]) {
                    a[x+1]=b[i];
                    print(a);
                    return;
                }
                if (b[i]<a[x+1]&&b[i]>a[x-1]){
                    a[x]=b[i];
                    print(a);
                    return;
                }
            }else {
                if (b[i]<a[y] && b[i]>a[x]) {
                    a[y-1]=b[i];
                    print(a);
                    return;
                }
                if (b[i]>a[y-1]&&b[i]<a[y+1]){
                    a[y]=b[i];
                    print(a);
                    return;
                }
            }
        }
        System.out.println("NO");
    }

    private static void print(int[] a){
        StringBuilder sb=new StringBuilder();
        for (int i:a){
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
    }
}
