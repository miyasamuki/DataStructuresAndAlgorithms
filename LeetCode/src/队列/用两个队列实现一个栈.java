package 队列;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class 用两个队列实现一个栈{
    Queue<Integer> queue_1 = null;
    Queue<Integer> queue_2 = null;
    public 用两个队列实现一个栈() {
       queue_1 = new LinkedList<Integer>();
       queue_2 = new LinkedList<Integer>();
    }
    public void push(int x){
        //当栈中有值的时
        //
        queue_2.offer(x);
        while(!queue_1.isEmpty()){
            queue_2.offer(queue_1.poll());
        }
        Queue<Integer> temp = queue_1;
        queue_1 = queue_2;
        queue_2 = temp;

    }
    public <E>Object pop(){
     return queue_1.poll();

    }
    public <E>Object top(){
        return queue_1.peek();
    }
    public boolean empty(){
       return queue_1.isEmpty();
    }

    public static void main(String[] args) {
        用两个队列实现一个栈 test=new 用两个队列实现一个栈();
    }
}
