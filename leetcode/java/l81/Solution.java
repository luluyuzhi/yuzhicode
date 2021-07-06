package leetcode.l81;

public class Solution {

  /**
   * 搜索旋转排序数组 II 这道题与l33的题唯一的却别在于，数组中的数可能会有重复数据 在进行二分查找的时候，mid 左右区间边界可能会有重复数字，无法直接判断区间是否有序
   * 针对这种情况，我们对 区间左边界 + 1， 区间右边界 - 1； 直到区间两侧的边界值不相等为止
   *
   * @param nums
   * @param target
   * @return 如果存在就返回true， 如果不存在就返回false
   */
  public boolean search(int[] nums, int target) {
    boolean flag = false;
    int n = nums.length;
    if (n == 0) {
      return flag;
    }
    if (n == 1) {
      return nums[0] == target ? true : false;
    }

    int l = 0;
    int r = n - 1;
    while (l <= r) {
      int mid = l + (r - l) / 2;
      if (nums[mid] == target) {
        flag = true;
        break;
      }
      if (nums[l] == nums[mid] && nums[mid] == nums[r]) {
        l++;
        r--;
      } else {
        if (nums[l] <= nums[mid]) {
          if (nums[l] <= target && target < nums[mid]) {
            r = mid - 1;
          } else {
            l = mid + 1;
          }
        } else {
          if (nums[mid] < target && target <= nums[n - 1]) {
            l = mid + 1;
          } else {
            r = mid - 1;
          }
        }
      }
    }
    return flag;
  }

}
