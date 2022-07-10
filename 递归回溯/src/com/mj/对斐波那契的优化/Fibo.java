package com.mj.对斐波那契的优化;

/**
 * @Author:ghq
 * @Date: 2022/05/27/21:58
 * @Description
 */
public class Fibo {
    public static void main(String[] args) {
        System.out.println(fibo2(20,new int[21]));

    }

    static int fibo2(int n, int[] array) {
        array[1] = array[2] = 1;

        for (int i = 3; i <= n; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n];
    }
    static int fibo3(int n){
        //为了进一步节省空间，使用滚动数组
        //凡是%2的地方都可以换成&1
        //也就是说这个数组只有两个位置，不停换内容
        if(n<=2) {return 1;}
        int[] array=new int[2];
        array[0]=array[1]=1;
        for (int i = 3; i <=n ; i++) {
            array[i%2]=array[(i-1)%2]+array[(i-2)%2];

        }
        return array[n%2];
    }
    //再进一步优化，甚至可以不用数组，使用first和second来替代,其实first和second就相当于两个指针
    static int fibo4(int n){
        if(n<=2) {return 1;}
        int first=1;
        int second=1;
        for (int i = 3; i <=n ; i++) {
            second=first+second;
            first=second-first;
        }
        return second;
    }


}
