package com.mj;

import com.mj.domian.Person;
import com.mj.printer.BinaryTrees;
import com.mj.priorityQueue.PriorityQueue;

import java.util.Comparator;

/**
 * @Author:ghq
 * @Date: 2022/04/02/22:31
 * @Description
 */
public class Test {
    String[] sourceList=new String[2];
    public static void main(String[] args) {
        PriorityQueue priorityQueue=new PriorityQueue();
        Person Alice=new Person("Alice",1);
        Person tom=new Person("tom",22);
        Person jack=new Person("Jack",13);
        Person amy=new Person("amy",4);
        priorityQueue.enQueue(Alice);
        priorityQueue.enQueue(tom);
        priorityQueue.enQueue(jack);
        priorityQueue.enQueue(amy);
        while (!priorityQueue.isEmpty()){
            System.out.println(priorityQueue.deQueue());
        }

    }
}
