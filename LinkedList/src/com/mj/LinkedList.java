package com.mj;


public class LinkedList<E> {
    class Node<E>{
        E element;
        Node<E> next;
        public Node(E element,Node<E> next){
            this.element=element;
            this.next=next;
        }
    }
    private int size;
    private Node<E> first;
    public void clear(){
        int size=0;
        first=null;
    }
    private void rangeCheck(int index){
        if(index<0||index>size){
            throw new IndexOutOfBoundsException();
        }

    }
    //链表居然也有索引
    //返回索引为index的Node
    private Node<E> node(int index){
        rangeCheck(index);//检查索引是不是越界
        Node<E> node=first;
        for(int i=0;i<index;i++){
            node=node.next;
        }
        return node;
    }
    public E set(int index,E element){
        Node<E> node=node(index);
        E old=node.element;
        node.element=element;
        return old;
    }
    //往链表中添加索引
    public void add(E element,int index){
       if(index==0){
           first=new Node<>(element,first);
       }
        Node preNode=node(index-1);
        Node nextNode=node(index);
        Node<E> newNode=new Node(element,nextNode);
        preNode.next=newNode;


    }

}
