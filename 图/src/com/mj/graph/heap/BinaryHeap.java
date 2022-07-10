package com.mj.graph.heap;

import javax.xml.bind.Element;
import java.util.Collection;
import java.util.Comparator;

/**
 * @Author:ghq
 * @Date: 2022/03/20/12:28
 * @Description
 */
public class BinaryHeap<E> extends AbstractHeap<E>{
    private static final int DEFAULT_CAPACITY=10;
    public BinaryHeap(Collection<E> elements, Comparator<E> comparator){
        //给你一个数组，将其排列成二叉堆，这个叫批量建堆
        super(comparator);
        size = elements == null ? 0 : elements.size();
        if(size==0){
            this.elements=(E[])new Object[DEFAULT_CAPACITY];
        }else{
            int capacity=Math.max(size,DEFAULT_CAPACITY);
            this.elements=(E[])new Object[capacity];
            int i=0;
            for (E element: elements) {
                this.elements[i++]=element; }

            heapify(); }
    }
    public BinaryHeap(Collection<E>  elements){
        //给你一个数组，将其排列成二叉堆，这个叫批量建堆
        this(elements,null);
    }//数组批量拷贝完成之后，可以进行批量建堆了
    private void heapify(){
        //自上而下的上滤
//        for (int i = 1; i <size ; i++) { siftUp(i);}
        //自下而上的下滤（从最后一个非叶子节点开始下滤）
        for (int i = (size>>1)-1; i >=0 ; i--) {
             siftDown(i); }
    }
    public BinaryHeap(Comparator<E> comparator){
        this(null,comparator);
    }

    public BinaryHeap() {
        this(null,null);

    }


/*
添加有两个问题：1.要扩容  2.要保证最大的在堆顶
 */

    @Override
    public void add(E element) {
        int index=size;
       elementNotNullCheck(element);
       ensureCapacity(size+1);
       elements[size++]=element;
       //先默认这是一个大顶堆
        siftUp(index);
    }
    private void siftUp(int index){
        //首先index>0才能有父节点
        E temp = elements[index];
        while(index>0) {
            int pindex=(index-1)>>1;
            if(compare(elements[pindex],temp)>0){
            break; }
            //交换index,pindex的内容
            elements[index] = elements[pindex];
            index=pindex; }
        elements[index]=temp;
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    @Override
    public E remove() {
        //使得最后一个元素和第一个元素互换，然后删掉最后一个元素
        emptyCheck();
        int lastIndex=--size;
        E root=elements[0];
        elements[0]=elements[lastIndex];
        elements[lastIndex]=null;
        siftDown(0);
        return root;
    }
    private void siftDown(int index){
        //下滤的条件1.index这个位置要有子节点2.index这个位置的节点要比它的最大子节点小
        //1.首先我们要直到完全二叉树的特点就是叶子节点正好是全部节点的一半
        E element=elements[index];
        int half=size>>1;
        while(index<half){
            int child_index=2*index+1;
            E child=elements[child_index];
            int right_index=2*index+2;
            if(right_index<size&&compare(elements[right_index],elements[child_index])>0){
                child_index=right_index;
                child=elements[right_index];
            }
            if(compare(element,child)>0){break;}
            elements[index]=child;
            index=child_index;
        }
        elements[index]=element;
    }
//replace方法就是删除顶部元素，然后替换未element
    @Override
    public E replace(E element) {
        //将element替换掉堆顶元素，然后完成一次下滤操作
       elementNotNullCheck(element);
       E root=null;
       if(size==0){
           elements[0]=element;
           size++;
       }else{
           root=elements[0];
           elements[0]=element;
           siftDown(0);
       }
       return root;
    }



    public Object root() {
        return 0;
    }


    public Object left(Object node) {
        Integer index=(Integer) node;
        index=(index<<1)+1;
        return index>=size?null:index;
    }

    public Object right(Object node) {
        Integer index=(Integer) node;
        index=(index<<1)+2;
        return index>=size?null:index;
    }

    public Object string(Object node) {
        Integer index=(Integer) node;
        return elements[index];
    }
}
