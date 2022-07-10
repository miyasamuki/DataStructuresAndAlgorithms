package com.环形队列;

import javax.xml.bind.Element;

public class CircleQueue<E> {
    private int front;//记录对头的
    private int size;
    private E[] elements;
    private static final int DEFAULT_CAPACITY=10;
    public CircleQueue(){
        //给循环队列分配内存
        elements=(E[])new Object[DEFAULT_CAPACITY];
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public void enQueue(E element) {
        //入队
        ensureCacity(size+1);

            elements[(front+size)%elements.length]=element;
            size++;
        //入队从队尾入队

    }
    public void ensureCacity(int capacity){
       int oldCapacity=elements.length;
       if(capacity<=oldCapacity) return;
       int newCapacity=oldCapacity+(oldCapacity>>1);
       E[] newElements=(E[])new Object[newCapacity];
        int i=front;

        for (int j = 0; j < elements.length; j++) {
            newElements[j]=elements[(front+j)%elements.length];

        }
        elements=newElements;
        front=0;
    }
    public E deQueue(){
       //出队，从队头开始出队
        E frontElement=elements[front];
        elements[front]=null;
        front=(front+1)%elements.length;
        size--;
        return frontElement;

    }
    public E front(){
        return elements[front];
    }
}
