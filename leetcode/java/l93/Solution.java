package l93;

import java.util.ArrayList;
import java.util.List;

/**
 * . 复原IP地址
 */
public class Solution {

  static final int SEG_COUNT = 4;
  List<String> ans = new ArrayList<>(); // 记录所有IP地址的可能
  int[] segments = new int[SEG_COUNT]; // ip地址4段

  public List<String> restoreIpAddresses(String s) {
    dfs(s, 0, 0);
    return ans;
  }

  public void dfs(String s, int segId, int segStart) {
    // 如果找到了4段IP地址并且遍历完了字符串，那么就是一种答案
    if (segId == SEG_COUNT) {
      //字符串遍历到末尾了
      if (segStart == s.length()) {
        StringBuffer ipAddr = new StringBuffer();
        for (int i = 0; i < SEG_COUNT; i++) {
          ipAddr.append(segments[i]);
          if (i != SEG_COUNT - 1) {
            ipAddr.append(".");
          }
        }
        ans.add(ipAddr.toString());
      }
      //当4段ip地址都找到了，但是字符串没有遍历完成，那么就回溯
      return;
    }
    // 如果还没找到4段ip地址就已经遍历完了字符串，那么就提前回溯
    if (segStart == s.length()) {
      return;
    }
    // 由于不能有前导零，如果当前数字为0，那么这一段ip地址只能为0
    if (s.charAt(segStart) == '0') {
      segments[segId] = 0;
      dfs(s, segId + 1, segStart + 1);
    }
    // 一般情况下，枚举每一种可能性并递归
    int addr = 0;
    for (int segEnd = segStart; segEnd < s.length(); segEnd++) {
      addr = addr * 10 + (s.charAt(segEnd) - '0');
      if (addr > 0 && addr <= 0xFF) {// 0xFF = 255
        segments[segId] = addr;
        dfs(s, segId + 1, segEnd + 1);
      } else {
        break;
      }
    }
  }

  public static void main(String[] args) {
    System.out.println("XXXX");
  }
}
