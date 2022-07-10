package com.mj.disJoint;

/**
 * @Author:ghq
 * @Date: 2022/04/25/22:38
 * @Description
 */
public class Main {
    public static void main(String[] args) {
        GenericUnionFind uf=new GenericUnionFind<Person>();
        uf.mapset(new Person(0,"小0"));
        uf.mapset(new Person(1,"小1"));
        uf.mapset(new Person(2,"小2"));
        uf.mapset(new Person(3,"小3"));
        uf.mapset(new Person(4,"小4"));
        uf.mapset(new Person(5,"小5"));
        uf.mapset(new Person(6,"小6"));
        uf.mapset(new Person(7,"小7"));

        uf.union(new Person(4,"小4"),new Person(0,"小0"));
        uf.union(new Person(3,"小3"),new Person(0,"小0"));
        uf.union(new Person(1,"小1"),new Person(0,"小0"));
        uf.union(new Person(3,"小3"),new Person(2,"小2"));
        uf.union(new Person(5,"小5"),new Person(2,"小2"));
         uf.union(new Person(6,"小6"),new Person(7,"小7"));

        System.out.println(uf.isSame(new Person(2,"小2"),new Person(7,"小7")));
        uf.isSame(new Person(4,"小4"),new Person(5,"小5"));
        System.out.println(uf.isSame(new Person(4,"小4"),new Person(5,"小5")));
        uf.union(new Person(0,"小0"),new Person(6,"小6"));
        System.out.println(uf.isSame(new Person(2,"小2"),new Person(7,"小7")));

    }
}
