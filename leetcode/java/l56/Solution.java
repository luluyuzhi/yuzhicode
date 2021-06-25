package leetcode.l56;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Solution {

  /**
   * 合并区间
   *
   * @param intervals
   * @return
   */
  public int[][] merge(int[][] intervals) {
    if (intervals == null || intervals.length == 0) {
      return new int[1][2];
    }
    // 用来存储合并后的区间
    ArrayList<int[]> mergedList = new ArrayList<>();
    /**
     * 复习点：
     *      1. Comparable 接口的实现类，自身可以进行大小的比较， 比如 S类实现了Comparable接口， s1和s2这两个对象就可以进行大小的比较   s1-s2
     *      2. Comparator 接口的实现类，相当于一个第三方的比较器，通过这个比较器可以比较两个对象的大小
     *          比如： S类实现了Comparator<T>， 那么s对象可以比较两个T对象的大小，通过 s.compara(T1, T2)
     */
    // 现将数组中的区间， 按照第一个 L 左边界进行升序排序
    Arrays.sort(intervals, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return o1[0] - o2[0];
      }
    });

    // 排序完毕之后，依次加入到 mergedList中， 处理区间合并
    for (int i = 0; i < intervals.length; i++) {
      //
      if (mergedList.size() == 0 || mergedList.get(mergedList.size() - 1)[1] < intervals[i][0]) {
        mergedList.add(intervals[i]);
      } else {
        mergedList.get(mergedList.size() - 1)[1] = Math.max(mergedList.get(mergedList.size() - 1)[1], intervals[i][1]);
      }
    }
    return mergedList.toArray(new int[mergedList.size()][]);
  }


}
