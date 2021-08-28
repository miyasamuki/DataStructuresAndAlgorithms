package com.mj;

import java.util.HashMap;

public class ValidBracket {
    private static HashMap<Character,Character> map=new HashMap<>();
    {
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');
    }
    public boolean isValid(String s) {
        //如果是左括号，直接入栈
        /*当遍历到右字符，将栈顶元素取出来，匹配就对了（这里有两种情况
        ）
        如果栈是空的说明括号无效
        如果栈不为空，将栈顶元素弹出，与右边想匹配
        */
        //当所有字符扫描完了，栈为空，则有效
        //所有字符扫描完毕，栈不为空，则无效

        //学到了
        //在java中如果是普通的属性，要在构造函数中给他赋值，
        //但是如果是静态属性，就可以在静态代码块中给它赋值

        Stack<Character> stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                stack.push(s.charAt(i));
            } else {
                if (stack.isEmpty()) return false;
                else {
                    Character left = stack.pop();
                    if (s.charAt(i)!=map.get(left)) return false;



                }
            }

        }
        return stack.isEmpty();

    }
}
