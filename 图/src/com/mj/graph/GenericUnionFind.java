package com.mj.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author:ghq
 * @Date: 2022/04/27/23:22
 * @Description
 */
public class GenericUnionFind<V>
{/**
 通用类型的并查集，主要有两点，
 1.将parent[]变为node,以前是parent的索引是集合中的某对象，parent的值是父节点对象
   现在是node的v是集合中的某对象，node中的parents是父节点对象
 2.hashmap提供v和v的父节点之间的中映射关系
 3.另外之前的初始化是直接初始化parents数组，现在的初始化是将V放入hashmap
 */
    //基于UnionFind_QU的优化
     Map<V,Node> map=new HashMap<>();
        private static class Node<V>{

            V value;
            int rank=1;//表示树的初始高度为1
            Node<V> parents=this;

            public Node(V value) {
                this.value = value;
            }
        }
         //进行初始化
    public void mapset(V v){

            Node<V> node=new Node<>(v);
            if(map.containsKey(v)){return;}
            map.put(v,node);
    }

    protected boolean isSame(V v,V v2){
        find(v);
        find(v2);
        return Objects.equals(find(v),find(v2));
    }

   //UF的查找是找根节点
    public V find(V v) {
            //首先你要通过v找到根节点findNode
        //只有根节点的父节点是它自己，这个不难想把
        Node<V> node=findNode(v);
        if(node==null) {return null;}
        return node.value;


       }
 public Node<V> findNode(V value){
            Node<V> node=map.get(value);
            if(node==null) {return null;}
            while(!Objects.equals(node.value,node.parents.value)){
                node=node.parents.parents;

            }
            return node;
 }
    public void union(V v1, V v2) {
        Node<V> node1=findNode(v1);
        Node<V> node2=findNode(v2);
        if(node1==null||node2==null) {return;}
        if(Objects.equals(node1.value,node2.value)){return;}
        if (node1.rank>node2.rank) {
           node2.parents=node1;
        }else if(node1.rank<node2.rank){
            node1.parents=node2;
        }else{
            node2.parents=node1;
            node1.rank++;
        }
    }
}
