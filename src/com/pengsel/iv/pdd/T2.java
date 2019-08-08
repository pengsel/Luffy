package com.pengsel.iv.pdd;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class T2 {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()){
            String a=scanner.nextLine();
            String[] as=a.split(" ");
            Map<Character,Integer> map=new HashMap<>();
            for (String s:as){
                if (map.containsKey(s.charAt(0))){
                    map.put(s.charAt(0),map.get(s.charAt(0))+1);
                }else {
                    map.put(s.charAt(0),1);
                }
                if (map.containsKey(s.charAt(s.length()-1))){
                    map.put(s.charAt(s.length()-1),map.get(s.charAt(s.length()-1))+1);
                }else {
                    map.put(s.charAt(s.length()-1),1);
                }
            }

            Iterator iterator=map.keySet().iterator();
            boolean circle=true;
            while (iterator.hasNext()){
                char ch=(char)iterator.next();
                if (map.get(ch)%2!=0){
                    circle=false;
                    break;
                }
            }
            System.out.println(circle);
        }


    }
}
