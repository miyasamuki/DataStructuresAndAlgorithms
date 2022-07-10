package com.mj.希尔排序;

import com.mj.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:ghq
 * @Date: 2022/04/19/21:20
 * @Description
 */
public class ShellSort<E extends Comparable<E>> extends Sort<E> {
    private List<Integer> stepSequence;
    /**
     * 如果array.length=16
     * 那么stepSequence就是1,2,4,8
     */
    public void generate(){
        stepSequence=new ArrayList<>();
        int size=array.length;
        while((size>>=1)>0){
            stepSequence.add(size);
        }
    }
    @Override
    protected void sort() {
        generate();
        for(Integer step:stepSequence){
            //每一次都进行一遍插入排序
            for(int col=0;col< step;col++){
                for(int begin=col+step;begin<array.length;begin+=step){
                    int cur=begin;
                    while(cur>col&&cmp(array[cur],array[cur-step])<0){
                        swap(cur,cur-step);
                        cur-=step;
                    }
                }
            } } }
}
