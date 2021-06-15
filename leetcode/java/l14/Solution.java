package leetcode.l14;

public class Solution {

  /**
   * 求给定数组中所有字符串的最长公共前缀
   * 采用纵向扫描的方式
   *
   * @param strs 输入的字符串数组
   * @return 返回字符串数组中最长公共前缀
   */
  public String longestCommonPrefix(String[] strs) {

    if(strs==null || strs.length==0)
    {
      return "";
    }
    int strCount = strs.length;
    int length = strs[0].length();
    //遍历第一个字符串，以第一个字符串为基准
    for (int i = 0; i < length; i++) {
      char tempChar = strs[0].charAt(i);
      //从第二个字符串开始，一次遍历
      for (int j=1;j<strCount;j++)
      {
        if (i==strs[j].length() || strs[j].charAt(i)!=tempChar)
        {
          // substring(i,j) 方法截取字符串， 不包含处索引的字符，实际截取 i ~ j-1
          return strs[0].substring(0,i);
        }
      }
    }
   return strs[0];
  }
}
