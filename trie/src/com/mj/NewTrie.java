package com.mj;

import com.sun.corba.se.spi.orbutil.proxy.CompositeInvocationHandlerImpl;

import java.util.HashMap;

/**
 * @Author:ghq
 * @Date: 2022/04/30/12:01
 * @Description
 */
public class NewTrie<V> {
    /**
     * 这里想写一个不带value的trie
     * 1.首先这个节点里应该有个map,让我可以通过里面村的character,找到下一个子节点
     * hashMap<character,Node<V></>
     * 2.先要遍历trie,就必须要用根节点，根节点是parent=null的节点，因此parent在Node中必须要有
     * 3.
     */
    private Node<V> root=new Node<V>(null);
    private int size;
    private class Node<V>{
       Node<V> parent;
       HashMap<Character,Node<V>> childeren;//
        boolean word;
        V value;//value可能就是相当于这个词的索引，但是好像用不到

        public HashMap<Character, Node<V>> getChilderen() {
            return childeren==null?(childeren=new HashMap<>()):childeren;
        }

        public Node(Node<V> parent) {
            this.parent = parent;
        }
    }
    public int size(){
        return size;
    }
    //然后应该有添加功能
    public V add(String str,V value){
        Node<V> node=root;
        for (int i = 0; i < str.length(); i++) {
             char c=str.charAt(i);
             Node<V> childrenNode=node.getChilderen().get(c);
             if(childrenNode==null){
                 childrenNode=new Node<>(node);
                 node.getChilderen().put(c,childrenNode);
             }
             node=childrenNode;
        }
        if(node.word){//如果本身就是存在的单词
            V oldValue=node.value;
            value=node.value;
            return oldValue;
        }
        node.word = true;
        node.value = value;
        size++;
        return null;
    }
    private void keyCheck(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("key must not null");
        }
    }
    V remove(String key) {
        return null;
    }
    Node<V> node(String key){
        keyCheck(key);
        Node<V> node=root;
        for (int i = 0; i < key.length(); i++) {
            char c=key.charAt(i);
            Node<V> childnode=node.getChilderen().get(c);
            if(childnode==null){
                return null;
            }
            node=childnode;
        }
        return node;
    }
    V get(String key){
        Node<V> node=node(key);
        return node.value;
    }
    public boolean starsWith(String str){
        keyCheck(str);
        Node<V> node=root;
        for (int i = 0; i < str.length(); i++) {
            char character=str.charAt(i);
            Node<V> childnode=node.getChilderen().get(character);
            if(childnode==null){
                return false;
            }
            node=childnode;
        }
        return true;
    }
}
