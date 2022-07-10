package com.mj.domian;

/**
 * @Author:ghq
 * @Date: 2022/04/02/22:33
 * @Description
 */
public class Person implements Comparable<Person> {
    private int age;
    private String name;

    public Person(String name, int age) {
        this.name=name;
        this.age=age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return age-o.getAge();
    }
}
