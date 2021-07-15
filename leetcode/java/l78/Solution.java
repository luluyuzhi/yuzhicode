package leetcode.l78;

import java.util.ArrayList;
import java.util.List;

public class Solution {
  public List<List<Integer>> subsets(int[] nums) {
    // 采用回溯方解决此问题
    ArrayList<List<Integer>> list = new ArrayList<>();
    ArrayList<Integer> path = new ArrayList<>();
    dfs(nums, 0, nums.length, path, list);
    return list;
  }
  public void dfs(int[] nums, int begin, int len, ArrayList<Integer> path, ArrayList<List<Integer>> res){
    res.add(path);
    for(int i=begin;i<len;i++){
      path.add(nums[i]);
      dfs(nums, i+1, len, path, res);
      // 回溯, 移除最后一个元素（通过索引进行移除）
      path.remove(path.size()-1);
    }
  }

  public static void main(String[] args) {
    new Solution().subsets(new int[]{1,2,3});
  }
}
