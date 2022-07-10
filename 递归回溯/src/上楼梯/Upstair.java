package 上楼梯;

/**
 * @Author:ghq
 * @Date: 2022/05/29/10:31
 * @Description
 */
public class Upstair {
    public static void main(String[] args) {
      new Upstair().hanota(3,"A","B","C");
    }
    public void hanota(int n,String p1,String p2,String p3){
        if(n<=1){
            move(n,p1,p3);
            return;
        }
        hanota(n-1,p1,p3,p2);
        move(n,p1,p3);
        hanota(n-1,p2,p1,p3);
    }


    public void move(int n,String from,String to){
        System.out.println("将"+n+"号元素"+"从"+from+"移动到"+to);
    }
}
