public class Solution {

    /*
    给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。

    子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。

     
    示例 1：

    输入：nums = [10,9,2,5,3,7,101,18]
    输出：4
    解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
    示例 2：

    输入：nums = [0,1,0,3,2,3]
    输出：4
    示例 3：

    输入：nums = [7,7,7,7,7,7,7]
    输出：1

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //todo 解题思路：
    /*
    设 dp[i] 为 以 nums[i] 为结尾的最长递增子序列的长度
    dp[i] = max(  dp[j]+1 ,当 0<j<i 且 nums[i] > nums[j]   )
    dp[0] = 1

    dp[i] 以 第i个数结尾， 连续递增的子序列最少为1（即本身）
     */


    public int lengthOfLIS(int[] nums) {
        if (nums==null || nums.length==0){
            return 0;
        }
//        设 dp[i] 为 以 nums[i] 为结尾的最长递增子序列的长度
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxLen = 1;
        for (int i = 1; i < nums.length; i++) {
            // 设置初始值
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                {
                    //转移方程   dp[i] = max(  dp[j]+1 ,当 0<j<i 且 nums[i] > nums[j]   )
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    public static void main(String[] args) {

    }
}
