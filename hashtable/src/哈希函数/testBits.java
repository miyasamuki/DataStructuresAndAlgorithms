package 哈希函数;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:ghq
 * @Date: 2021/12/28/20:23
 * @Description
 */
public class testBits {
    public static void main(String[] args) {
       //计算各种数据类型的哈希值
        int a=110;
        float b=10.6f;
        long c=156L;
        double d=10.9;
        String e="rose";
        //整数类型的hashCode
        System.out.println(Integer.hashCode(a));
        //浮点类型的hashCode
        System.out.println(Float.floatToIntBits(b));
        //long类型的hashCode
        System.out.println(Long.hashCode(c));
        System.out.println(Double.doubleToLongBits(d));
        //String
        System.out.println(e.hashCode());
        //重写hashCode后
        Person p1=new Person(18,20,"小明");
        Person p2=new Person(18,20,"小明");
        Map<Object,Object> map=new HashMap<>();
        map.put(p1,"abc");
        map.put(p2,"bcd");
        System.out.println(map.size());//1
        System.out.println(map.get(p1));//bcd

    }
}
