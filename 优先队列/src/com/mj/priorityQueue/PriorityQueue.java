package com.mj.priorityQueue;

import com.mj.domian.Person;
import com.mj.heap.BinaryHeap;
import com.mj.printer.BinaryTreeInfo;

import java.util.Comparator;

/**
 * @Author:ghq
 * @Date: 2022/04/02/21:23
 * @Description
 */
public class PriorityQueue<E> implements Queue {
    BinaryHeap<E> heap=null;

    public PriorityQueue(Comparator comparator) {
        heap=new BinaryHeap<>(comparator);
    }
    public PriorityQueue(){
        heap=new BinaryHeap<>();
    }


    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public void enQueue(Object element) {
           heap.add((E) element);
    }

    @Override
    public Object deQueue() {
        return heap.remove();
    }

    @Override
    public Object front() {
        return heap.get();
    }

    @Override
    public void clear() {
       heap.clear();
    }

}
