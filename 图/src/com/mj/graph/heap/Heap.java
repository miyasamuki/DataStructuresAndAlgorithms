package com.mj.graph.heap;


/**
 * @Author:ghq
 * @Date: 2022/03/20/12:23
 * @Description
 */
public interface Heap<E> {
    int size();//元素的数量
    boolean isEmpty();//是否为空
    void clear();//清空
    void add(E element);//添加元素
    E get();//获得堆顶元素
    E remove();//删除堆定元素
    E replace(E element);//移除堆顶元素的同时，加入一个新的元素
}
