package com.mj.归并排序;


import com.mj.Sort;

/**
 * @Author:ghq
 * @Date: 2022/04/17/22:20
 * @Description
 */
public class MergeSort<E extends Comparable<E>> extends Sort<E> {
    private E[] leftArray;//空间复杂度n/2

    @Override
    protected void sort() {
        leftArray=(E[])new Comparable[array.length>>1];
        sort(0,array.length);
    }
    private void sort(int begin,int end){
        //由于begin和end是前闭后开的，所以begin=0.end=2,表示[0][1]这两个元素
        if(end-begin<2){return;}
        int mid=(end+begin)>>1;
        sort(begin,mid);

        sort(mid,end);
        merge(begin,mid,end);

    }
    private void merge(int begin,int mid,int end){
       int li=0,le=mid-begin;
       int ai=begin;
       int ri=mid,re=end;
        for (int i = li; i <le ; i++) {
            leftArray[i]=array[begin++];
        }
       while(li<le){
           if(ri<re&&cmp(array[ri],leftArray[li])<0){
               array[ai++]=array[ri++];
           }else{
               array[ai++]=leftArray[li++];
           }
       }
    }
}
