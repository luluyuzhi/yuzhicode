package leetcode.l137;

import java.util.HashMap;

public class Solution {

  /**
   * 返回数组中只出现一次的数字II
   *
   * @param nums
   * @return
   */
  public int singleNumber(int[] nums) {
    // 对此提交使用hash
    HashMap<Integer, Integer> hash = new HashMap<>();
    for (int num : nums) {
      int value = hash.getOrDefault(num, 0);
      value++;
      hash.put(num, value);
    }
    int resutl = -1;
    for (int key : hash.keySet()) {
      if (hash.get(key) == 1) {
        resutl = key;
        break;
      }
    }
    return resutl;
  }

  /**
   * 进阶：保证不借助任何额外空间实现 说明： 由于 除了某个元素只出现一次外，其余元素都出现了3次 由于 int固定32位，对每一位进行求和之后mod  3， 所得到的就是出现一次的那个元素
   *
   * @param nums
   * @return
   */
  public int singleNumber2(int[] nums) {
    int[] bitArray = new int[32];
    for (int num : nums) {
      for (int i = 0; i < 32; i++) {
        if (((num >> i) & 1) == 1) { // 每次计算出移位完之后的最后移位的数
          bitArray[i]++; // 如果i位为1，那么32位数组的当前位置+1
        }
      }
    }
    int result = 0;
    for(int i=0;i<32;i++){
      if (bitArray[i]%3==1){
        result |= 1<<i;
      }
    }
    return result;
  }

}
