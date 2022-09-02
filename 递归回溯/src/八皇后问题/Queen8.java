package 八皇后问题;

/**
 * @Author:ghq
 * @Date: 2022/08/19/14:57
 * @Description
 */
public class Queen8 {
    //如何记录一个格子里是否有皇后，一种方式是queen[row]=col
    //另一种方式是使用三个boolean数组
    int[] queen;//queen[row]=col:表示在row行col列有皇后
    int ways;
    boolean[] cols;//这个用来记录某一列上是不是有皇后cols[col]=true表示这一行有皇后；继续优化，直接将一个boolean[8]优化为一个byte
    boolean[] rightTop;//用两个字节取代rightTop和leftTop
    boolean[] lefttTop;
    public static void main(String[] args) {
        new Queen8().NQueen(4);
        System.out.println(Double.SIZE);//Integer4个字节  Byte 1个字节.Double 8个字节
        System.out.println();
    }
    public void NQueen(int n){
        if(n<1) {return;}
        queen=new int[n];
        cols=new boolean[(n<<1)-1];
        rightTop=new boolean[(n<<1)-1];
        lefttTop=new boolean[(n<<1)-1];
        //然后一行一行添加皇后
           place(0);
        System.out.println("一共有"+ways+"方法");

    }
    public void place(int row){
        if(row== queen.length){
            ways++;
            show();
            System.out.println();
            return;
        }
         for(int col=0;col<queen.length;col++){
             //往这一行放皇后，看看这一行能不能放
             if(cols[col]){//说明这一列有皇后
                 continue; }
             if(rightTop[row+col])
             {continue;}
             if(lefttTop[row-col+ queen.length-1])
             {continue;}
             cols[col]=true;
             rightTop[row+col]=true;
             lefttTop[row-col+ queen.length-1]=true;
             queen[row]=col;
             place(row+1);
             cols[col]=false;
             rightTop[row+col]=false;
             lefttTop[row-col+ queen.length-1]=false;

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
//上面这个方法要有优化，没必要每次都循环上面的row一遍

    //show方法，打印出当前皇后的排放情况
    public void show(){
        for (int row = 0; row <queen.length; row++) {
            for (int col=0;col<queen.length;col++){
                if(queen[row]==col){
                    System.out.print("1  ");

                }else{
                    System.out.print("0  ");
                }
            }
            System.out.println("");
        }
    }
}
