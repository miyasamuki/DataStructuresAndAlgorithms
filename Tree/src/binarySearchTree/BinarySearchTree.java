package binarySearchTree;

import com.sun.xml.internal.bind.ValidationEventLocatorEx;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<E extends Comparable> {
    protected int size;
    protected Node<E> root;
    protected Comparator<E> comparator;

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree() {

    }

    protected static class Node<E> {
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

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
    }

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<E>(parent, element);
    }

    public void add(E element) {
        checkElementNotNull(element);
        if (size == 0) {
            //如果是第一个节点
            this.root = createNode(element, null);
            //如果是AVL树则需要在添加节点之后做处理，如果是二叉搜索树则不需要实现这个方法
            afterNode(node(element));
        } else {
            //如果不是第一个节点
            Node<E> present = root;
            Node<E> parent = null;
            int cmp = 0;
            while (present != null) {
                cmp = present.element.compareTo(element);
                parent = present;
                if (cmp < 0) {
                    present = present.right;

                } else if (cmp > 0) {
                    present = present.left;
                } else {
                    present.element = element;
                    return;
                }
            }
            Node<E> newNode = createNode(element, parent);
            if (cmp < 0) {
                parent.right = newNode;
            }
            if (cmp > 0) {
                parent.left = newNode;
            }
            afterNode(node(element));
        }
        size++;
    }

    protected void afterNode(Node<E> node) {
        return;
    }

    protected int compare(E element, E element1) {
        if (comparator != null) {
            return comparator.compare(element, element1);
        } else {
            return element.compareTo(element1);
        }
    }

    public void preOrderTraversal() {
        //前序遍历
        preOrderTraversal(root);
    }

    public void preOrderTraversal(Node<E> node) {
        if (node == null) {return;}
        //前序遍历
        System.out.println(node.element);
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    /*
    中序遍历
     */
    public void inorderTraversal() {
        inorderTraversal(root);
    }

    public void inorderTraversal(Node<E> node) {
        if (node == null) {return;}
        inorderTraversal(node.left);
        System.out.println(node.element);
        inorderTraversal(node.right);
    }

    /*
    后序遍历
     */
    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    public void postOrderTraversal(Node<E> node) {
        if (node == null) return;
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.println(node.element);
    }

    /*
    层序遍历
     */
    public void levelOrderTraversal(Vistor<E> vistor) {
        if (root == null) return;
        Queue<Node<E>> queue = new LinkedList<Node<E>>();
        queue.offer(root);
        int rowSize = 1;//表示row入队此时这一行有1个元素
        int height = 0;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            // System.out.println(node.element);替换为自定义遍历法
            rowSize--;
            vistor.visit(node.element);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (rowSize == 0) {//表示这一层已经遍历完了，开始遍历下一层
                rowSize = queue.size();
                height++;
            }
        }
        System.out.println("这课树的高度为" + height);
    }

    public static interface Vistor<E> {
        void visit(E element);
    }

    /**
     * 计算树的高度
     *
     * @param
     */
    public int height() {
        return height(root);
    }

    public int height(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * 判断树是否是完全二叉树
     *
     * @param
     */
    public boolean isCompetele() {
        if (root == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf == true && !node.isLeaf()) {
                return false;
            }
            if (node.right != null && node.left != null) {
                queue.offer(node.right);
                queue.offer(node.left);
            } else if (node.left == null && node.right != null) {
                return false;
            } else {
                //其他的必须是叶子节点
                leaf = true;
            }
        }
        return true;
    }

    /**
     * 二叉树的前驱节点
     *
     * @param
     */
    private Node<E> predesessor(Node<E> node) {
        if (node == null) return null;
        Node<E> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        //所以来到这里时\
        // node.parent=null
        //node==node.parent.right
        return node.parent;
    }

    /**
     * 二叉树的前驱节点
     *
     * @param
     */
    private Node<E> successor(Node<E> node) {
        if (node == null) return null;
        Node<E> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        //所以来到这里时\
        // node.parent=null
        //node==node.parent.right
        return node.parent;
    }

    public void remove(E element) {
        remove(node(element));
    }

    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = element.compareTo(node.element);
            Node<E> parent = node;
            if (cmp == 0) {
                return node;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    //由于一个节点的主要就是parent,left,right的所以你更改一个节点的时候，一定主要要判断这几个属性
    private void remove(Node<E> node) {
        if (node == null) return;
        size--;
        //删除度为2的节点
        if (node.hasTwoChildren()) {
            Node<E> s = predesessor(node);
            node.element = s.element;
            node = s;
        }//删除度为0或度为1的节点
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {
            node.parent = replacement.parent;
            //说明是度为1,度为1的节点也要分是不是有父节点
            if (node.parent == null) {
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
            afterRemove(node,replacement);
        } else if (node.parent == null) {
            root = null;
            afterRemove(node,null);
        } else {//叶子节点并且不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            afterRemove(node,null);
        }//删除node
    }
    protected void afterRemove(Node<E> node,Node<E> replacement){return;}
    public void clean() {
        root = null;
        size = 0;
    }

    public boolean contain(E element) {
        return node(element) != null;
    }

    public void checkElementNotNull(E element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
    }

    public String treeToString() {
        StringBuilder sb = new StringBuilder();
        treeToString(root, sb, "");
        return sb.toString();

    }

    public void treeToString(Node<E> node, StringBuilder sb, String prefix) {
        if (node == null) return;
        treeToString(node.left, sb, prefix + "L---");
        sb.append(prefix).append(node.element).append("\n");
        treeToString(node.right, sb, prefix + "R---");
    }
}
