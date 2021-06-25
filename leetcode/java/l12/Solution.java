package leetcode.l12;

public class Solution {

  /**
   * 输入一个数字，转换成罗马字符串 1 <= num <= 3999  邪恶的一笑！！！ 直接采用数字硬编码，简单粗暴，还快！！！
   *
   * @param num
   * @return
   */
  public String intToRoman(int num) {
    StringBuffer roman = new StringBuffer();
    String[] qianfenwei = new String[]{"", "M", "MM", "MMM"};
    String[] baifenwei = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String[] shifenwei = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String[] gefenwei = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    roman.append(qianfenwei[num / 1000]);
    roman.append(baifenwei[num % 1000 / 100]);
    roman.append(shifenwei[num % 1000 % 100 / 10]);
    roman.append(gefenwei[num % 1000 % 100 % 10 / 1]);
    return roman.toString();
  }
}
