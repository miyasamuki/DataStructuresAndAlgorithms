package com.mj;


import com.mj.binarySearchTree.BinarySearchTree;
import com.mj.binarySearchTree.Comparator;

import java.util.LinkedList;
import java.util.Queue;

public class TreeMap<K, V> implements Map<K,V> {
    private static final boolean RED=false;
    private static final boolean BLACK=true;
    private int size;
    private Node<K,V> root;
    //
    private Comparator<K> comparator;

    public TreeMap(){
        this(null);
    }
    public TreeMap(Comparator<K> comparator){
        this.comparator=comparator;
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }
    protected Node<K,V> createNode(K key,V value,Node<K,V> parent) {
        return new Node<K,V>(key,value,parent);
    }
    @Override
    public void clear() {
        root=null;
        size=0;
    }
    public void checkKeyNotNull(K element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
    }


    @Override
    public V put(K key, V value) {
        checkKeyNotNull(key);
        if (size == 0) {
            //如果是第一个节点
            this.root = createNode(key,value, null);
            //如果是AVL树则需要在添加节点之后做处理，如果是二叉搜索树则不需要实现这个方法
            afterNode(node(key));
        } else {
            //如果不是第一个节点
            Node<K,V> present = root;
            Node<K,V> parent = null;
            int cmp = 0;
            while (present != null) {
                cmp = compare(key,present.key);
                parent = present;
                if (cmp < 0) {
                    present = present.left;

                } else if (cmp > 0) {
                    present = present.right;
                } else {
                    V oldValue= present.value;
                    present.value = value;
                    present.key=key;
                    return oldValue;
                }
            }
            Node<K,V> newNode = createNode(key,value, parent);
            if (cmp < 0) {
                parent.left = newNode;
            }
            if (cmp > 0) {
                parent.right = newNode;
            }
            afterNode(node(key));
        }
        size++;
        return value;
    }
    private int compare(K key1,K key2){
        if(comparator==null){
            return ((Comparable<K>)key1).compareTo(key2);
        }else{
            return comparator.compare(key1,key2);
        }
    }
    protected void afterNode(Node<K,V> node){
        Node<K,V> parent= node.parent;
        //如果添加的是根节点
        if(parent==null) {
            black(node);
            return;//这段代码一定要放到前面，因为如果是根节点，根节点的parent是null,null是black,
            //如果isBlack放前面，则永远添加不到根节点
        }
        Node<K,V> uncle=((Node<K,V>)parent).sibling();
        Node<K,V> grand=parent.parent;
        //如果父节点是黑色，直接返回
        if(isBlack(parent)) return;//为什么直接return呢,因为这个是afterAdd
        if(isRed(uncle)){
            black(uncle);
            black(parent);
            //祖父节点向上合并，相当于是一个新添加的节点
            afterNode(red(grand));
            return;
        }else{
            red(grand);
            if(grand.left==parent){
                if(node.isLeftChild()){//LL
                    black(parent);
                    rotateRight(grand);

                }else{//LR
                    black(node);
                    rotateLeft(parent);
                    rotateRight(grand);
                }
            }else{
                red(grand);
                if(node.isLeftChild()){//RL
                    black(node);
                    rotateRight(parent);
                    rotateLeft(grand);
                }else{//RR
                    black(parent);
                    rotateLeft(grand);
                }
            }
        }
    }
    @Override
    public V get(K key) {
        Node<K,V> node=node(key);
        return node!=null?node.value:null;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }
    private V remove(Node<K,V> node) {
        if (node == null) {return null;}
        V oldvalue=node.value;
        size--;
        //删除度为2的节点
        if (node.hasTwoChildren()) {
            Node<K,V> s = predesessor(node);
            node.key = s.key;
            node.value = s.value;
            node = s;
        }//删除度为0或度为1的节点
        Node<K,V> replacement = node.left != null ? node.left : node.right;
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
        return oldvalue;
    }
    protected void afterRemove(Node<K,V> node, Node<K,V> replacement){
        //如果要删除的节点是红色，那么直接删掉就可以了
        if(isRed(node)) {return;}
        //如果要删除的节点是黑色，但是要取代它的节点是红色
        if(isRed(replacement)){
            black(replacement);
            return;
        }
        //删除的是叶子节点，或者根节点
        Node<K,V>  parent=node.parent;
        //删除的是根节点
        if(parent==null) {return;}
        //删除的是叶子节点
        //已经不能用sibling()去判断兄弟节点了，已经事实上node节点已经被清空了
        //   Node<E> brother=((RBNode<E>)node).sibling();
        //这是为了分情况，如果parent.left==null证明是普通的删除，如果是node.isLeftChild()证明是父节点下溢的那种删除，因为如果node是已经删掉的
        //因为如果node是已经删掉的，调用node.isLeftChild()永远都返回空
        boolean left=parent.left==null||node.isLeftChild();
        Node<K,V>  sibling=left?parent.right:parent.left;

        if(left){//被删除的节点在左边，兄弟节点在右边
            //同样的，看兄弟节点是不是红色
            if(isRed(sibling)){
                black(sibling);
                red(parent);
                rotateLeft(parent);
                sibling=parent.right;
            }
            //现在统一处理兄弟节点是黑色的情况
            //2.兄弟节点没有可以借的
            if(isBlack(sibling.right)&&isBlack(sibling.left)){
                if(isBlack(parent)){
                    red(sibling);
                    afterRemove(parent,null);
                }
                black(parent);
                red(sibling);
            }else{//1.兄弟节点有红色的子节点可以借
                if(isBlack(sibling.right)){
                    //证明兄弟节点有左汉字
                    rotateRight(sibling);
                    sibling=parent.right;
                }
                color(sibling,colorOf(parent));
                black(parent);
                black(sibling.right);
                rotateLeft(parent);

            }

        }else{//被删除的节点在右边，兄弟节点在左边
            //兄弟节点是红色
            if(isRed(sibling)){
                black(sibling);
                red(parent);
                rotateRight(parent);
                sibling=parent.left;//更换兄弟
            }
            //现在可以统一处理兄弟节点是黑色的情况了
            if(isBlack(sibling.left)&&isBlack(sibling.right)){
                //兄弟节点没有多余的元素可以借，父节点要向下合并
                if(isBlack(parent)){
                    //如果父节点是黑色
                    red(sibling);
                    afterRemove(parent,null);
                }
                black(parent);
                red(sibling);

            }else{//兄弟节点至少有一个红色子节点可以借
                //可以先染色再旋转

                boolean right= sibling.right!=null;
                if(right){
                    rotateLeft(sibling);
                    sibling=parent.left;
                }
                /**或者可以这么写
                 * if(isBlack(sibling.left){
                 *     rotateLeft(sibling)
                 * }
                 */
                color(sibling,colorOf(parent));
                black(parent);
                black(sibling.left);
                //其他两种情况都可以按LL型处理
                rotateRight(parent);

            }


        }

    }
    private Node<K,V> predesessor(Node<K,V> node) {
        if (node == null) {return null;}
        Node<K,V> p = node.left;
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

    @Override
    public boolean containsKey(K key) {
        return node(key)!=null;
    }

    @Override
    public boolean containsValue(V value) {
   //层序遍历法，使用队列的方式来遍历
        if(root==null) {return false;}
        Queue queue=new LinkedList();
        queue.offer(root);
        while(queue!=null){
            Node<K,V> node=(Node<K,V>)queue.poll();
            if(valEqual(value,node.value)){return true;}
            if(node.left!=null){
                queue.offer(node.left);
            }
            if(node.right!=null){
                queue.offer(node.right);
            }

        }
        return false;

    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        traversal( root, visitor);
    }

    private boolean valEqual(V v1,V v2){
        return v1==null?v2==null:v1.equals(v2);
    }

    public void traversal(Node<K,V> node,Visitor<K, V> visitor) {
        //使用红黑树的中序遍历：中序遍历的顺序是从小到大
        //也就是说中序遍历是left root  right
        if(node==null||visitor.stop) {return;}
        traversal(node.left,visitor);
        visitor.visit(node.key,node.value);
        traversal(node.right,visitor);


    }
    private Node<K,V> node(K key) {
        Node<K,V> node = root;
        while (node != null) {
            int cmp = comparator.compare(key,node.key);
            Node<K,V> parent = node;
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
    protected void rotateLeft(Node<K,V> grand){
        Node<K,V> parent=grand.right;
        Node<K,V> child=parent.left;
        grand.right=child;
        parent.left=grand;
        afterRotate(grand, parent, child);
    }

    protected void rotateRight(Node<K,V> grand){
        Node<K,V> parent=grand.left;
        Node<K,V> child=parent.right;
        grand.left= child;
        parent.right=grand;
        afterRotate(grand, parent, child);
    }
    protected void afterRotate(Node<K,V> grand,Node<K,V> parent,Node<K,V> child) {
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
    private Node<K,V> color(Node<K,V>node, boolean color){
        if(node==null) {return node;}
        ((Node<K,V>)node).color=color;
        return node;
    }
    private Node<K,V> red(Node<K,V> node){
        return color(node,RED);
    }
    private Node<K,V>black(Node<K,V> node){
        return color(node,BLACK);
    }
    private boolean colorOf(Node<K,V> node){
        return node==null?BLACK:((Node<K,V>)node).color;
    }
    private boolean isBlack(Node<K,V> node){
        return colorOf(node)==BLACK;
    }
    private boolean isRed(Node<K,V>node){
        return colorOf(node)==RED;
    }
    private static class Node<K,V> {
        K key;
        V value;
        boolean color=RED;
        Node<K,V> left;
        Node<K,V> right;
        Node<K,V> parent;

        public Node(K key,V value,Node<K,V> parent) {
            this.key=key;
            this.value=value;
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
        public Node<K,V>  sibling(){
            //兄弟节点一定是同一个父亲
            if(isLeftChild()){
                return parent.right;
            }
            if(isRightChild()){
                return parent.left;
            }
            return null;
        }

    }
}
