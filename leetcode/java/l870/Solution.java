package leetcode.l870;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class Solution {

  /**
   * 优势洗牌
   * A[i]>B[i]的个数 称为A相对于B的优势数（别名：田忌赛马）
   *
   * @param nums1
   * @param nums2
   * @return
   */
  public int[] advantageCount(int[] nums1, int[] nums2) {
    // 将两份数组进行克隆，然后进行升序排序
    int[] A = nums1.clone();
    Arrays.sort(A);
    int[] B = nums2.clone();
    Arrays.sort(B);
    // 创建一个map，记录B数组每个元素对应的A的元素，通过map，可以还原原始nums的原始位置关系
    HashMap<Integer, Deque<Integer>> assignedMap = new HashMap<>();   // 这里为什么要使用Deque，而不是使用一个数字，因为可能存在相同的key值，就是B中会存在多个一样的数据
    //双端队列，即可以当成队列使用，也可以当成栈使用， pop方法弹出队头元素
    Deque<Integer> remain = new LinkedList<>();
    //初始化assignedMap
    for(int b:B){
      assignedMap.put(b, new LinkedList<Integer>());
    }

    // 遍历A数组， 比对B数组中的每个元素，找到A中大于B中元素的最小的那个值，并记录到assignedMap中，如果没有找到
    int j=0;
    for(int a:A){
      if (a>B[j]){
//        assignedMap.put(B[j], a);
        assignedMap.get(B[j]).add(a);
        j++;
      }else{
        remain.add(a);
      }
    }
    int[] result = new int[nums1.length];
    for (int i=0;i<result.length;i++){
      if (assignedMap.get(nums2[i]).size()!=0){
        result[i]=assignedMap.get(nums2[i]).pop();
      }else{
        result[i] = remain.pop();
      }
    }
    return result;
  }

  public static void main(String[] args) {
  }

}
