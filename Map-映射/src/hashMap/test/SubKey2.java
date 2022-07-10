package hashMap.test;

/**
 * @Author:ghq
 * @Date: 2022/01/16/15:32
 * @Description
 */
public class SubKey2 extends Key{
    public SubKey2(int value) {
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
