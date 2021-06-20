package leetcode.l38;

public class Solution {

  // l38 外观数组

  /**
   * 外观数组就是对上一个字符串的表述，举个例子：
   * n      表述的字符串表述
   * 1.     1
   * 2.     11
   * 3.     21
   * 4.     1211
   * 5.     111221
   * 第一项是数字 1
   * 描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
   * 描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
   * 描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
   * 描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
   * ===============
   * 要描述 一个数字字符串，首先要将字符串分割为 最小 数量的组，每个组都由连续的最多 相同字符 组成。
   * 然后对于每个组，先描述字符的数量，然后描述字符，形成一个描述组。
   * 要将描述转换为数字字符串，先将每组中的字符数量用数字替换，再将所有描述组连接起来
   *
   * =================
   * 本例使用的方法是，当前元素与后一个元素进行比对，如果相同，计数+1；
   * 如果不同或者超出字符串长度范围，就拼接  计数  字符
   * @param n
   * @return 返回字符串
   */
  public String countAndSay(int n) {
    if (n == 1) {
      return "1";
    } else {
      String str = countAndSay(n - 1); // 取出前一个描述字符串
      // 对这个字符串进行描述
      StringBuffer sb = new StringBuffer();
      char[] chars = str.toCharArray();
      int count = 1;
      for (int i = 0; i < chars.length; i++) {
        if (i + 1 >= chars.length) {
          sb.append(count);
          sb.append(chars[i]);
          break;
        }
        if (chars[i] != chars[i + 1]) {
          sb.append(count);
          sb.append(chars[i]);
          count = 1;
        } else {
          count++;
        }
      }
      return sb.toString();
    }
  }
}
