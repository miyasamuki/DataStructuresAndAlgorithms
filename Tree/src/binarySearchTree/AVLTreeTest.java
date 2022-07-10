package binarySearchTree;

public class AVLTreeTest {
    static void test1(){
    Integer data[]=new Integer[]{
            7,4,9,2,5,8,11,3,12,1
    };
    AVLTree<Integer> avl=new AVLTree<>();
   // AVLTree<Integer> bst2=new AVLTree<>(new Comparator<>(Integer){

    //});
    for (int i=0;i<data.length;i++){
        avl.add(data[i]);
    }
        System.out.println(avl.treeToString());
        avl.add(13);
        System.out.println(avl.treeToString());
    }

    public static void main(String[] args) {
        test1();
    }
}
