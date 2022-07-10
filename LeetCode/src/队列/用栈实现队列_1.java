package 队列;

import java.util.Stack;

public class 用栈实现队列_1 {


    public static void main(String[] args) {
        Stack<Integer> instack=new Stack();
        Stack<Integer> outstack=new Stack();
        instack.push(11);
        instack.push(22);
        instack.push(33);
        instack.push(44);
        while(!instack.isEmpty()){
            outstack.push(instack.pop());
        }
      while(!outstack.isEmpty()){
          System.out.println(outstack.pop());
      }

    }

}
