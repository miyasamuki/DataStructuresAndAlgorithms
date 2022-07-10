package 二叉树.搜索二叉树;

import java.util.LinkedList;
import java.util.Queue;

//将二叉树的左右子树交换
class BinarySearchTree<E extends Comparable> {
        private int size;
        private Node<E> root;
        public BinarySearchTree() {
        }
    private class Node<E> {
        Node<E> parent;
        Node<E> left;
        Node<E> right;
        E element;

        public Node(Node<E> parent, E element) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }
        public void add(E element) {
           if(element==null) throw new IllegalArgumentException();
            if (size == 0) {
                //如果是第一个节点
                this.root = new Node<E>(null, element);
            } else {
                //如果不是第一个节点
                Node<E> present = root;
                Node<E> parent=null;
                int cmp=0;
                while (present != null) {
                    cmp=present.element.compareTo(element);
                    parent=present;
                    if (cmp<0) {
                        present=present.right;

                    } else if(cmp>0){
                        present=present.left;
                    }else {
                        present.element=element;
                        return;
                    }
                }
                Node<E> newNode=new Node<>(parent,element);
                if(cmp<0){
                    parent.right=newNode;
                }
                if(cmp>0){
                    parent.left=newNode;
                }
            }
            size++;
        }


        //翻转二叉树的遍历法
        public void reverseBinaryTree(){
            reverseBinaryTree(root);
        }
        public void reverseBinaryTree(Node<E> node){
            if(node==null) return;
            reverseBinaryTree(node.right);
            System.out.println(node.element);
            reverseBinaryTree(node.left);
        }
        //打印树
       public void printTree(){
          printTree(root);

       }
       public void printTree(Node<E> root){
            if(root==null) return;
           Queue<Node<E>> queue=new LinkedList<>();
           queue.offer(root);
           while(queue.isEmpty()){
               Node<E> node=queue.poll();
               System.out.println(node.element);
               if(node.left!=null){
                   queue.offer(node.left);
               }
               if(node.right!=null){
                   queue.offer(node.right);
               }
           }
       }
       public String treeToString(){
            StringBuilder sb=new StringBuilder();
           treeToString(root,sb,"");
            return sb.toString();

       }
       public void treeToString(Node<E> node,StringBuilder sb,String prefix){
            if(node==null) return;
           treeToString(node.left,sb,prefix+"L---");
           sb.append(prefix).append(node.element).append("\n");
           treeToString(node.right,sb,prefix+"R---");
       }
    }



