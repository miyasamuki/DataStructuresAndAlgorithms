package com.mj.冒泡;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:ghq
 * @Date: 2022/04/10/10:58
 * @Description
 */
public class Times {
    //计算时间的类
    interface Task{
        void excute();
    }
    public void test(String description,Task task){

        long t1 = System.currentTimeMillis();
        //从外面传进来的，可以先不实现，等外面的调用方实现
        //如果是在里面自己new的类，则要自己实现
        task.excute();
        long t2 = System.currentTimeMillis();
        System.out.println(description+"耗时："+(t2-t1));


    }
}
