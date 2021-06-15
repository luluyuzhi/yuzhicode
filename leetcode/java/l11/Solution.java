package leetcode.l11;

public class Solution {

  //盛最多水的容器
  public int maxArea(int[] height) {
    //头尾双指针， 向中间移动，每次只移动元素最小的那边，然后求 小元素X间距，求得最大值
    if (height == null || height.length == 0) {
      return 0;
    }
    int maxarea = 0;
    int length = height.length;
    int left = 0, right = length - 1;
    while (left < right) {
      int area = 0;
      //找出最小的一边
      if (height[left] <= height[right]) {
        //如果左边小
        area = height[left] * (right - left);
        left++;
      } else {
        area = height[right] * (right - left);
        right--;
      }
      maxarea = Math.max(maxarea, area);
    }
    return maxarea;
  }
}
