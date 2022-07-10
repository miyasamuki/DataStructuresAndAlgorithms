package com.deque;
//双端队列
public class Deque<E> extends 双向循环链表<E>{
    List<E> list=new 双向循环链表<E>();
//    int size(){
//        return list.size();
//    }
    boolean Empty(){
       return list.isEmpty();
    }
    void enQueueRear(E element){
        //从队尾入队
        list.add(list.size(),element);
    }
    void enQueueFront(E element){
        //从队头入队
        list.add(0,element);
    }
    E deQueueRear(){
        //从队尾出队
        return list.remove(list.size()-1);
    }
    E deQueueFront(){
        //从队头出队
        return list.remove(0);
    }
    E front(){
        return list.getFirstElement();
    }
    E rear(){
        return list.getlastElement();
    }
}
