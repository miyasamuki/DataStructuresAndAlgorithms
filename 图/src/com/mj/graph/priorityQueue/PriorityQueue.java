package com.mj.graph.priorityQueue;

import com.mj.graph.heap.BinaryHeap;

import java.net.InterfaceAddress;
import java.util.Collection;
import java.util.Comparator;

/**
 * @Author:ghq
 * @Date: 2022/04/02/21:23
 * @Description
 */
public class PriorityQueue<E> implements Queue {


    BinaryHeap<E> heap=null;

    public PriorityQueue(Collection<E> elements, Comparator comparator) {

        heap= new BinaryHeap(elements,comparator);
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
