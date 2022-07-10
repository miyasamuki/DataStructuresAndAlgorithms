package com.mj.选择排序;

import com.mj.Sort;

import java.util.Arrays;

/**
 * @Author:ghq
 * @Date: 2022/04/10/20:00
 * @Description
 */
public class SelectSort<E extends Comparable<E>> extends Sort<E> {
    public static void main(String[] args) {
        int[] array=new int[10];
        for (int i = 0; i < array.length; i++) {
              array[i]=(int)(Math.random()*100);
        }
        System.out.println("排序前："+ Arrays.toString(array));

    }

    /**
     * 由于选择排序交换的次数要远远少于冒泡排序，平均性能要优于冒泡排序
     * 最好，最坏，平均时间复杂度O(n^2),空间复杂度O(1),属于稳定排序
     * 选择排序还能优化吗？
     * 使用堆排序，一旦涉及堆的操作使用堆排序可以大大优化性能
     * @param
     * @return
     */

    @Override
    protected void sort() {
        for(int end=array.length;end>1;end--) {
            int max=0;
            int max_i=0;
            for (int i = 0; i < end; i++) {
                if (cmp(i,max_i)>0) {
                    max = (Integer)array[i];
                    max_i=i;
                }
            }
            swap(max_i,end-1);
        }

    }
}
