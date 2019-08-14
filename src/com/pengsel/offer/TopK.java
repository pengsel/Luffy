package com.pengsel.offer;

import java.util.ArrayList;

import java.util.PriorityQueue;

public class TopK {

    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {

        if (k > input.length || k <= 0){
            return new ArrayList<>();
        }

        PriorityQueue<Integer> queue=new PriorityQueue<>((a,b)-> -(a-b));
        for (int i:input){
            if (queue.size()<k){
                queue.offer(i);
            }else if (queue.size()==k&&i<queue.peek()){
                queue.poll();
                queue.offer(i);
            }
        }
        ArrayList<Integer> arrayList=new ArrayList<>();
        for (Integer integer : queue) {
            arrayList.add(integer);
        }
        return arrayList;

    }

    public static void main(String[] args) {
        int[] array=new int[]{4,5,1,6,2,7,3,8};
        System.out.println(new TopK().GetLeastNumbers_Solution(array,0));
    }
}
