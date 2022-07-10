package com.mj.cn.linkedHashMap;

import sun.awt.image.ImageWatched;

/**
 * @Author:ghq
 * @Date: 2022/01/29/10:37
 * @Description
 */
public class LinkedHashMap<K,V> extends HashMap<K,V> {
    private LinkedNode<K,V> first;
    private LinkedNode<K,V> last;
    @Override
    protected Node<K,V> createNode(K key, V value, Node<K,V> parent){
       LinkedNode node=new LinkedNode(key,value,parent);
      if(first==null){
          first=last=node;
      }else{
         last.next=node;
         node.prev=last;
         last=node;
      }
      return node;
    }
    private static class LinkedNode<K,V> extends Node<K,V>{
        LinkedNode<K,V> prev;
        LinkedNode<K,V> next;

        public LinkedNode(K key, V value, Node<K, V> parent) {
            super(key, value, parent);
        }
    }
    public void travelsal(Visitor<K,V> vistor){
        if(vistor==null) {
            return;
        }
        LinkedNode<K,V> node=first;
        while(node!=null){
            if(vistor.visit(node.key, node.value)){return;}
            node=node.next;
        }

    }
    @Override
    public void clear() {
        super.clear();
        first=null;
        last=null;
    }
    @Override
    protected V remove(Node<K,V> node){
        if(node==null) {
            return null;
        }
        LinkedNode<K,V> linkedNode=(LinkedNode<K, V>) node;
        LinkedNode<K,V> prev=linkedNode.prev;
        LinkedNode<K,V> next=linkedNode.next;
        if(prev==null){
            first=next;
        }else{
            prev.next=next;
        }if(next==null){
            prev=last;
        }else{
            next.prev=prev;
        }
        return super.remove(node);
    }
}
