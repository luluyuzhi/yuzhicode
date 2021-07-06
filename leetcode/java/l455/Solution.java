package leetcode.l455;

import java.util.Arrays;

public class Solution {

  /**
   * ********分配饼干******** 排序+贪心 每次找到能够满足 胃口最小的那个孩子的饼干，然后继续处理下一个孩子。每次都满足胃口最小的那个孩子
   *
   * @param g g[i]表示第i个孩子的胃口值
   * @param s s[i]表示第i块饼干的尺寸
   * @return 返回能够喂饱孩子的数量
   */
  public int findContentChildren(int[] g, int[] s) {
    int count = 0;
    int childers = g.length;
    int cookies = s.length;
    // 孩子的胃口值进行排序
    Arrays.sort(g);
    // 饼干的尺寸值进行排序
    Arrays.sort(s);
    for (int i = 0, j = 0; i < childers && j < cookies; i++, j++) {
      // todo 找到饼干尺寸大于孩子i的胃口
      while (j<cookies&&g[i]>s[j]){
        j++;
      }
      if (j<cookies){
        count++;
      }
    }
    return count;
  }
}
