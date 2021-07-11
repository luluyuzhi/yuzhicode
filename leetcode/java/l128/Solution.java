package leetcode.l128;

import java.util.HashSet;

public class Solution {

  /**
   * 求最长连续子序列的长度
   * @param nums
   * @return
   */
  public int longestConsecutive(int[] nums) {
    if (nums==null||nums.length==0){
      return 0;
    }
    int longestLen = 0;
    HashSet<Integer> set = new HashSet<>();
    // 去重
    for(int num:nums){
      set.add(num);
    }
    //找到起始点，就是判断当前元素-1的数存不存在当前数组中，如果不存在，就可以往后+1寻找连续子序列
    for(int num:nums){
      if (!set.contains(num-1)){
        int currentNum = num;
        int currentStreak = 1;
        while (set.contains(currentNum+1)){
          currentNum++;
          currentStreak++;
        }
        longestLen = Math.max(longestLen, currentStreak);
      }
    }
    return longestLen;
  }

}
