package com.mj;


public class JosephusTest {
    static void josephus(){
        双向循环链表<Integer> list=new 双向循环链表<>();
        for (int i = 1; i <=8 ; i++) {
            list.add(i-1,i);
        }
        list.reset();//指向头节点
        //移动两部
        while(!list.isEmpty()){
        list.next();
        list.next();
        System.out.println(list.remove());//指向的位置跳两次，然后删掉所指向的东西，remove之后指向下一个节点
    }
    }

    public static void main(String[] args) {
        josephus();
    }
}
