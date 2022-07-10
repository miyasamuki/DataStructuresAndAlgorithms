package com.mj.计数排序;
import com.mj.Sort;
import java.lang.Integer;
import java.util.*;

/**
 * @Author:ghq
 * @Date: 2022/04/20/12:38
 * @Description
 */
public class CountingSort extends Sort<Integer> {
    int[] counts;
    int[] newArray;
    @Override
    protected void sort() {
        int max=0;
        int min=0;
        for (int i = 0; i < array.length; i++) {
            if(array[i]>max){ max=array[i]; }
            if(array[i]<min){ min=array[i]; }
        }
        counts=new int[max-min+1];
        for (int i = 0; i < array.length; i++) {
            counts[array[i]-min]++;
        }
        for (int i = 1; i < counts.length; i++) {
            counts[i]+= counts[i-1];
        }

        newArray=new int[array.length];
        for (int i = array.length-1; i >=0 ; i--) {
            //如果这个i在array-min这个数组里有，
            //那么久按照count[i]的大小给它放位置
            //但是如果换个思路，直接遍历array呢
            //array[0] 等于count[m]=1的那个m
            newArray[--counts[array[i]-min]]=array[i];

        }
        for (int i = 0; i < array.length; i++) {
            array[i]=newArray[i];
        }
    }

}
