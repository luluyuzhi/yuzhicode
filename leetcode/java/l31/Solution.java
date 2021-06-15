package leetcode.l31;

public class Solution {


  public void nextPermutation(int[] nums) {
    int i = nums.length - 2;
    while (i >= 0 && nums[i] >= nums[i + 1]) {
      i--;
    }
    if (i >= 0) {
      int j = nums.length - 1;
      while (j >= 0 && nums[i] >= nums[j]) {
        j--;
      }
      //找到较小的数  和   较大的数
      swap(nums, i, j);
    }
    reverse(nums, i + 1);
  }

  /**
   * 交换数组中两个位置的元素
   *
   * @param nums
   * @param i
   * @param j
   */
  public void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  /**
   * 翻转数组中从start开始剩余元素的位置
   *
   * @param nums
   * @param start
   */
  public void reverse(int[] nums, int start) {
    int left = start;
    int right = nums.length - 1;
    while (left < right) {
      swap(nums, left, right);
      left++;
      right--;
    }
  }
}
