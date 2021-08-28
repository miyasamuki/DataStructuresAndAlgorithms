package com.mj;

import java.util.ArrayList;
import java.util.List;

/*

 */
public class Stack <E>  {
    //为什么栈不能用下标来取值,所以直接用继承的方式不合适，应该用组合的方式
    //如果栈用链表实现的话，不久可以用下标来取值了么
    //想知道ArrayList里面那个新开的数组是干什么用的
    private List<E> list=new ArrayList<>();
    public int size(){
        return 0;
    }
    public boolean isEmpty(){
        return false;
    }
    public void push(E element){

    }
    public E pop(){
        return null;
    }
    public E top(){
        return null;
    }
}
