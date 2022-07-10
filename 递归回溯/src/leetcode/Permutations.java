package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @Author:ghq
 * @Date: 2022/06/03/22:43
 * @Description
 */
public class Permutations {
    public static void main(String[] args) {
       Permutations solution=new Permutations();
        System.out.println(solution.permute(new int[]{1,2,3}));
    }


    public List<List<Integer>> permute(int[] nums) {
      int depth=0;//用于判断是否回溯
      List<List<Integer>> res=new ArrayList<>();
      //双端队列,这个实际上就是将双端队列当作栈来使用，后进后出
      Deque path=new ArrayDeque();
      boolean[] used=new boolean[nums.length];
      recursion(nums,depth,used,path,res);
      return res;


    }
    private void recursion(int[]nums,int depth,boolean[] used,Deque path,List<List<Integer>> res){
        if(depth== nums.length){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(used[i]){
                continue;
            }
            path.addLast(nums[i]);
            used[i]=true;
            recursion(nums,depth+1,used,path,res);
            path.removeLast();
            used[i]=false;
        }
    }

}
