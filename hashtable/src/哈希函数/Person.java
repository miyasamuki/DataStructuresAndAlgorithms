package 哈希函数;

import java.util.Objects;

/**
 * @Author:ghq
 * @Date: 2021/12/30/15:43
 * @Description
 */
public class Person {
    private int age;
    private float height;
    private String name;
    public Person(int age,float height,String name){
        this.age=age;
        this.height=height;
        this.name=name;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {return true;}
//        if (o == null || getClass() != o.getClass()) {return false;}
//        Person person = (Person) o;
//        return age == person.age && Objects.equals(name, person.name);
//    }
    //用来对比两个对象是否相等
    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}//this==o表示内存地址相同，那么铁定了外面传进来的对象就是我自己
        //getClass()表示本类的类；o.getClass()表示外面传进来的类  表示外面传进来的类是空或者本类和外面传进来的lei不是同一个
        if (o == null || getClass() != o.getClass()) {return false;}//表示类不同还可以是!(o instanceof Person)
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
//    @Override
//    public int hashCode() {
//        //将所有参数按照字符串来计算
//        int hashCode=Integer.hashCode(age);
//        hashCode=hashCode*31+Float.hashCode(height);
//        hashCode=hashCode*31+name!=null?name.hashCode():0;
//        return hashCode;
//        //哈希值太大，整型溢出怎么办？不用作任何处理，溢出就溢出，反正只取后32位就好
//        //不重写hashCode()方法会有什么后果：不重写的话会将对象的内存地址值做hash,所以两个值一样的对象就被认为是两个不同的对象存再hashMap中了
//        //然而实际开发中，经常是要重写hashCode的
//
//    }
}
