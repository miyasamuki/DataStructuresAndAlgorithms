package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @Author:ghq
 * @Date: 2022/06/13/11:04
 * @Description
 */
public class Permutations2 {
    public static void main(String[] args) {
        int[] nums={1,1,2};
        Permutations2 p2=new Permutations2();
        System.out.println(p2.permute(nums));
    }
    public List<List<Integer>> permute(int[] nums){
        List<List<Integer>> resultList=new ArrayList<>();
        Deque<Integer> deque=new ArrayDeque<>();
        boolean[] used=new boolean[nums.length];
        recursion(nums,deque,used,resultList);
        return resultList;

    }
    //list是正在循环的顺序，used是循环过的顺序，resultList是最后返回
    public void recursion(int[] nums, Deque<Integer> deque, boolean[] used, List<List<Integer>> resultList){
        if(deque.size()== nums.length&&!resultList.contains(new ArrayList<>(deque))){
            resultList.add(new ArrayList<>(deque));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(used[i]){
                continue;
            }
            deque.add(nums[i]);
            used[i]=true;
            recursion(nums, deque, used, resultList);
            deque.remove(nums[i]);
            used[i]=false;
        }
    }

}
