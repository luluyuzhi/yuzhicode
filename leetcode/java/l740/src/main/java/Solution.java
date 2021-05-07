public class Solution {
    public static int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }

        // all数组构造完毕
        int maxvalue = nums[0];
        for (int num : nums) {
            maxvalue = Math.max(maxvalue, num);
        }

        int[] all = new int[maxvalue + 1];
        for (int num : nums) {
            all[num]++;
        }

        int[] dp = new int[maxvalue + 1];
        dp[0] = 0;
        dp[1] = all[1] * 1;

        // 转移方程 dp[i] = Math.max(dp[i-1], dp[i-2]+i*all[i])
        for (int i = 2; i < maxvalue + 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + i * all[i]);
        }
        return dp[maxvalue];
    }

    public static void main(String[] args) {
        System.out.println(deleteAndEarn(new int[]{2, 3, 3, 4}));
    }
}
