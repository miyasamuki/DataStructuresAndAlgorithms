package com.mj.binarySearchTree;

public class AVLTree<E extends Comparable> extends BBST<E>{
    //由于父类的构造函数无法继承，所以我们要自己写
    public AVLTree(){
       this(null);
    }

    public AVLTree(Comparator<E> comparator){
        super(comparator);


    }
  //如果是AVL树则需要重写afterNode()方法
    @Override
    protected void afterNode(Node<E> node) {
        //由于每一个节点最初都是叶子节点，所以高度需要afterNode调节
        while((node=node.parent)!=null){
            //由于node=node.parent的操作，所以最后找到的一定是不平衡的节点
            if(isBalanced(node)){
               //更新高度
                updateHeight(node);
            }else{
              //恢复平衡
                rebalance(node);
                //整棵树恢复平衡
                break;
            }
        }
    }
    protected void afterRemove(Node<E> node,Node<E> replacement){
        //平衡并更新删除节点以及它的所有父节点
        while((node=node.parent)!=null){
             if(!isBalanced(node)){
                 rebalance(node);
             }else{
                 updateHeight(node);
             }
        }

    }
    /*
    恢复平衡
    rebalance传进来的那个高度最低的那个不平衡节点:失衡点
     */
    private void rebalance(Node<E> grand){
       Node<E> parent =((AVLNode<E>)grand).tallerChild();
       Node<E> node=((AVLNode<E>)parent).tallerChild();
       if(parent.isLeftChild()){
           if(node.isLeftChild()){//LL
              //LL类型 右旋
               rotateRight(grand);
           }else{//LR
              rotateLeft(parent);
              rotateRight(grand);
           }
       }else{
           if(node.isLeftChild()){//RL
                 rotateRight(parent);
                 rotateLeft(grand);
           }else{//RR
                 rotateLeft(grand);
           }
       }
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
    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
       super.afterRotate(grand, parent, child);
        updateHeight(grand);//先更新矮的，再更新高的，顺序不可变
        updateHeight(parent);
    }

    private void updateHeight(Node<E> node){
        ((AVLNode<E>) node).updateHeight();
    }

    //由于是AVL树，所以node需要一个高度，用来计算失衡因子
    private static class AVLNode<E> extends Node<E>{
        int height=1;//因为每一个节点创建的时候都是叶子节点，
        //叶子节点的高度就是1
        public AVLNode(Node<E> parent,E element){
            super(parent,element);
        }
        public int balanceFactor(){
            int leftHeight=left==null?0:((AVLNode<E>)left).height;
            int rightHeight=right==null?0:((AVLNode<E>)right).height;
            return leftHeight-rightHeight;
        }
        public void updateHeight(){
            int leftHeight= left == null?0:((AVLNode<E>)left).height;
            int rightHeight = right ==null?0:((AVLNode<E>)right).height;
            height=1+Math.max(leftHeight,rightHeight);
        }
        public Node<E> tallerChild(){
            int leftHeight= left ==null?0:((AVLNode<E>)left).height;
            int rightHeight = right ==null?0:((AVLNode<E>)right).height;
            if(leftHeight>rightHeight) return left;
            if(leftHeight<rightHeight) return right;
            return isLeftChild()?left:right;
        }

    }
    protected Node<E> createNode(E element,Node<E> parent){
       return new AVLNode<E>(parent,element);
    }
    protected boolean isBalanced(Node<E> node){
        return Math.abs(((AVLNode<E>)node).balanceFactor())<=1;
        //这里只是一个节点是否平衡，如果要判断整个树是不是平衡，要遍历所有节点才行
    }
}
