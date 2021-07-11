package leetcode.l413;

public class Solution {

  /**
   * 求等差子数组的个数
   * @param nums
   * @return 返回等差子数据的个数
   */
  public int numberOfArithmeticSlices(int[] nums) {
    if(nums==null || nums.length<=2){
      return 0;
    }
    int length = nums.length;
    /*
    定义dp数组，dp[i] 表示以数组中i位置元素为结尾的等差子数组的个数
    边界值： dp[0] = dp[1] = 0
    子问题： 当 nums[i]-nums[i-1] == num[i-1]-num[i-2]时：
            dp[i] = dp[i-1]+1
     */
    int[] dp = new int[length];
    for (int i = 2; i <length; i++) {
      if(nums[i]-nums[i-1] == nums[i-1]-nums[i-2]){
        dp[i] = dp[i - 1] + 1;
      }
    }
    int result = 0;
    for (int i = 0; i < length; i++) {
      result += dp[i];
    }
    return result;
  }

}
