package com.mj;

public abstract class AbstractList<E> implements List<E> {
    protected int size;
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
}
