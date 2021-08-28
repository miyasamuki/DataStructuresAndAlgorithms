public class ArrayList {
    private static final int DEFAULT_CAPACITY=10;
    private int[] elements;
    int size;
    public ArrayList(int capacity) {
        capacity=(capacity<DEFAULT_CAPACITY)?DEFAULT_CAPACITY:capacity;
        elements=new int[capacity];

    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }
    public int get(int index){
        if(index>size||index<0){
            throw new IndexOutOfBoundsException("index"+index+"size"+size);
        }
        return elements[index];

    }
    //添加的时候如何对数组扩容
    public void add(int element){
           add(size,element);
    }
    //扩容，先写一个ensureCapacity(),保证容量，
    private void ensureCapacity(int capacity){
        int oldCapacity=elements.length;
        if(oldCapacity>=capacity) return;
        //新容量为旧的容量的1.5被
        int newCapacity=oldCapacity+oldCapacity>>1;
        int[] newElements=new int[newCapacity];
        for(int i=0;i<size;i++){
            newElements[i]=elements[i];
        }
        elements=newElements;//数组的指针变换，这样以前的数组就可以被回收
    }
    //在某个位置添加元素
    public void add(int index,int element){
        rangeCheckForAdd(index);
        ensureCapacity(size+1);
        for(int i=size-1;i>=index;i--){
            elements[i+1]=elements[i];
        }
        size++;
        elements[index]=element;
    }

//删除元素，就是删掉那个被删掉的，然后其他元素往前挪动
    public int remove(int index){
        for(int i=index+1;i<=size-1;i++){
            elements[i-1]=elements[i];
            //将后面的整个挪过来
        }
        size--;
       // elements[size]=null;将int改为泛型就可以改变这个问题

        return 0;
    }

    public String toString(){

        StringBuilder string=new StringBuilder();

        return string.toString();
    }
    //检查是否有数组越界
    private void outOfBound(int index){
        throw new IndexOutOfBoundsException("index"+index+"size"+size);
    }
    private void rangeCheck(int index){
        if(index<0||index>=size){
            outOfBound(index);
        }
    }
    private void rangeCheckForAdd(int index){
        if(index<0||index>size){
            outOfBound(index);
        }

    }
}
