package leetcode.l3;

import java.util.HashSet;
import java.util.Set;

public class Solution {

  /**
   * 无重复字符的最长子串
   * @param s
   * @return
   */
  public int lengthOfLongestSubstring(String s) {
    Set<Character> set =new HashSet<>();
    int rk = -1;  // 记录右指针
    int ans = 0;  //记录无重复字符的连续子串的长度
    int length = s.length();
    // 如果 i 到 rk 没有重复字符， 那么 i+1  到  rk 一定没有重复字符串
    // 如果 i 到 rk 有重复字符，那么rk保持不动，set中持续删除i-1所指的元素
    for(int i=0;i<length;i++)
    {
      if(i!=0)
      {
        set.remove(s.charAt(i-1));
      }
      // 始终保持访问rk右指针的后一个元素
      while(rk+1<length&&!set.contains(s.charAt(rk+1)))
      {
        set.add(s.charAt(rk+1));
        rk++;
      }
      ans = Math.max(ans, rk-i+1);
    }
    return ans;
  }
}
