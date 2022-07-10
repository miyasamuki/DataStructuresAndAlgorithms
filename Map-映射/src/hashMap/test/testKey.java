package hashMap.test;

import com.mj.Map;
import hashMap.HashMap;

import java.util.Hashtable;

/**
 * @Author:ghq
 * @Date: 2022/01/09/0:05
 * @Description
 */
public class testKey {

    public static void main(String[] args) {
        new Hashtable<>();
        Map map=new HashMap<Object,Integer>();
//        Key key1=new SubKey1(1);
//        Key key2=new SubKey2(1);
//        map.put(key1,1);
//        map.put(key2,2);
//        System.out.println(key2.equals(key1));
//        System.out.println(map.size());
      test3((HashMap<Object,Integer>)map);
        //test4((HashMap<Object,Integer>)map);
      //  test5((HashMap<Object,Integer>)map);

    }
    static void test2(HashMap<Object,Integer> map){
        for (int i = 1; i <=20; i++) {
            map.put(new Key(i),i);
        }
        for (int i = 5; i <=7 ; i++) {
            map.put(new Key(i),i+5);
        }
        System.out.println(map.size()==20);
        System.out.println(map.get(new Key(4))==4);
        System.out.println(map.get(new Key(5))==10);
        System.out.println(map.get(new Key(8))==8);
    }
    static void test3(HashMap<Object,Integer> map){
        map.put(null,1);//1
        map.put(new Object(),2);//2
        map.put("jack",3);//3
        map.put(10,4);//4
        map.put(new Object(),5);//5:Object的equals方法是两个对象必须是相同的对象，内存地址相同
        map.put("jack",6);//5 因为jack已经有了
        map.put(10,7);//5  因为10已经有了
        map.put(null,8);//5  因为null已经有了
        map.put(10,null);//5 因为10已经有了
        System.out.println("test3");
        System.out.println(map.size()==5);
        System.out.println(map.get(null)==8);

    }
    static void test4(HashMap<Object, Integer> map) {
        System.out.println("test4");
        map.put("jack", 1);
        map.put("rose", 2);
        map.put("jim", 3);
        map.put("jake", 4);
        map.remove("jack");
        map.remove("jim");
        for (int i = 1; i <= 10; i++) {
            map.put("test" + i, i);
            map.put(new Key(i), i);
        }
        for (int i = 5; i <= 7; i++) {
            Asserts.test(map.remove(new Key(i)) == i);
        }
        for (int i = 1; i <= 3; i++) {
            map.put(new Key(i), i + 5);
        }
        Asserts.test(map.size() == 19);
        Asserts.test(map.get(new Key(1)) == 6);
        Asserts.test(map.get(new Key(2)) == 7);
        Asserts.test(map.get(new Key(3)) == 8);
        Asserts.test(map.get(new Key(4)) == 4);
        Asserts.test(map.get(new Key(5)) == null);
        Asserts.test(map.get(new Key(6)) == null);
        Asserts.test(map.get(new Key(7)) == null);
        Asserts.test(map.get(new Key(8)) == 8);

    }

    static void test5(HashMap<Object, Integer> map) {
        System.out.println("test5");
        for (int i = 1; i <= 20; i++) {
            map.put(new SubKey1(i), i);
        }
        map.put(new SubKey2(1), 5);
        System.out.println(map.get(new SubKey1(1)) == 5);
        Asserts.test(map.get(new SubKey2(1)) == 5);
        System.out.println(map.size());
    }
}
