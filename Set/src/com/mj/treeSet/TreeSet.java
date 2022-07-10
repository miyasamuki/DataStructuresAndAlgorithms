package com.mj.treeSet;

import com.mj.Set;
import com.mj.binarySearchTree.RBTree;

public class TreeSet<E extends Comparable> implements Set<E> {
    private RBTree<E> tree=new RBTree<E>();
    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public boolean contains(E element) {
        return tree.contain(element);
    }

    @Override
    public void add(E element) {
          tree.add(element);
    }

    @Override
    public void remove(E element) {
          tree.remove(element);
    }

    @Override
    public void traversal(Visitor<E> vistor) {
            tree.inorderTraversal();
    }
}
