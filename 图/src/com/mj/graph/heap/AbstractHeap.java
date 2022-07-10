package com.mj.graph.heap;

import java.util.Comparator;

/**
 * @Author:ghq
 * @Date: 2022/03/21/0:14
 * @Description
 */
public abstract class AbstractHeap<E> implements Heap<E>{

    protected Comparator<E> comparator;
    protected static final int DEFAULT_CAPACITY=10;
    protected E[] elements;
    protected int size;
    public AbstractHeap(Comparator<E> comparator){
        this.comparator=comparator;
        this.elements=(E[])new Object[DEFAULT_CAPACITY];
    }
    public AbstractHeap(){
        this(null);
    }
    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {
        size=0;
    }
    /*
    添加有两个问题：1.要扩容  2.要保证最大的在堆顶
     */
    protected void ensureCapacity(int capacity){
        int oldCapcacity=elements.length;
        if(oldCapcacity>=capacity) {return;}
        int newCapacity=oldCapcacity+(oldCapcacity>>1);
        E[] newElements=(E[])new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i]= elements[i];

        }
        elements=newElements;
        System.out.println(oldCapcacity+"扩容为"+newCapacity);
    }
    protected int compare(E e1,E e2){
        return comparator!=null?comparator.compare(e1,e2):((Comparable<E>)e1).compareTo(e2);
    }
    protected void emptyCheck(){
        if(size==0){
            throw new IndexOutOfBoundsException("heap is empty");
        }
    }
    protected void elementNotNullCheck(E element){
        if(element==null){
            throw new IllegalArgumentException("Heap is empty");
        }
    }
}
