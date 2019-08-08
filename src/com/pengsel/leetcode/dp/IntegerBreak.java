package com.pengsel.leetcode.dp;

/**
 * 343. Integer Break (Medim)
 *
 * 按照微积分求导,如果不要求整数,应该将其分成e,但是因为整数,所以尽量将其分成3或者2(3优先),
 * 但是有特例3*1<2*2,所以将n%3=1时要将其分成2+2
 */
public class IntegerBreak {

    int integerBreak(int n){

        if(n==2)return 1;
        if(n==3) return 2;

        if (n%3==0){
            return (int)Math.pow(3,n/3);
        }
        if (n%3==1){
            return (int)Math.pow(3,n/3-1)*4;
        }

        if (n%3==2){
            return (int)Math.pow(3,n/3)*2;
        }
        return 0;
    }
}
