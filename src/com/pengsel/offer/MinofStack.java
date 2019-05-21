package com.pengsel.offer;

import java.util.Stack;

public class MinofStack {
    Stack<Integer> st=new Stack<Integer>();
    Stack<Integer> stMin=new Stack<Integer>();
    public void push(int node) {
        if(st.size()==0){
            st.push(node);
            stMin.push(node);
        }
        else{
            st.push(node);
            stMin.push(Math.min(node,stMin.peek()));
        }
    }

    public void pop() {
        st.pop();
        stMin.pop();
    }

    public int top() {
        return st.peek();
    }

    public int min() {
        return stMin.peek();
    }
}
