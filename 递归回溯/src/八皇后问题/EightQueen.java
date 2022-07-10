package 八皇后问题;

/**
 * @Author:ghq
 * @Date: 2022/05/30/10:58
 * @Description
 */
public class EightQueen {
    public static void main(String[] args) {
        new EightQueen().eightQueen(8);
    }

    //难点一：棋盘怎么表示，比如哪些格子能走，这个格子怎么表示：可以搞一个内部类。x,y
              //或者搞一个数组 queens[row]=cols :如果有内容，就表示这个[row,cols]里面有皇后
    //难点二：回溯怎么表示：递归就可以表示回溯
    //难点三：斜线怎么表示  （cols2-cols1)/(row2-row1)=+1或者-1；是不是就可以表示在同一斜线上了
    int[] queen;
    int way;
    public void eightQueen(int n) {
        //首先8个皇后放到8X8的棋盘上，又彼此不在攻击范围，则必是每一行有一个皇后
        if(n<1){return;}

        queen=new int[n];
        place(0,n);
    }
    public void place(int row,int n){
        if(row== queen.length){
            way++;
            show(way);
            return;
        }
        for (int col = 0; col < queen.length; col++) {
            if(isValid(row,col,n)){
                queen[row]=col;
                cols=(byte)(cols|(1<<col));//按位或的运算出来之后是int,所以要强转位byte
                topRight=(short) (topRight|(1<<(row+col)));
                topLeft=(short) (topLeft|(1<<(col-row+n-1)));
                place(row+1,n);
                //要还原，比如说要将第三位cols中的1还原为0，就&11111011，
                //那么怎么获得11111011呢，对00000100取反就好
                cols &=~(1<<col);
                topRight &=~(1<<(row+col));
                topLeft &=~(1<<(col-row+n-1));

            }

        }
    }
    //N皇后问题的优化,用一个cols数组来记录，如果这一列有queen,值就为true,否则就为false
    byte cols;
    //向左的斜线也是这个思路，怎么标记每根斜线呢，可以是col-row+n-1
    short topLeft;
    //向右的斜线也是这个思路，怎么标记每根斜线呢，就是col+row
    short topRight;
    public boolean isValid(int row,int col,int n){
       //那么我要如何判断一个位置是不是isValid呢
        //当然是根据queen[]中的元素，如果和queen[]中的任何一个元素处在同一行同一列同一斜线，都不算isValid()
              if((cols&(1<<col))!=0){//其实就是判断cols的col列是0还是1
                  System.out.println("["+row+"]"+"["+col+"] = false");
                  return false;
              }
              if((topRight&(1<<(row+col)))!=0){
                  System.out.println("["+row+"]"+"["+col+"] = false");
                  return false;
              }
              if((topLeft&(1<<(col-row+n-1)))!=0){
                  System.out.println("["+row+"]"+"["+col+"] = false");
                  return false;
               }
        System.out.println("["+row+"]"+"["+col+"] = true");
        return true;
    }
    //如何显示摆放情况，自然是拿到queen,我怎么没想到呢
    public void show(int way){
        System.out.println("第"+way+"种方法");
        for (int row = 0; row < queen.length; row++) {
            for (int col = 0; col < queen.length; col++) {
                if(queen[row]==col){
                    System.out.print(" 1 ");
                }else{
                    System.out.print(" 0 ");
                }
            }
            System.out.println();
        }

    }
}
