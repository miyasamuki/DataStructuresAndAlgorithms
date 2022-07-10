package com.mj.listSet;

import com.mj.ListSet;
import com.mj.Set;

public class ListSetTest {
    public static void main(String[] args) {
        Set<Integer> set=new ListSet<>();
        set.add(10);
        set.add(11);
        set.add(10);
        set.add(11);
        set.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });

    }
}
