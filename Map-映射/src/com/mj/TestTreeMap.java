package com.mj;

import com.mj.binarySearchTree.Comparator;
import com.mj.binarySearchTree.RBTree;

import java.nio.file.Files;

public class TestTreeMap {
    static void test1(){
       TreeMap<Integer,String> treeMap= new TreeMap<>(new Comparator<Integer>() {
           @Override
           public int compare(Integer e1, Integer e2) {
               return e1-e2;
           }
       });
       treeMap.put(1,"小红");
       treeMap.put(2,"小明");
       treeMap.put(3,"小强");
       treeMap.traversal(new Map.Visitor<Integer, String>() {

           @Override
           public boolean visit(Integer key,String value) {
               System.out.println("key:"+key+","+"value:"+value);
               return false;
           }
       });
    }

    /**
     * 使用map统计代码行数
     */
    static void test2(){
        TreeMap<String,Integer> treeMap= new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String e1, String e2) {

                return Integer.valueOf(e1)-Integer.valueOf(e2);

            }
        });
       // Files.read("D:\\学习\\day0-20\\代码\\10-映射\\src\\com\\mj\\map",new String[]{"java"});
        treeMap.traversal(new Map.Visitor<String, Integer>() {

            @Override
            public boolean visit(String key,Integer value) {
                System.out.println("key:"+key+","+"value:"+value);
                return false;
            }
        });
    }

    public static void main(String[] args) {
        test1();
    }
}
