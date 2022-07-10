package com.mj.基数排序;

import com.mj.Sort;

/**
 * @Author:ghq
 * @Date: 2022/04/23/23:11
 * @Description
 */
public class RedixSort extends Sort<Integer> {
    @Override
    protected void sort() {
        //首先由高位到低位依次排序
        //1.首先我要知道最大的数，有多少位
        int max=0;
        for (int i = 0; i < array.length; i++) {
            if(array[i]>max){
                max=array[i];
            }

        }
        //如果判断一个数有多少位（位数是divider)
        for (int divider = 1; divider <= max; divider*=10) {
            //从低到高
            countingsort(divider);
        }
    }

    /**
     * 个位数：array[i]/1%10
     * 十位数：array[i]/10%10
     * 百位数：array[i]/100%10
     * 然后将countingsort改造为处理array[i]的基数的排序
     */
    protected void countingsort(int divider) {
        //array[i]全部换成array[i]的基数
        int[] counts;
        int[] newArray;

        //每次都是调用基数排序，对某个位数进行排序，因此，0-10就够了
        counts=new int[10];
        for (int i = 0; i < array.length; i++) {
            counts[array[i]/divider%10]++;
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
            newArray[--counts[array[i]/divider%10]]=array[i];

        }
        for (int i = 0; i < array.length; i++) {
            array[i]=newArray[i];
        }
    }
}
