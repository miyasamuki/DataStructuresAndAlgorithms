package binarySearchTree;

public class RBTreeTest {
    static void test3(){
    Integer data[] = new Integer[]{
            55,87,56,74,96,22,62,20,70,68,90,50
    };
    RBTree<Integer> rb= new RBTree<>();
    for(int i=0;i<data.length;i++){
        rb.add(data[i]);
    }
        System.out.println(rb.treeToString());
    for(int i=0;i< data.length;i++){
        rb.remove(data[i]);
        System.out.println("["+data[i]+"]");
        System.out.println(rb.treeToString());//删掉一个打印一次
    }
    }

    public static void main(String[] args) {
        test3();
    }
}
