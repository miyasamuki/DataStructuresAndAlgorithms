package hashMap.test;

/**
 * @Author:ghq
 * @Date: 2022/01/16/16:05
 * @Description
 */
public class Asserts {
    public static void test(boolean val){
        try {
            if (!val) {
                System.out.println("测试未通过");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
