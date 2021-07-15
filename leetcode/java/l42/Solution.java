package leetcode.leetcode.l42;

import java.util.LinkedList;

public class Solution {

  public int trap(int[] height) {
    // 使用单调栈进行求解; 栈低到栈顶逐渐递减
    if (height == null || height.length == 0) {
      return 0;
    }
    //栈中存放每个柱子的下标，单调栈按照每个柱子的高度进行约束
    LinkedList<Integer> stack = new LinkedList<>();
    // 定义接水量
    int result = 0;
    for (int i = 0; i < height.length; i++) {
      while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
        int top = stack.pop();
        if (stack.isEmpty()) {
          //弹出栈顶元素之后，整个栈的空间大小为0，则此时无法接水
          break;
        }
        int left = stack.peek();
        int currentWidth = i - left - 1;
        // 当前长度为 top两侧最小值 减去 top的长度
        int currentHeight = Math.min(height[left], height[i]) - height[top];
        result += currentWidth * currentHeight;
      }
      stack.push(i);
    }
    return result;
  }

}
