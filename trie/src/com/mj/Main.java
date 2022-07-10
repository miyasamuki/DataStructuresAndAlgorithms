package com.mj;

/**
 * @Author:ghq
 * @Date: 2022/04/05/16:57
 * @Description
 */
public class Main {
    //测试trie
    public static void main(String[] args) {
        NewTrie<Integer> trie=new NewTrie<>();
        //Trie<Integer> trie=new Trie<>();
        trie.add("cat",1);
        trie.add("dog",2);
        trie.add("catalog",3);
        trie.add("cast",4);
        trie.add("小码哥",5);
        Asserts.tests(trie.size()==5);
        Asserts.tests(trie.starsWith("c"));
        trie.starsWith("ca");
        Asserts.tests(trie.starsWith("ca"));
        Asserts.tests(trie.starsWith("cat"));
        Asserts.tests(trie.starsWith("cata"));
        Asserts.tests(trie.get("小码哥")==5);
//        trie.remove("小码哥");
//        Asserts.tests(trie.remove("小码哥")==5);
    }

}
