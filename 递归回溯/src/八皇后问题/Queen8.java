package 八皇后问题;

/**
 * @Author:ghq
 * @Date: 2022/08/19/14:57
 * @Description
 */
public class Queen8 {
    int[] queen;//queen[row]=col:表示在row行col列有皇后
    int ways;
    public static void main(String[] args) {
        new Queen8().eightQueen(8);
    }
    public void eightQueen(int n){
        queen=new int[n];
        //然后一行一行添加皇后

           place(0,n);

    }
    public void place(int row,int n){
        if(row== queen.length){
            ways++;
            return;
        }
         for(int col=0;col<n;col++){
             //往这一行放皇后，看看这一行能不能放
             if(isValid(row,col)){
                queen[row]=col;
                place(row+1,n);
             }
         }
    }
    public boolean isValid(int row,int col){
        for(int i=0;i<row;i++){
        //判断行
        if(queen[i]==col){
         //表示这一列已经有皇后
            return  false;
        }
        //判断斜线上是不是有皇后
        if(row-i==Math.abs(col-queen[i])){
            return false;
        }

        }
        return true;

}
}
