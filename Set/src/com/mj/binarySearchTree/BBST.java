package com.mj.binarySearchTree;
//将AVL的旋转代码抽出，BBST是可自平衡的二叉搜索树
public class BBST<E extends Comparable> extends BinarySearchTree<E> {
    public <E extends Comparable> BBST(Comparator<E> comparator) {

    }

    protected void rotateLeft(Node<E> grand){
        Node<E> parent=grand.right;
        Node<E> child=parent.left;
        grand.right=child;
        parent.left=grand;
        afterRotate(grand, parent, child);
    }

    protected void rotateRight(Node<E> grand){
        Node<E> parent=grand.left;
        Node<E> child=parent.right;
        grand.left= child;
        parent.right=grand;
        afterRotate(grand, parent, child);
    }

    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        //树，一定要记得调整parent
        // parent成为子树的根节点,更新parent的parent
        //如果不是根节点，那么是它parent的左还是右
        parent.parent= grand.parent;
        if(grand.isLeftChild()){
            grand.parent.left= parent;
        }else if(grand.isRightChild()){
            grand.parent.right= parent;
        }else{
            root= parent;
        }
        //更新各个节点的parent;
        if(child !=null){
            child.parent= grand;
        }
        //grand
        grand.parent= parent;
       // updateHeight(grand);//先更新矮的，再更新高的，顺序不可变
       // updateHeight(parent);红黑树不用更新高度
    }

}
