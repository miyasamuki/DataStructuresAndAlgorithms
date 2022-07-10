package com.mj.graph.priorityQueue;

public interface Queue<E>{
    int size();//元素的数量
    boolean isEmpty();//是否为空
    void enQueue(E element);//入队。
    E deQueue();//出队，出优先级最高的元素
    E front();//获取队列得头元素
    void clear();//清空

}
