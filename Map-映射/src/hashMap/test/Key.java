package hashMap.test;

import java.util.Objects;

/**
 * @Author:ghq
 * @Date: 2022/01/09/0:05
 * @Description
 */
public class Key {
    int value;
    public Key(int value){
        this.value=value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Key key = (Key) o;
        return value == key.value;
    }

    @Override
    public int hashCode() {
        return value/10;
    }
}
