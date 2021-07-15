package leetcode.l1884;

public class Solution {

  /**
   * 鸡蛋掉落-两枚鸡蛋
   *
   * @param n
   * @return
   */
  public int twoEggDrop(int n) {
    int x = 1;
    int sum = 0;
    while (sum < n) {
      sum += x;
      if (sum >= n) {
        break;
      }
      x++;
    }
    return x;
  }

}
