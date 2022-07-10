package com.mj;

import com.mj.heap.BinaryHeap;
import com.mj.printer.BinaryTreeInfo;
import com.mj.printer.BinaryTrees;

import java.util.Comparator;

/**
 * @Author:ghq
 * @Date: 2022/03/20/12:22
 * @Description
 */
public class Main {


    static void test() {
        BinaryHeap<Integer> heap=new BinaryHeap<>();
        heap.add(68);
        heap.add(72);
        heap.add(43);
        heap.add(50);
        heap.add(38);
        heap.add(10);
        heap.add(90);
        heap.add(65);
        BinaryTrees.println(heap);

    }

    public static void main(String[] args){
        testTopK();
    }
//在很多数据中找出前五个最大的数据
    static void testTopK() {
        int k=5;
        Integer[] bigData={51,30,39,92,74,25,16,93,91,19,54,47,73,62,76,63,35,18,90,6,65,49,3,26,61,21,48};
        BinaryHeap<Integer> heap=new BinaryHeap<>(null, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
               return o2-o1;
            }//小顶堆是01-02，大顶堆是o2-o1
        });
        for (int i = 0; i < bigData.length; i++) {
            if(i<5){
                heap.add(bigData[i]);
            }else if(heap.get()<bigData[i]){
                heap.replace(bigData[i]);
            }

        }
        BinaryTrees.println(heap);
    }
}
