package com.mj;

public interface Set<E> {
    int size();
    boolean isEmpty();
    void clear();
    boolean contains(E element);
    void add(E element);
    void remove(E element);
    void traversal(Visitor<E> vistor);//vistor是遍历方式
        public static abstract class Visitor<E>{
        boolean stop;
        public abstract boolean visit(E element);
        }
}
