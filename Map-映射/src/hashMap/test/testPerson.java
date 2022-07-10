package hashMap.test;

import com.mj.Map;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import hashMap.HashMap;


/**
 * @Author:ghq
 * @Date: 2022/01/06/0:22
 * @Description
 */
public class testPerson {
    public static void main(String[] args) {
        Person p1=new Person(16,23,"小黄");
        Person p2=new Person(16,24,"小黄");
        Map map=new HashMap();
        map.put(p1,1);
        map.put(p2,1);
        map.put("ck",2);
        map.put("ck",3);
        map.put("ck2",3);
        map.put(null,6);
        System.out.println(map.size());
        map.traversal(new Map.Visitor() {
            @Override
            public boolean visit(Object key, Object value) {
                System.out.println(key+":"+value+";");
                return false;
            }
        });
    }
}
