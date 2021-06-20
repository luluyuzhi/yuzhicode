package leetcode.l13;

import java.util.HashMap;

public class Solution {

  // 13. 罗马数字转整数
  public int romanToInt(String s) {
    // 将罗马数据映射存储到hashMap中
    HashMap<Character, Integer> hashMap = new HashMap<>();
    hashMap.put('I', 1);
    hashMap.put('V', 5);
    hashMap.put('X', 10);
    hashMap.put('L', 50);
    hashMap.put('C', 100);
    hashMap.put('D', 500);
    hashMap.put('M', 1000);
    char[] chars = s.toCharArray();
    int i = 0;
    int result = 0;
    int length = chars.length;
    while (i < length) {
      //遍历到最后一个元素
      if (i + 1 >= length) {
        result += hashMap.get(chars[i]);
        break;
      }

      if (hashMap.get(chars[i]) >= hashMap.get(chars[i + 1])) {
        result += hashMap.get(chars[i]);
        i += 1;
        continue;
      }

      if (hashMap.get(chars[i]) < hashMap.get(chars[i + 1])) {
        result += hashMap.get(chars[i + 1]) - hashMap.get(chars[i]);
        i += 2;
        continue;
      }
    }
    return result;
  }
}
