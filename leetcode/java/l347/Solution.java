package leetcode.l347;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {
  // 前K个高频元素
  public int[] topKFrequent(int[] nums, int k) {
    HashMap<Integer, Integer> map = new HashMap<>();
    for(int num:nums){
      map.put(num, map.getOrDefault(num, 0) + 1);
    }
    PriorityQueue<int[]> priorityQueue = new PriorityQueue<int[]>(new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return o1[1] - o2[1];  // 数组中存放两个元素，位置0存放具体的数值， 位置1处存放该数值出现的次数
      }
    });
    for(Map.Entry<Integer,Integer> entry : map.entrySet()){
      int num = entry.getKey();
      int value = entry.getValue();
      // 入队，也就是 进入堆候选（优先级队列内部实现为 小顶堆）
      // 判断堆的元素是否大于等于K，如果没有，直接入 堆；否则对比当前元素出现的次数与堆顶元素出现的次数， 如果待插入元素的出现次数小于堆顶，那么这个元素出现的次数一定不在频次前K中
      if (priorityQueue.size()>=k){
        if (priorityQueue.peek()[1]<value){
          priorityQueue.poll();
          priorityQueue.offer(new int[]{num, value});
        }
      }else{
        priorityQueue.offer(new int[]{num, value});
      }
    }
    int[] result = new int[k];
    for(int i=0;i<k;i++){
      result[i] = priorityQueue.poll()[0];
    }
    return result;
  }
}
