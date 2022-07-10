package hashMap;

import com.mj.Map;
import com.mj.TreeMap;
import com.mj.binarySearchTree.Comparator;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * @Author:ghq
 * @Date: 2021/12/31/21:55
 * @Description:使用hashtable实现一个hashMap
 */
public class HashMap<K,V> implements Map<K, V> {
    private static final boolean RED=false;
    private static final boolean BLACK=true;
    private int size;//这个size应当是bucket的size
    private Node<K,V>[] table ;//这个table中会存放每一颗红黑树的根节点
    private static final int DEFAULTCAPACITY=1<<4;
    private Comparator comparator;
    private static final float DEFAULT_LOAD_FACTOR=0.75f;//负载因子
    /**
     * hashtable竟然是将红黑树的根节点放在hashtable的bucket里
     * @return   因此我们hashtable的数组应该是Node数组
     */
    //构造函数为hashMap()要初始化table的大小
    public HashMap(){
        table=new Node[DEFAULTCAPACITY];//java不可以创建泛型数组
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public void clear() {
        if(size==0) {return;}
      size=0;
        /**
         * 由于我们table中的每个bucket中都放着红黑树的根节点，
         * 所以我们要清空这个表，需要将每个bucket存放的root节点置空
         */
        for (int i = 0; i < table.length; i++) {
            table[i]=null;
        }
    }
    public void resize(){
        //如果装填因子<=0.75
        if(1.0*size/table.length<DEFAULT_LOAD_FACTOR){
            return; }
        //保留以前的数据
        Node<K,V>[] oldTable=table;
        //进行扩容
        table=new Node[oldTable.length<<1];
        //使用层序遍历法，将旧的数据装填到新的表中\
        //************************非常低效的做法*************************
        //这种做法是每put一个数据，都会新new一个节点，完全没有利用原来老的红黑树
        //************************非常低效的做法****************************
        size=0;
        for (int i = 0; i < oldTable.length; i++) {
            if(oldTable[i]==null){return;}
            Queue queue=new LinkedList();
            queue.offer(oldTable[i]);
            while(!queue.isEmpty()) {
                Node<K,V> node = (Node<K, V>) queue.poll();
                if (node.left == null) {
                    queue.offer(node.left);}
                if (node.right == null) {
                    queue.offer(node.right);}
                //挪动代码得放到最后
                moveNode(node);
            }
        }
    }
    private void moveNode(Node<K,V> Newnode){
        //将旧的树上的每一个节点都挪动到新的树上去
        //将旧的节点的关系断掉
        Newnode.parent=null;
        Newnode.left=null;
        Newnode.right=null;
        //新加入的节点需要时红色的
        Newnode.color=RED;
        K k1=Newnode.key;
        V value=Newnode.value;
        int index=index(k1);
        Node<K,V> root=table[index];
        if(root==null){
            root=Newnode;
            //红黑树根节点一旦创建需要修复红黑树的性质
            table[index]=root;
            afterNode(Newnode);
            return;
        }
        //能来到这里root!=null,由于这里只是挪动，所以只用决定往左走还是往右走就好了
        //肯定没有equals的
        Node<K,V> parent=root;
        Node<K,V> node=root;
        int cmp=0;
        int h1=k1==null?0:k1.hashCode();
        Node<K,V> result=null;
        do{
            parent=node;
            K k2=node.key;
            int h2=node.hash;
            if(h1>h2){
                cmp=1;
            }else if(h1<h2){
                cmp=-1;
            }else if(k1!=null&&k2!=null
                    &&k1.getClass()==k2.getClass()
                    &&(k1 instanceof Comparable)
                    &&(cmp=((Comparable) k1).compareTo(k2))!=0){
                //这里做修改，cmp=0也不能说明是一个对象，不能进行覆盖，因为上面已经判断了两个对象不相等了
            }else{
                //能来到这里说明上面已经找过了search=true,并且没有找到，可直接比较内存为了避免重复查找，加bool值search做优化
                cmp=System.identityHashCode(k1)-System.identityHashCode(k2);
            }
            if(cmp>0){
                node=node.right;
            }else if(cmp<0){
                node=node.left;
            }
        }while(node!=null);
        if(cmp>0){
            parent.right=Newnode;
        }else{
            parent.left=Newnode;
        }
        Newnode.parent=parent;
        //加入节点后使得红黑树平衡
        afterNode(Newnode);

    }
    @Override
    public V put(K k1, V value) {
        //添加时需要判断节点的总数，进而计算出负载因子，然后判断是不是需要扩容
        resize();
        //首先我必须要拿到k的index(即生成key的哈希索引）
        int index=index(k1);
        Node<K,V> root=table[index];//取出table的index位置的root根节点
        if(root==null){
            root=new Node<K,V>(k1,value,null);
            //红黑树根节点一旦创建需要修复红黑树的性质
            table[index]=root;
            size++;
            afterNode(root);
            return root.value;
       }
        //能来到这里root!=null
        Node<K,V> parent=root;
        Node<K,V> node=root;
        int cmp=0;
        int h1=k1==null?0:k1.hashCode();
        Node<K,V> result=null;
        boolean searched=false;
        do{
            parent=node;
            K k2=node.key;
            int h2=node.hash;
            if(h1>h2){
                cmp=1;
            }else if(h1<h2){
                cmp=-1;
            }else if(Objects.equals(k1,k2)){
                cmp=0;
            }else if(k1!=null&&k2!=null
                  &&k1.getClass()==k2.getClass()
                  &&(k1 instanceof Comparable)
            &&(cmp=((Comparable) k1).compareTo(k2))!=0){
            //这里做修改，cmp=0也不能说明是一个对象，不能进行覆盖，因为上面已经判断了两个对象不相等了
            }
                else if(!searched){//意味着k1和k2中有一个为null,或者key不具有可比较性
                //这里的存放方法应该与查询方法对应，不可以比较内存地址
                //先扫描,然后根据内存地址来决定左右
                if(node.left!=null&&(result=node(node.left,k1))!=null
                ||node.right!=null&&(result=node(node.right,k1))!=null){
                    //已经存在这个key
                    node=result;
                    cmp=0;
                }else{
                    //没有找到
                    searched=true;
                    cmp=System.identityHashCode(k1)-System.identityHashCode(k2);
                }
            }else{
                //能来到这里说明上面已经找过了search=true,并且没有找到，可直接比较内存为了避免重复查找，加bool值search做优化
                cmp=System.identityHashCode(k1)-System.identityHashCode(k2);
            }
            if(cmp>0){
                node=node.right;
            }else if(cmp<0){
                node=node.left;
            }else{
                V oldValue=node.value;
                node.key=k1;
                node.value=value;
                return oldValue;
            }
        }while(node!=null);
        Node<K,V> newNode=new Node<>(k1,value,parent);
        if(cmp>0){
            parent.right=newNode;
        }else{
            parent.left=newNode;
        }
        //加入节点后使得红黑树平衡
        size++;
        afterNode(newNode);
        return null;
    }

    public int compare(K key, K key1, int keyHash, int key1Hash) {
        if (keyHash != key1Hash) {
            return keyHash - key1Hash;
        }
        //当 hash值相等得时候：当hashCode相等得时候比较两个值的内存地址是不是一样
        //当两个对象是一个内存地址，直接返回0：相等
        if (Objects.equals(key, key1)) {//key和key1都为null或者都不为空的情况
            return 0;
        }
        if (key != null && key1 != null) {//key和key1都不为空的情况
            String k1Class = key.getClass().getName();
            String k2Class = key.getClass().getName();
            int result = k1Class.compareTo(k2Class);
            if (result != 0) {//如果不是同一种类型，则将比较类型的String值得大小
                return result;
            }
            //同一种类型并且具备可比较性
            if (key instanceof Comparable) {
                return ((Comparable<K>) key).compareTo(key1);
            }
        }
        //同一种类型，哈希值相等，但是不具备可比较性
        // 或者key和key1之间又有一个是null
        //直接对象.hashCode()有可能是对象自己实现得hashCode,但是System.identityHashCode()肯定是根据内存地址得hashCode
        //如果同一种类型不具备可比较性，就会来到这
        return System.identityHashCode(key)-System.identityHashCode(key1);
    }
//    private void goIntoQueue(Node<K, V> node, Queue queue) {
//        if(node.hasTwoChildren()){
//            queue.offer(node.left);
//            queue.offer(node.right);
//        }else if(node.isLeaf()){
//
//        }else{
//            if (node.left == null) {
//                queue.offer(node.right);
//            } else {
//                queue.offer(node.left);
//            }
//        }
//    }

    //根据key生成对应的索引，也就是在桶数组的位置
    private int index(K k1){
        //为了让hash值更加均匀。使得hash值的高16位和低16位再混合一次
        return hash(k1)&(table.length-1);
    }

    private int index(Node<K,V> node){
        return (node.hash^(node.hash>>>16))&(table.length-1);
    }
    private int hash(K key){
        if(key==null) {return 0;}
        int hashCode=key.hashCode();
        int hash=hashCode^(hashCode>>>16);
        return hash;
    }
    private Node<K,V> node(K k1){
        Node<K,V> root=table[index(k1)];
        return root==null?null:node(root,k1);
    }
    //这个方法表示去node节点里面找k1
    public Node<K,V> node(Node<K,V> node,K k1){
        int h1=hash(k1);
        int cmp=0;
        Node<K,V> result=null;
        while(node!=null){
          K k2=node.key;
          int h2=node.hash;
          if(h1>h2){
              node=node.right;
          }else if(h1<h2){
              node=node.left;
          }else if(Objects.equals(k1,k2)){
              return node; }
          else if(k1!=null&& k2!=null
                   && k1.getClass()==k2.getClass()
                   && k1 instanceof Comparable
                   &&(cmp=((Comparable)k1).compareTo(k2))!=0){
              if(cmp>0){
                  node=node.right;
              }else {
                  node=node.left;
              }//这里有些矛盾，上面equals已经不相等了，但是cmp=0,依然返回了node,所以做出修改
          }else if(node.right!=null&&(result=node(node.right,k1))!=null){//key不具备可比较性，且有可能是null
              return result;
          }else if(node.left!=null&&(result=node(node.left,k1))!=null){
              return result;
          }else{ return null; }
        }
        return null; }

    @Override
    public V get(K key) {
        Node<K,V> node=node(key);
        return node!=null?node.value:null;
    }

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }
    @Override
    public V remove(K key){
        return remove(node(key));
    }

