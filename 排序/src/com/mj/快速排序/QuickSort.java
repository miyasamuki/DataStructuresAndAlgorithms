package com.mj.快速排序;

import com.mj.Sort;

/**
 * @Author:ghq
 * @Date: 2022/04/18/22:51
 * @Description
 */
public class QuickSort<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
       sort(0,array.length);
    }
    private void sort(int begin,int end){
        if(end-begin<2) {return;}
        int mid=pivotIndex(begin,end);
        sort(begin,mid);
        sort(mid+1,end);

    }
    //确定[begin,end)范围内轴点的位置
    private int pivotIndex(int begin,int end){
        end=end-1;
        E pivot=array[begin];
        while(end>begin){
            while (end>begin) {
                if (cmp(array[end], pivot) > 0) {
                    end--;
                } else {
                    array[begin] = array[end];
                    begin++;
                    break;
                }
            }
            while(end>begin) {
                if (cmp(array[begin],pivot)<0){
                    begin++;
                }else{
                    array[end]=array[begin];
                    end--;
                    break;
                }
            }
        }
        array[end]=pivot;
        return begin;

    }
}
