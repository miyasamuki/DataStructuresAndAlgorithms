package hashMap.test;

/**
 * @Author:ghq
 * @Date: 2022/01/16/15:28
 * @Description
 */
public class SubKey1 extends Key{
    public SubKey1(int value) {
        super(value);
    }
    @Override
    public boolean equals(Object obj){
        if(obj==this) {return true;}
        if(obj==null||obj.getClass()!=SubKey1.class
        &&obj.getClass()!=SubKey2.class){
            return false;
        }
        return ((Key)obj).value==value;
    }
}
