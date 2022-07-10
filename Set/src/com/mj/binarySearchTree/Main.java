package com.mj.binarySearchTree;


public class Main {
    public static class IntegerComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer e1, Integer e2) {
            return e1-e2;
        }
    }

    public static void main(String[] args) {
        Integer[] arr=new Integer[]{7,4,9,2,5,8,11,1,3,10,12};
        BinarySearchTree<Integer> bst=new BinarySearchTree<Integer>(new IntegerComparator());
        for(int i=0;i<arr.length;i++){
            bst.add(arr[i]);
        }
        System.out.println(bst.treeToString());
        bst.remove(7);
        System.out.println(bst.treeToString());
        bst.levelOrderTraversal(new BinarySearchTree.Vistor(){

            @Override
            public void visit(Object element) {
                System.out.println(element+"new");
            }
        });
    }
}
