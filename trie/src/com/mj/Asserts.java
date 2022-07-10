package com.mj;

/**
 * @Author:ghq
 * @Date: 2022/04/06/10:52
 * @Description
 */
public class Asserts {
    public static void tests(boolean testValue){
        if(testValue==true){
            System.out.println("测试通过");
        }else{
            throw new IllegalArgumentException("测试不通过");
        }
    }
}
