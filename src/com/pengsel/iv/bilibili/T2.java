package com.pengsel.iv.bilibili;

import java.lang.String;

import java.util.Arrays;
import java.util.Scanner;

public class T2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            String line=in.nextLine();
            String[] nums=line.split(",");
            Arrays.sort(nums,(a,b)->{
                String ab=a.concat(b);
                String ba=b.concat(a);
                for (int i=0;i<ab.length();i++){
                    if (ab.charAt(i)-ba.charAt(i)>0){
                        return 1;
                    }else if (ab.charAt(i)-ba.charAt(i)<0){
                        return -1;
                    }
                }
                return 0;
            });
            StringBuilder sb=new StringBuilder();
            for (String s:nums){
                sb.append(s);
            }
            System.out.println(sb);

        }
    }
}
