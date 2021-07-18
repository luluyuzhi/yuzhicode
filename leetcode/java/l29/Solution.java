package leetcode.l29;

public class Solution {

  /**
   * 两数相除,倍增法求两个数相除，不使用乘法、除法和mod运算
   *
   * @param dividend
   * @param divisor
   * @return
   */
  public int divide(int dividend, int divisor) {
    if (dividend == Integer.MIN_VALUE && divisor == -1) {
      return Integer.MAX_VALUE; // 溢出处理
    }
    int sign = 1;  // 最终结果的正负符号
    if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
      sign = -1;
    }
    // 将除数和被除数转换为负数
    dividend = dividend > 0 ? -dividend : dividend;
    divisor = divisor > 0 ? -divisor : divisor;
    // 都转换成负数，是因为int 的范围是[2^31, 2^31-1]，如果a是-2^32，转为正数时将会溢出
    if (dividend > divisor) {
      return 0;
    }
    int result = div(dividend, divisor);
    return sign == -1 ? -result : result;
  }

  /**
   * 倍增法大致思路： 举例：  11/3 首先11比3大，结果至少是1， 然后我让3翻倍，就是6，发现11比3翻倍后还要大，那么结果就至少是2了，那我让这个6再翻倍，得12，11不比12大，吓死我了，差点让就让刚才的最小解2也翻倍得到4了。
   * 但是我知道最终结果肯定在2和4之间。也就是说2再加上某个数，这个数是多少呢？我让11减去刚才最后一次的结果6，剩下5，我们计算5是3的几倍，也就是除法； 递归出了
   * <p>
   * 但是需要注意的是，我们将两个数都变为了负数，判断条件应该是 被除数  大于  除数的时候，返回0；
   *
   * @param dividend
   * @param divisor
   * @return
   */
  private int div(int dividend, int divisor) {
    if (dividend > divisor) {
      return 0;
    }
    int count = 1; // 最小结果为1
    int tb = divisor;
    // while循环结束会找到翻倍后大于被除数的那个数
    while (((tb + tb) > dividend) && (tb + tb) < 0) {
      count = count + count;
      tb = tb + tb;
    }
    return count + div(dividend - tb, divisor);
  }
}
