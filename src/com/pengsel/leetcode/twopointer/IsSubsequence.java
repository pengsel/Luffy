package com.pengsel.leetcode.twopointer;

public class IsSubsequence {

    public boolean isSubsequence(String s, String t) {
        if(s.length()==0) return true;
        if(s.length()>t.length()) return false;

        int is=0;
        for (int i=0;i<t.length();i++){
            if (s.charAt(is)==t.charAt(i)){
                is++;
                if (is==s.length()) return true;
            }
        }

        return false;
    }
}
