package com.pengsel.iv.bilibili;

import java.util.Scanner;

public class T1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            String line=in.nextLine();
            String[] words=line.split(" ");
            StringBuilder sb=new StringBuilder();
            for (int i=words.length-1;i>=0;i--){
                sb.append(words[i]);
                if (i!=0){
                    sb.append(" ");
                }
            }
            System.out.println(sb.toString());

//            char[] cs=line.toCharArray();
//            swap(cs,0,cs.length-1);
//            int lastSpace=0;
//            for (int i=0;i<cs.length;i++){
//                if (cs[i]==' '){
//                    swap(cs,lastSpace,i-1);
//                    lastSpace=i+1;
//                }
//            }
//
//            System.out.println(new String(cs));
        }
    }


    private static void swap(char[] chars,int start,int end){

        while (start<end){
            char temp=chars[start];
            chars[start]=chars[end];
            chars[end]=temp;
            start++;
            end--;
        }
    }
}
