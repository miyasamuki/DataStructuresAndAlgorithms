package com.mj;

import javax.xml.bind.Element;

public class DoubleLinkedList<E> implements List<E>{
    private int size;
    private Node<E> first;
    private Node<E> last;

    @Override
    public void add(E i) {

    }

    private class Node<E>{
        E element;
        Node pre;
        Node next;
        public Node(E element, Node pre, Node next) {
            this.element = element;
            this.pre = pre;
            this.next = next;
        }

    }
    public Node<E> node(int index){
        Node<E> temp=first;
        for(int i=1;i<index;i++){
            temp=temp.next;
        }
        return temp;
    }
    public void add(int index,E element){
        //当index=0的时候，next.pre是空
        Node<E> next=node(index);//node函数怎么写呢
        if(index==0){
            Node<E> pre=null;
            Node<E> node=new Node<E>(element,null,next);
            first=node;///注意啊这个地方表头是size,first,last;first是个指向下一个的指针，所以直接first=node就可以了
            next.pre=node;

        }
        else if(index==size){
            Node<E> oldlast=last;
            Node<E> node=new Node<E>(element,oldlast,null);//它的上一个是之前那个last
          //  pre.next=node;
            last=node;//新的last
            if(oldlast==null){
                first=last;

            }else {
                oldlast.next = node;//还有一种oldlast==null
            }

        }
        else{
            Node<E> pre = next.pre;
            Node<E> node = new Node<E>(element, pre, next);//这个函数是自己的线已经接好了，该接对方的线了
            next.pre = node;
            pre.next = node;
        }
     size++;
    }

    public E remove(int index){
        //分三种清空1.index=0;

        Node<E> node=node(index);
        Node<E> pre=node.pre;
        Node<E> next=node.next;
        if(pre==null){
            first=next;
           next.pre=pre;
        }else if(next==null){
            last=pre;
            pre.next=next;

        }else {

            pre.next=next;//没有人指向的节点会自动被删除，很神奇
            next.pre=pre;

        }
        size--;
        return node.element;
    }
    public void clear(){
        size=0;
        first=null;
        last=null;//由于Linkedlist是栈指针，所以被栈指针指向的变量不会被回收，
        //清除的时候只需要断掉两条栈指针，中间的一些被栈指针间接指向的对象自然不再被栈指针指向，因此被回收
        //因为在java中如何你的对象不被gc root对象引用，就会自然被回收
    }
    //gc root 对象：1）被栈指针指向的对象（如：局部变量指向的对象）
    public static void main(String[] args) {
        List<Integer> Linkedlist= new DoubleLinkedList<>();//Linkedlist是局部变量，所以Linkedlist是栈指针
        for(int i=0;i<50;i++){
            Linkedlist.add(i);
        }


    }
    static void testList(List<Integer> list){


    }


}
