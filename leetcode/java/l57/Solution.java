package leetcode.l57;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

  /**
   * @param intervals
   * @param newInterval
   * @return
   */
  public int[][] insert(int[][] intervals, int[] newInterval) {
    int left = newInterval[0];
    int right = newInterval[1];
    boolean flag = false; //记录新插入的元素是否插入到了list中
    ArrayList<int[]> merged = new ArrayList<>();
    for (int i = 0; i < intervals.length; i++) {
      // 当前区间在 新插入区间的左侧且没有交集的时候
      if (intervals[i][1]<left){
        merged.add(intervals[i]);
      }else if (intervals[i][0]>right){
        // 当前区间在 新插入区间的右侧且没有交集的时候， 首先要保证的就是将新插入的区间插入到 merged中
        if (!flag){
          merged.add(new int[]{left, right});  // 添加新插入的区间
          flag=true;
        }
        merged.add(intervals[i]);
      }else{
        //存在交集的时候，要进行区间合并
        left = Math.min(left, intervals[i][0]);
        right = Math.max(right, intervals[i][1]);
      }
    }
    if (!flag){
      //如果新插入的区间是在最后，那么新增新插入的区间
      merged.add(new int[]{left, right});
    }
    int[][] ans = new int[merged.size()][2];
    for (int i = 0; i < merged.size(); i++) {
      ans[i] = merged.get(i);
    }
    return ans;
  }


  public static void main(String[] args) {
    Arrays.stream(new Solution()
        .insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8})).forEach(x->{
      System.out.println(x);
    });
  }
}
