package com.mj;


import com.sun.org.apache.xml.internal.security.c14n.helper.AttrCompare;

import java.util.HashMap;

/**
 * @Author:ghq
 * @Date: 2022/04/05/16:24
 * @Description
 */
public class Trie<V> {
    private int size;
    private Node<V> root = new Node<V>(null);


    private class Node<V> {
        //每个Node都有一个map,里面记录着这个Character和下一个node
        // private Map<Character,Node<V>> map;
        Node<V> parent;
        HashMap<Character, Node<V>> children;//children.get(Character)可以获得对应的子节点
        V value;
        Character character;
        boolean word;//word为true表示是单词的结尾

        public Node(Node<V> parent) {
            this.parent = parent;
        }

        public HashMap<Character, Node<V>> getChildren() {
            return children == null ? (children = new HashMap<>()) : children;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        root = null;
    }

    public V get(String key) {
        Node<V> node = node(key);
        return node == null ? null : node.value;
    }

    //首先这个方法是看这个String到底存不存在
    public Node<V> node(String key) {
        keyCheck(key);
        if (root == null) {
            return null;
        }
        Node<V> node = root;

        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (node.children.get(c) == null) {

                break;
            }
            node = node.children.get(c);

        }
        if (node != null && node.word == true) {
            //说明key这个String是存在的，
            //返回最后一个node
            return node;
        } else {
            return null;
        }

    }

    private void keyCheck(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("key must not null");
        }
    }

    public boolean contains(String str) {
        return node(str) != null;
    }

    V add(String str, V value) {
        keyCheck(str);

        Node<V> node = root;
        for (int i = 0; i < str.length(); i++) {
            //node(i)方法是判断字符串是否存在的，完全没有必要判断node是否存在
            char c = str.charAt(i);
            Node<V> childNode = node.getChildren().get(c);
            if (childNode == null) {
                childNode = new Node<V>(node);
                childNode.character = c;
                node.getChildren().put(c, childNode);
            }
            node = childNode;
        }
        if (node.word) {
            V oldValue = node.value;
            node.value = value;
            return oldValue;
        }

        node.word = true;
        node.value = value;
        size++;
        return null;


    }

    /**
     * remove分三种情况
     * 1.这个不是一个单词，不能删
     * 2.单词是存在的，并且最后一个字符不是子节点，直接把最后一个字符的word变成false
     * 3.单词是存在的，并且最后一个字符是子节点，（要依次删除，不停的判断每一个节点是不是子节点）
     */

//    V remove(String key) {
//        Node<V> node =node(key);
//        if(node==null||!node.word) {return null;}
//        size--;
//        V oldValue=node.value;
//        //如果还有子节点
//        if(node.children!=null&&!node.children.isEmpty()){
//
//            node.word=false;
//            node.value=null;
//            return oldValue;
//        }
//        //如果没有子节点,要删掉该节点对应的character
//        Node<V> parent=null;
//        while((parent=node.parent)!=null){
//            parent.children.remove(node.character);
//            if(!parent.children.isEmpty()) {break;}
//            node=parent;
//        }
//        return null;
//
//    }
    V remove(String key) {
        keyCheck(key);
       Node<V> node=node(key);
       //如果找不到这个单词或者这个单词不是一个完整的单词就返回null
       if(node==null||node.word!=true){return null;}
       size--;
       V oldValue=node.value;
       //如果找到这个单词了，但是这个单词的后面还有子节点
        if(node.children!=null&&!node.children.isEmpty()){
            node.word=false;
            return oldValue;
        }
        //是子节点，删掉子节点，依次判断父节点是不是子节点
        Node<V> parent=null;
        while((parent=node.parent)!=null){

            parent.children.remove(node.character);
            if(parent.word||!parent.children.isEmpty()){break;}
            node=parent;

        }
        return null;

    }

    public boolean starsWith(String prifix) {
        Node<V> node = root;
        for (int i = 0; i < prifix.length(); i++) {
            char c = prifix.charAt(i);
            Node<V> childNode = node.getChildren().get(c);
            if (childNode == null) {
                return false;
            }
            node = childNode;
        }
        return true;
    }
}
