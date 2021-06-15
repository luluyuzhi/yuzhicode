package leetcode.l18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

  /**
   * 计算数组中的四数之和，是否存在目标值，如果存在就返回
   * 基本思路：
   *        先确定两个元素，然后再利用双指针来求解(需要对整个数组进行排序)
   *        确定两个元素的时候，可以进行剪枝操作
   *          1. 确定第一个元素nums[i]的时候
   *            1.1 如果 num[i]与之后的连续三个数相加 > target，那么需要跳出循环，因为排序后，越往后的数越大，不可能再出现等于target的四个数之和
   *            1.2 如果 num[i]与数组中的最后三个数相加 < target， 那么说明此次确定的num[i]确定小了，需要进行下次循环，确定一个更大的数
   *            补充点： 每次确定元素的时候，需要保证不能与上次的相同，确保元素不会重复
   *          2. 如果第一个元素确定之后，再进行第二个元素的确认
   *            2.1 剪枝同上
   *            2.2 剪枝同上
   *        如果两个元素都确定好了，那么就开始使用双指针，这里面就跟 确定三数之和相似了，这里就不做过多的介绍啦。
   * @param nums
   * @param target
   * @return 返回一个list，记录所有四个数之和=target的四元组。
   */
  public List<List<Integer>> fourSum(int[] nums, int target) {
    List<List<Integer>> quaList = new ArrayList<List<Integer>>();
    if (nums == null || nums.length < 4) {
      return quaList;
    }
    // 先对数组进行排序
    Arrays.sort(nums);
    int length = nums.length;
    //确定第一个元素
    for (int i=0;i<length-3;i++){
      //除了第一次外，每次第一个数都要保证不与上一次的数相同，避免出现重复的四元组
      if (i>0&&nums[i]==nums[i-1])
      {
        continue;
      }
      //如果第一个确定了，且与之后的三个数相加大于target，那么由于数组已经排好序，后面不可能再出现等于target的情况
      if(nums[i]+nums[i+1]+nums[i+2]+nums[i+3] > target)
      {
        break;
      }
      //同理，如果第一个数确定了，且与之数组中最后三个数相加小于target，那么就表示第一个数确定小了，直接continue进入下一次循环即可，选择更大的第一个数
      if (nums[i]+nums[length-3]+nums[length-2]+nums[length-1] < target)
      {
        continue;
      }
      //确定第二个元素
      for (int j=i+1;j<length-2;j++)
      {
        if (j>i+1&&nums[j]==nums[j-1])
        {
          continue;
        }
        if (nums[i]+nums[j]+nums[j+1]+nums[j+2]>target)
        {
          break;
        }
        if (nums[i]+nums[j]+nums[length-2]+nums[length-1] < target)
        {
          continue;
        }
        //两个数确定之后，就开始使用双指针了
        int left = j+1;
        int right = length-1;
        while (left<right)
        {
          int sum = nums[i]+nums[j]+nums[left]+nums[right];
          //如果四数之后等于 target，记录这四个数
          if (sum==target)
          {
            quaList.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
            //记录完之后，需要移动左右指针  到  与上一次不相同元素的位置
            while (left<right&&nums[left]==nums[left+1])
            {
              left++;
            }
            left++;
            while (left<right&&nums[right]==nums[right-1])
            {
              right--;
            }
            right--;
          }else if(sum< target){
            left++;
          }else{
            right--;
          }
        }
      }
    }
      return quaList;
  }
}
