package leetcode.l17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import sun.java2d.pipe.SolidTextRenderer;

public class Solution {

  // 电话号码的字母组合
  public HashMap<Integer, char[]> charMap = new HashMap<>();

  //因为会有相同的数字按下，所以，访问标志位就不用添加了
//  public HashMap<Integer, boolean[]> flagMap = new HashMap<>();
  public Solution() {
    charMap.put(2, "abc".toCharArray());
    charMap.put(3, "def".toCharArray());
    charMap.put(4, "ghi".toCharArray());
    charMap.put(5, "jkl".toCharArray());
    charMap.put(6, "mno".toCharArray());
    charMap.put(7, "pqrs".toCharArray());
    charMap.put(8, "tuv".toCharArray());
    charMap.put(9, "wxyz".toCharArray());
//    flagMap.put(2,new boolean[3]);
//    flagMap.put(3,new boolean[3]);
//    flagMap.put(4,new boolean[3]);
//    flagMap.put(5,new boolean[3]);
//    flagMap.put(6,new boolean[3]);
//    flagMap.put(7,new boolean[4]);
//    flagMap.put(8,new boolean[3]);
//    flagMap.put(9,new boolean[4]);
  }

  public ArrayList<String> list = new ArrayList<>();

  public List<String> letterCombinations(String digits) {
    if(digits.length()==0||digits==null){
      return list;
    }
    int digitLen = digits.length();
    char[] chars = digits.toCharArray();
    int digitIndex = 0;
    StringBuffer sb = new StringBuffer();
    dfs(chars, 0, sb, digitLen);

    return list;
  }

  public void dfs(char[] digitChars, int index, StringBuffer sb, int digitLen) {
    //如果输入的digits已经边到最后一个数字，那么就记录所经历的字母
    if (index >= digitLen) {
      list.add(new String(sb));  // 由于sb 是一个引用，所以这里要创建一个新的对象
      return;
    }
    char[] getChars = charMap.get(digitChars[index] - '0');  // 字符转换成数字
//    boolean[] flags = flagMap.get(digitChars[index]-'0');
    for (int i = 0; i < getChars.length; i++) {
//      if (flags[i])
//      {
//        continue;
//      }
//      flags[i] = true;
      sb.append(getChars[i]);
      dfs(digitChars, index + 1, sb, digitLen);
      //回溯， 状态重置
//      flags[i] = false;
      sb.deleteCharAt(sb.length() - 1);  // 路径缓存中删除最后一个元素
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().letterCombinations("22"));
  }
}
