package com.mj;

/**
 * @Author:ghq
 * @Date: 2022/04/14/23:09
 * @Description
 */
public class Person implements Comparable<Person>{
    private int age;
    private int score;

    public Person(int age, int score) {
        this.age = age;
        this.score = score;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Person o) {
        return age-o.getAge();
    }
}
