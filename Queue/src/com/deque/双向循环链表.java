package com.deque;

public class 双向循环链表<E> implements List<E>{
    private int size;
    private Node<E> first;
    private Node<E> last;
    /*
    为了解决约瑟夫问题
   增加一个节点
     */
    public int size(){
        return size;
    }
    private Node<E> current;
    //增加一个方法,让current指向头节点first
    public void reset(){
        current=first;
    }
    //让current往后走一步//也就是current=current.next;
    public E next(){
        if(current==null) return null;
        current=current.next;
        return current.element;
    }
    //删除current指向的节点，删除成功之后让current指向下一个节点
    public E remove(Node<E> current){
        if(current==null) return null;
        if(size==1){
            first=null;
            last=null;
        }
        Node<E> prev= current.pre;
        Node<E> next=current.next;
        prev.next=next;
        next.pre=prev;
        if(current==first){

          next=first;

        }
            if(current==last) {
                prev= last;
            }
            size--;
            return current.element;


    }
    //双向循环链表之添加
    public void add(int index,E element){

        if(index==size){
            //index=size,表示实际上添加的是第size-1个元素
            Node<E> oldlast=last;
            last=new Node<E>(element,oldlast,first);

            if(oldlast==null){
                first=last;
                first.next=first;
                first.pre=first;
            }else{
                first.pre=last;
                oldlast.next=last;
            }
        }else{

            Node<E> next=node(index);
            Node<E> prev=next.pre;
            Node<E> newNode=new Node<E>(element,prev,next);
            next.pre=newNode;
            prev.next=newNode;
            if(next==first){//index==0
                first=newNode;
            }
        }

        size++;


    }
    public boolean isEmpty(){
        if(size==0){
            return true;
        }else{
            return false;
        }
    }
    public E remove(){
        if(current==null) return null;
        Node<E> next=current.next;
               E element =remove(current);
                if(size==0){
                    current=null;
                }else {
                    current = next;
                }
                return element;
                //返回被删的元素，并且指向被删元素的下一个
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
    //ize大小的链表，最后一个元素是node(size-1）
    public Node<E> node(int index){
        Node<E> node=null;
        if(index<(size>>1)){
            node=first;
            for (int i = 0; i < index; i++) {
                node= node.next;
            }
            return node;

        }else{
            node=last;
            for(int i=size-1;i>index;i--){
                node=node.pre;
            }
            return node;
        }
    }
    public E remove(int index){
        Node<E> node=node(index);
        Node<E> prev=node.pre;
        Node<E> next=node.next;
        if(size==1){
            first=null;
            last=null;
        }else {
            if (prev == node(size)) {

                next.pre = node(size);
                node(size).next = next;
                first=next;
            } else if (next == first) {
                prev.next = first;
                first.pre = prev;
                last=prev;
            } else {
                prev.next = next;
                next.pre = prev;
            }
        }
       size--;
        return node.element;
    }
    public E getFirstElement(){
        return first.element;
    }
    public E getlastElement(){
        return last.element;
    }
}
