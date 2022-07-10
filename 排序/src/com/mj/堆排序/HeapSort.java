package com.mj.堆排序;

import com.mj.Sort;

/**
 * @Author:ghq
 * @Date: 2022/04/13/16:35
 * @Description
 */
public class HeapSort<E extends Comparable<E>> extends Sort<E> {
    private int heapSize;
    @Override
    protected void sort() {
        /**
         * 原地建堆
         */
        heapSize=array.length;
        //完全二叉树叶子节点占所有节点的一般
        for (int i = (heapSize>>1)-1; i>=0; i--) {
              siftDown(i);
        }
         while(heapSize>1){
             //交换堆顶和尾部的元素
             swap(0,heapSize-1);
             heapSize--;
              //对0位置shiftDown
             siftDown(0);
         } }
    private void siftDown(int index){
        //下滤的条件1.index这个位置要有子节点2.index这个位置的节点要比它的最大子节点小
        //1.首先我们要直到完全二叉树的特点就是叶子节点正好是全部节点的一半
        E element=array[index];
        int half=heapSize>>1;
        while(index<half){
            int child_index=2*index+1;
            E child=array[child_index];
            int right_index=2*index+2;
            if(right_index<heapSize&&compare(array[right_index],array[child_index])>0){
                child_index=right_index;
                child=array[right_index];
            }
            if(compare(element,child)>0){break;}
            array[index]=child;
            index=child_index;
        }
        array[index]=element;
    }
    private int compare(E e1,E e2){
        return ((Comparable<E>)e1).compareTo(e2);
    }
}
