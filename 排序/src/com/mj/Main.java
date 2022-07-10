package com.mj;

import com.mj.基数排序.RedixSort;
import com.mj.堆排序.HeapSort;
import com.mj.希尔排序.ShellSort;
import com.mj.归并排序.MergeSort;
import com.mj.快速排序.QuickSort;
import com.mj.插入排序.InsertSort;
import com.mj.计数排序.CountingSort;

import java.util.Arrays;

/**
 * @Author:ghq
 * @Date: 2022/04/13/21:47
 * @Description
 */
public class Main {
    public static void main(String[] args) {
        Integer[] array1=new Integer[]{26, 15, 23, 38, 48, 71, 79, 55, 81, 76,12,32,55,33,12,34};
        new RedixSort().sort(array1);
       System.out.println(Arrays.toString(array1));
    }
}
