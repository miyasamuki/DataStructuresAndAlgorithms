package com.环形队列;

public class CircleTest {
    public static void main(String[] args) {
       CircleQueue<Integer> circleQueue=new CircleQueue<>();
        for (int i = 0; i < 10; i++) {
            circleQueue.enQueue(i);//0,1,2,3,4...10
        }
        for (int i = 0; i <5 ; i++) {
            circleQueue.deQueue();//null null null null null 5 6 7 8 9
        }
        for(int i=10;i<20;i++) //5 6 7 8 9 10 11 12 13 14
        {
            circleQueue.enQueue(i);
        }
        while(!circleQueue.isEmpty()){
            System.out.println(circleQueue.deQueue());

        }


    }
}
