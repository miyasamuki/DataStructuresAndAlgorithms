package com.mj.binarySearchTree;

public class RBTree<E extends Comparable> extends BBST<E> {
    private static final boolean RED=false;
    private static final boolean BLACK=true;
    public RBTree(){
        this(null);
    }
    public RBTree(Comparator<E> comparator){
        super(comparator);
    }
    //afterNode
    @Override
    protected void afterNode(Node<E> node){
        Node<E> parent= node.parent;
        //如果添加的是根节点
        if(parent==null) {
            black(node);
            return;//这段代码一定要放到前面，因为如果是根节点，根节点的parent是null,null是black,
            //如果isBlack放前面，则永远添加不到根节点
        }
        Node<E> uncle=((RBNode<E>)parent).sibling();
        Node<E> grand=parent.parent;
        //如果父节点是黑色，直接返回
        if(isBlack(parent)) {return;}//为什么直接return呢,因为这个是afterAdd
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
    protected void afterRemove(Node<E> node,Node<E> replacement){
        //如果要删除的节点是红色，那么直接删掉就可以了
        if(isRed(node)) {return;}
        //如果要删除的节点是黑色，但是要取代它的节点是红色
        if(isRed(replacement)){
             black(replacement);
             return;
        }
        //删除的是叶子节点，或者根节点
        Node<E> parent=node.parent;
        //删除的是根节点
        if(parent==null) {return;}
        //删除的是叶子节点
     //已经不能用sibling()去判断兄弟节点了，已经事实上node节点已经被清空了
         //   Node<E> brother=((RBNode<E>)node).sibling();
        //这是为了分情况，如果parent.left==null证明是普通的删除，如果是node.isLeftChild()证明是父节点下溢的那种删除，因为如果node是已经删掉的
        //因为如果node是已经删掉的，调用node.isLeftChild()永远都返回空
        boolean left=parent.left==null||node.isLeftChild();
        Node<E> sibling=left?parent.right:parent.left;
        //==============================================================
            //兄弟节点是黑色，并且有红色的儿子可以借 下面brothe就是sibling
//            if(isBlack(brother)){
//                //没有儿子可以借
//                if(brother.left==null&&brother.right==null){
//                    //下溢
//                    if(isRed(parent)){//如果父节点是红色，不用考虑父节点也下溢的情况
//                        black(parent);
//                        red(brother);
//                    }else{//父节点是黑色，要递归父节点
//                        red(brother);
//                        afterRemove(parent,null);
//                    }
//
//                }else{
//                    //有红色的2个儿子可以借
//                    if(brother.hasTwoChildren()){
//                        if(brother.isLeftChild()){
//                            //LL型旋转比较方法
//                            rotateRight(parent);
//                            red(brother);
//                            black(parent);
//                            black(brother.left);
//
//                        }
//                        if(brother.isRightChild()){
//                            //RR型旋转比较方便
//                            rotateLeft(parent);
//                        }
//
//
//                    }else {
//                        //有红色的一个儿子可以借
//                        Node<E> brotherSon = brother.left == null ?brother.right:brother.left;
//                        if(brother.isLeftChild()){//L
//                            if(brotherSon==brother.left){//LL
//                                rotateRight(parent);
//                            }else{//LR
//                                rotateLeft(brother);
//                                rotateRight(parent);
//                            }
//
//
//                        }else{//R
//                            if(brotherSon==brother.right){//R
//                                rotateLeft(parent);
//                            }else{//RL
//                                rotateRight(brother);
//                                rotateLeft(parent);
//                            }
//
//                        }
//
//                    }
//
//                }
//
//
//            }else{
//                //兄弟节点是红色
//              if(brother.isLeftChild()){
//                  rotateRight(parent);
//                  black(brother);
//                  red(parent);
//                  //然后上面的的情况再来一遍
//              }else{
//                  rotateLeft(parent);
//                  black(brother);
//                  red(parent);
//                  //然后上面的情况再来一遍
//              }
//
//            }

//================================================================
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
    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<E>(parent, element);
    }
    //对节点染色
    private Node<E> color(Node<E> node,boolean color){
        if(node==null) {return node;}
        ((RBNode<E>)node).color=color;
        return node;
    }
    private Node<E> red(Node<E> node){
        return color(node,RED);
    }
    private Node<E> black(Node<E> node){
        return color(node,BLACK);
    }
    private boolean colorOf(Node<E> node){
        return node==null?BLACK:((RBNode<E>)node).color;
    }
    private boolean isBlack(Node<E> node){
        return colorOf(node)==BLACK;
    }
    private boolean isRed(Node<E> node){
        return colorOf(node)==RED;
    }
    @Override
    public String treeToString() {
        StringBuilder sb = new StringBuilder();
        treeToString(root, sb, "");
        return sb.toString();

    }
    @Override
    public void treeToString(Node<E> node, StringBuilder sb, String prefix) {
        if (node == null) return;
        treeToString(node.left, sb, prefix + "L---");
        if(colorOf(node)==RED){
            sb.append("【RED】");
        }
        sb.append(prefix).append(node.element).append("\n");
        treeToString(node.right, sb, prefix + "R---");
    }
    public static class RBNode<E> extends Node<E> {
        boolean color;
        public RBNode(Node<E> parent, E element) {
            super(parent, element);
        }
        @Override
        public boolean isLeaf(){
            return left==null && right==null;
        }
        @Override
        public boolean hasTwoChildren(){
            return left!=null&&right!=null;
        }
        @Override
        public boolean isLeftChild(){
            return parent!=null&&this==parent.left;
        }
        @Override
        public boolean isRightChild(){
            return parent!=null&&this==parent.right;
        }
        public Node<E> sibling(){
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
