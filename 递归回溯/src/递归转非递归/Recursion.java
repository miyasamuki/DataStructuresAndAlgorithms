package 递归转非递归;

import java.util.Stack;

/**
 * @Author:ghq
 * @Date: 2022/05/29/17:41
 * @Description
 */
public class Recursion {
    public static void main(String[] args) {
        log(4);
    }
    static void log(int n){
        if(n<1) {return;}
        log(n-1);
        int v=n+10;
        System.out.println(v);
    }
    static class Frams{
        int n;
        int v;

        public Frams(int n, int v) {
            this.n = n;
            this.v = v;
        }

        @Override
        public String toString() {
            return "Frams{" +
                    "n=" + n +
                    ", v=" + v +
                    '}';
        }
    }
    static void newlog(int n){
        Stack<Frams> stack=new Stack();
        for (int i = n; i >0; i--) {
            Frams frams=new Frams(n,n+10);
            stack.push(frams);
        }
        while (!stack.isEmpty()){
            Frams pop = stack.pop();
            System.out.println(pop);
        }
    }

}
