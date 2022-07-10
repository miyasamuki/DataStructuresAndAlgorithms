package com.deque;

public class DequeTest {
    public static void main(String[] args) {
        Deque<Integer> deque=new Deque<>();
        deque.enQueueFront(11);
        deque.enQueueFront(22);
        deque.enQueueFront(33);
        deque.enQueueFront(44);//44,33,22,11
        deque.enQueueRear(55);//44,33,22,11,55
        while(!deque.Empty()){
            System.out.println(deque.deQueueFront());
        }

    }



}
