package 组合综合;

import java.util.ArrayList;
import java.util.List;

/**
 给你一个 无重复元素 的整数数组candidates 和一个目标整数target，找出candidates中可以使数字和为目标数target 的 所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。

 candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。

 对于给定的输入，保证和为target 的不同组合数少于 150 个。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/combination-sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/**搜索回溯
 * 对于这类寻找所有可行解的题，我们都可以尝试用【搜索回溯】的方法来解决
 *
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
         List<List<Integer>> ans=new ArrayList<>();
         List<Integer> combine=new ArrayList<>();
         recursion(candidates,target,ans,combine,0);
         return ans;
    }
    public void recursion(int[] candidates,int target,List<List<Integer>> ans,List<Integer> combine,int idx){
        //表示当前递归在candidates 数组的第idx 位
           if(idx==candidates.length){
               return;
           }
           if(target==0){
               ans.add(new ArrayList<>(combine));
               return;
           }
           //从第一个位置开始
        recursion(candidates,target,ans,combine,idx+1);
           //选择当前数
        if(target-candidates[idx] >= 0){
            combine.add(candidates[idx]);
            recursion(candidates,target-candidates[idx],ans,combine,idx);
            combine.remove(combine.size()-1);
        }
    }
}
