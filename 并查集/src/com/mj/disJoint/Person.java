package com.mj.disJoint;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;

import java.util.Objects;

/**
 * @Author:ghq
 * @Date: 2022/04/28/15:28
 * @Description
 */

public class Person {
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }
}
