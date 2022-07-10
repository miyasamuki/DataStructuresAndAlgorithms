package 队列;

import com.sun.org.apache.xml.internal.security.keys.storage.implementations.CertsInFilesystemDirectoryResolver;

public class MyQueueTest {
    public static void main(String[] args) {
        MyQueue myqueue=new MyQueue();
        myqueue.push(11);
        myqueue.push(22);
        myqueue.push(33);
        myqueue.push(44);
        System.out.println(myqueue.pop());//outstack(22,33,44)
        myqueue.push(55);
        System.out.println(myqueue.peek());

    }

}
