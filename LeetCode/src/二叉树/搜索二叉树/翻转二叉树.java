package 二叉树.搜索二叉树;

public class 翻转二叉树 {
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{7, 4, 9, 2, 5, 8, 11, 1, 3, 10, 12};
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        for (int i = 0; i < arr.length; i++) {
            bst.add(arr[i]);
        }
        System.out.println(bst.treeToString());
    }
}
