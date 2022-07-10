package com.mj.二分法查找;

/**
 * @Author:ghq
 * @Date: 2022/04/15/21:30
 * @Description
 */
public class BinarySearch {
    public static int indexOf(int[] array,int v){
        if(array==null||array.length==0) {return -1;}
        int begin=0;
        int end=array.length;

        while(begin<end) {
            int mid=(begin+end)>>1;
            if (array[mid] > v) {
                end=mid;

            } else if(array[mid]<v){

                begin=mid+1;
            }else{
                return mid;
            }
        }
       return -1;

    }
}
