package com.mj.冒泡;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author:ghq
 * @Date: 2022/04/09/23:37
 * @Description
 */
public class BubbleAlgorithm {
    public static void main(String[] args) {
        int[] list=new int[10];
        for(int i=0;i<10;i++){
            list[i]=(int)(Math.random()*100);
        }
      System.out.println(Arrays.toString(list));
         Times times=new Times();
//         times.test("冒泡排序1",()->{
//             BubbleSort1(list);
//         });
        times.test("冒泡排序3",()->{
            BubbleSort3(list);
        });
        System.out.println(Arrays.toString(BubbleSort3(list)));
    }
    public static int[] BubbleSort1(int[] list){
        for(int end=list.length-1;end>1;end--) {

            for (int i = 0; i < end; i++) {
                if (list[i] > list[i + 1]) {
                    int temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                }

            }

        }
        return list;
    }
    //冒泡算法优化1：如果是全部有序的数组，不再进行排序
    public static int[] BubbleSort2(int[] list){
        for(int end=list.length-1;end>1;end--) {
            boolean sort=true;
            for (int i = 0; i < end; i++) {
                if (list[i] > list[i + 1]) {
                    int temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    sort=false;
                }

            }
            if(sort==true){
                break;
            }
        }
        return list;
    }
    //冒泡算法优化3：后半部分如果已经有序了，则不再进行排序
    //所以说冒泡算法最坏的时间复杂度是1+2+3+.....+n=(1+n)*n/2
    //也就是说冒泡算法的时间复杂度是O(n^2)
    //最好的情况是本来就是全有序的，只有内层一次循环，所以时间复杂度是O(n)
    //空间复杂度是O(1),因为没有利用到
    public static int[] BubbleSort3(int[] list){
        int sortIndex=list.length-1;
        for(int end=sortIndex;end>1;end--) {

            for (int i = 0; i < end; i++) {
                if (list[i] > list[i + 1]) {
                    int temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    sortIndex=i;
                }

            }

        }
        return list;
    }
}
