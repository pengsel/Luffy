package com.pengsel.leetcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ReconstructQuene {

    /**
     * 先按照身高排序，从小到大
     * 再按照前面人个数排序，从大到小
     *
     * people中的元素从后往前，
     * 如果是最高的，arrayList直接add，因为上面已经排序了，前面人少的在后面
     * 如果不是，people[i][1]他前面的人数，即他的位置
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people,
                (o1,o2) ->{
                    if(o1[0]==o2[0])
                        return o2[1]-o1[1];
                    return o1[0]-o2[0];
                }
        );
        ArrayList<int[]> arrayList=new ArrayList<>();
        for (int i=people.length-1;i>=0;i--){
            if (people[i][0]==people[people.length-1][0])arrayList.add(people[i]);
            arrayList.add(people[i][1],people[i]);
        }
        int[][] ret=new int[people.length][2];
        for (int i=0;i<people.length;i++)
            ret[i]=arrayList.get(i);
        return ret;

    }

    public static void main(String[] args) {
        int[][] people={{1,2},{0,3}};
        new ReconstructQuene().reconstructQueue(people);
    }
}
