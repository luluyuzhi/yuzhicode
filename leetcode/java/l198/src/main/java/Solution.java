public class Solution {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        //定义状态dp数组
        int[] dp = new int[len];
        //边界值
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        //转移方程
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[len - 1];
    }

    public static void main(String[] args) {

    }
}
