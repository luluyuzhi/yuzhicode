package leetcode.l46;

import java.util.ArrayList;
import java.util.List;

public class Solution {

  /**
   * 数组中数字的全排列
   * @param nums
   * @return List<List<Integer>> res
   */
  public List<List<Integer>> permute(int[] nums){
    int len = nums.length;
    if (len==0){
      return null;
    }
    List<List<Integer>> res = new ArrayList<>();
    ArrayList<Integer> path = new ArrayList<>();
    boolean[] used = new boolean[len];
    dfs(nums, len, 0, path, used, res);
    return res;
  }

  /**
   * 深度遍历整个数组
   * @param nums  待全排列的数组
   * @param len   数组的长度
   * @param depth 当前路径的深度
   * @param path  记录当前遍历所经历的路径
   * @param used  记录数组中当前状态下的元素是否已经被遍历了
   * @param res   记录最终的全排列结果
   */
  private void dfs(int[] nums, int len, int depth, ArrayList<Integer> path, boolean[] used, List<List<Integer>> res) {
    if (depth==len){
      // 需要创建一个新对象来存储path，防止path回溯的时候，引用不变，导致res中的结果会发生变化
      res.add(new ArrayList<>(path));
      return;
    }
    for(int i=0; i<len;i++){
      // 判断数组中的元素是否被遍历了
      if (used[i]){
        continue;
      }
      path.add(nums[i]);
      used[i] = true;
      dfs(nums, len, depth+1, path, used, res);
      // 回溯，状态归位
      path.remove(path.size()-1);  // 移除最后一个元素
      used[i] = false;                  // 访问状态恢复初始值
    }
  }








}
