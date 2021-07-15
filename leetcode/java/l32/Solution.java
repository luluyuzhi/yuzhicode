package leetcode.l32;

import java.util.LinkedList;

public class Solution {

  /**
   * 最长有效括号长度
   * @param s
   * @return
   */
  public int longestValidParentheses(String s) {
    if(s==null || s.length()==0){
      return 0;
    }
    int maxLen=0;
    // 定义记录括号下标的 栈
    LinkedList<Integer> stack = new LinkedList<>();
    // 初始栈顶为-1
    stack.push(-1);
    //循环遍历整个字符串，到字符为（ 的时候，位置下标入栈； 当栈顶为 ）的时候，如果栈不为空，就先出栈，知道栈为空后，再将这个 ） 下标入栈
    // 每次出栈，如果栈不为空，那么就记录出栈后的栈顶元素下标与当前要入栈的元素的下标差值
    for(int i=0;i<s.length();i++){
      char current = s.charAt(i);
      if(current=='('){
        stack.push(i);
      }else{
        stack.pop();
        if(stack.isEmpty()){
          stack.push(i);
        }else{
          int gap = i - stack.peek();
          maxLen = Math.max(maxLen, gap);
        }
      }
    }
    return maxLen;
  }
}
