package leetcode.l135;

public class Solution {

  /**
   * ********分发糖果********
   * 根据孩子的评分分发糖果， 孩子的评分是无序的  且  评分高的孩子所分配到得糖果必须大于相邻孩子的糖果数量
   * 每个孩子至少一个糖果
   * 求最少需要多少枚糖果
   * @param ratings
   * @return 糖果的数量
   */
  public int candy(int[] ratings) {
    /*
    解题思路
          1. 把所有孩子的糖果初始化为 1
          2. 先从左往右遍历一遍，如果右边孩子评分比左边的高，则右边孩子的糖果数量更新为左边孩子糖果数量+1
          3. 再从右往左遍历一遍，如果左边孩子评分比右边的高，且 左边孩子所拥有的的糖果数量 不大于（<=） 右边糖果的数量，则左边孩子的糖果数量更新为右边孩子糖果数量+1

          贪心策略：在每次遍历中，只考虑并更新相邻一侧的大小关系
     */
    int childs = ratings.length;
    int[] candys = new int[childs];

    for(int i=0;i<candys.length;i++){
      candys[i] = 1;
    }
    //先从左往右遍历
    for (int i=0;i<childs-1;i++){
      if (ratings[i+1]>ratings[i]){
        candys[i+1] = candys[i]+1;
      }
    }
    // 再从右往左遍历
    for(int j = childs-1; j>0;j--){
      if(ratings[j-1]>ratings[j]&&candys[j-1]<=candys[j]){
        candys[j-1] = candys[j]+1;
      }
    }
    int count = 0;
    for(int num:candys){
      count+=num;
    }
    return count;
  }
}