    public V remove(Node<K,V> node) {
        if (node == null) {return null;}
        V oldvalue=node.value;
        size--;
        //删除度为2的节点
        if (node.hasTwoChildren()) {
            Node<K,V> s = predesessor(node);
            node.key = s.key;
            node.value = s.value;
            node.hash=s.hash;
            node = s;
        }//删除度为0或度为1的节点
        Node<K,V> replacement = node.left != null ? node.left : node.right;
        int index=index(node);
        if (replacement != null) {
            node.parent = replacement.parent;
            //说明是度为1,度为1的节点也要分是不是有父节点
            if (node.parent == null) {
                table[index] = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right =replacement;
            }
            afterRemove(node,replacement);
        } else if (node.parent == null) {
            table[index] = null;
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
        Node<K,V> parent=node.parent;
        //删除的是根节点
        if(parent==null) {return;}
        //删除的是叶子节点
        //已经不能用sibling()去判断兄弟节点了，已经事实上node节点已经被清空了
        //   Node<E> brother=((RBNode<E>)node).sibling();
        //这是为了分情况，如果parent.left==null证明是普通的删除，如果是node.isLeftChild()证明是父节点下溢的那种删除，因为如果node是已经删掉的
        //因为如果node是已经删掉的，调用node.isLeftChild()永远都返回空
        boolean left=parent.left==null||node.isLeftChild();
        Node<K,V> sibling=left?parent.right:parent.left;

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
    public boolean containsValue(V value) {
      //使用层序遍历法，遍历这个树有没有value
        if(size==0) {return false;}
        //使用层序遍历法
        Queue queue=new LinkedList();
        for(int index=0;index<table.length;index++){
            if(table[index]==null){
                continue;
            }
            queue.offer(table[index]);
            while(!queue.isEmpty()){
                Node<K,V> node= (Node<K, V>) queue.poll();
                if(node.value==value){return true;}
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
        }
        return false;


    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        //使用层序遍历法，遍历这个树有没有value
        if(size==0||visitor==null) {return;}
        //使用层序遍历法
        Queue queue=new LinkedList();
        for(int index=0;index<table.length;index++){
            if(table[index]==null){
                continue;
            }
            queue.offer(table[index]);
            while(!queue.isEmpty()){
                Node<K,V> node= (Node<K, V>) queue.poll();
                if(visitor.visit(node.key,node.value)) {return;}
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
        }
    }
    //=======================修复红黑树性质的一些==========================
    protected void afterNode(Node<K,V>node){
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
    protected void rotateLeft(Node<K,V> grand){
        Node<K,V> parent=grand.right;
        Node<K,V> child=parent.left;
        grand.right=child;
        parent.left=grand;
        afterRotate(grand, parent, child);
    }

    protected void rotateRight(Node<K,V>grand){
        Node<K,V> parent=grand.left;
        Node<K,V>child=parent.right;
        grand.left= child;
        parent.right=grand;
        afterRotate(grand, parent, child);
    }


    protected void afterRotate(Node<K,V> grand, Node<K,V> parent, Node<K,V> child) {
        //树，一定要记得调整parent
        // parent成为子树的根节点,更新parent的parent
        //如果不是根节点，那么是它parent的左还是右
        parent.parent= grand.parent;
        if(grand.isLeftChild()){
            grand.parent.left= parent;
        }else if(grand.isRightChild()){
            grand.parent.right= parent;
        }else{
            table[index(grand)]=parent;
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
    private Node<K,V> color(Node<K,V> node, boolean color){
        if(node==null) {return node;}
        ((Node<K,V>)node).color=color;
        return node;
    }
    private Node<K,V> red(Node<K,V> node){
        return color(node,RED);
    }
    private Node<K,V> black(Node<K,V> node){
        return color(node,BLACK);
    }
    private boolean colorOf(Node<K,V> node){
        return node==null?BLACK:((Node<K,V>)node).color;
    }
    private boolean isBlack(Node<K,V> node){
        return colorOf(node)==BLACK;
    }
    private boolean isRed(Node<K,V> node){
        return colorOf(node)==RED;
    }
    private static class Node<K,V> {
        K key;
        V value;
        int hash;
        boolean color=RED;
        Node<K,V> left;
        Node<K,V> right;
        Node<K,V> parent;

        public Node(K key, V value, Node<K,V> parent) {
            this.key=key;
            this.value=value;
            this.parent = parent;
            int hash=key==null?0:key.hashCode();
            this.hash=hash^(hash>>>16);//对hash值进行扰动计算，使得hash值更加均匀
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
        public Node<K,V> sibling(){
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
