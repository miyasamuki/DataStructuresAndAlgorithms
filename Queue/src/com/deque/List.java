package com.deque;

public interface List <E>{
    void add(int index,E element);
    boolean isEmpty();
    int size();
    E getFirstElement();
    E getlastElement();

    E remove(int index);
}
