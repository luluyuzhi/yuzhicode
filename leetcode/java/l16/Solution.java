package l16;


import java.util.Arrays;

/**
 * 最接近的三树之和
 */
public class Solution {

  public int threeSumClosest(int[] nums, int target) {
    int n = nums.length;
    Arrays.sort(nums);
    int best = 1000000;
    // 保证每次枚举的值 与 上一次的都有所不同
    for (int i = 0; i < n; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      int j = i + 1;
      int k = n - 1;

      while (j < k) {
        int sum = nums[i] + nums[j] + nums[k];
        if (sum == target) {
          return target;
        }
        if (Math.abs(sum - target) < Math.abs(best - target)) {
          best = sum;
        }
        if (sum > target) {
          int temp_k = k - 1;
          while (j < temp_k && nums[temp_k] == nums[k]) {
            temp_k--;
          }
          k = temp_k;
        } else {
          int temp_j = j + 1;
          while (temp_j < k && nums[temp_j] == nums[j]) {
            temp_j++;
          }
          j = temp_j;
        }
      }

    }
    return best;
  }

  public static void main(String[] args) {
    System.out.println(new Solution().threeSumClosest(new int[]{-3, -2, -5, 3, -4}, -1));
  }
}
