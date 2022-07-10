package com.mj.插入排序;

import com.mj.Sort;

/**
 * @Author:ghq
 * @Date: 2022/04/15/15:30
 * @Description
 */
public class InsertSort<E extends Comparable<E>> extends Sort<E> {
    /**
     * 插入排序，
     * 插入排序的优化1：将插入改为挪动
     */
//    @Override
//    protected void sort() {
//        for(int begin=1;begin< array.length;begin++){
//            int cur=begin;
//            if(cmp(array[begin-1],array[begin])>0) {
//                swap(begin, begin - 1);
//                cur--;
//            }
//        }
//
//    }

    /**
     * 使用二分搜索法来重写插入算法
     * 首先这个方法有两个问题
     * 1.当=mid的时候，算法不稳定，先插入的反而可以会被放在后面
     */
//indexOf(i)返回i位置的这个元素应该在的位置；
    protected int indexOf(int i) {
            E temp=array[i];
            int end=i;
            int begin=0;
            int mid=0;
            //begin与end,前包后不包
            //我们要找的是第一个大于这个值的位置
            while(begin<end) {
                mid=(begin+end)>>1;
                if(cmp(array[mid],temp)>0 ) {
                     end=mid;//小于去左边
                }else if(cmp(array[mid],temp)<0||cmp(array[mid],temp)==0){
                    begin=mid+1;//大于等于去右边
                }
            }
          return begin;
    }
    @Override
    protected void sort() {
        for(int i=1;i< array.length;i++){
            insert(i,indexOf(i)); } }

    private void insert(int i,int insert_index) {
        E insert=array[i];
        if(i ==insert_index){
            return;
        }else{
            for(int l = i; l>insert_index; l--){
                array[l]=array[l-1];
            }
            array[insert_index]=insert;
        }
    }
}
