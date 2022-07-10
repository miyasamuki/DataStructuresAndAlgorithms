package com.mj.cn.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Author:ghq
 * @Date: 2022/01/29/10:38
 * @Description
 */
public class TestLinkedHashMap {
    //写一个遍历hashMap的
    public static void main(String[] args) {
        Map<String,String> hashMap=new HashMap<String,String>();
        hashMap.put("111","aaaa");
        hashMap.put("222","bbb");
        hashMap.put("333","ccc");
      //事实上map有两种遍历方式,一种是遍历key,一种是遍历entry
        Set<Map.Entry<String, String>> entries = hashMap.entrySet();
        //获取遍历器
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while(iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            String key=next.getKey();
            String value=next.getValue();
            System.out.println("key:"+key+",value:"+value);
        }
    }

}
