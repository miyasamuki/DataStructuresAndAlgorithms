package 队列;

import java.util.Stack;

class MyQueue {
    private Stack<Integer> instack;
    private  Stack<Integer> outstack;

    /** Initialize your data structure here. */
    public MyQueue() {
        instack=new Stack();
        outstack=new Stack();

    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        instack.push(x);

    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(outstack.isEmpty()) {
            while (!instack.isEmpty()) {
                outstack.push(instack.pop());
            }
        }
        return outstack.pop();

    }

    /** Get the front element.获取队头元素 */
    public int peek() {
        if(outstack.isEmpty()) {
            while (!instack.isEmpty()) {
                outstack.push(instack.pop());
            }
        }
        return  outstack.peek();

    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        if(instack.isEmpty()&&outstack.isEmpty()){
            return true;
        }
        return false;
    }
}
