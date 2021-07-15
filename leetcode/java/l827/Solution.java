package leetcode.l827;

import java.util.HashMap;
import java.util.HashSet;

public class Solution {

  /**
   * 求最大人工岛屿
   *
   * @param grid
   * @return
   */
  public int largestIsland(int[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
      return 1;
    }
    // index 代表岛屿编号， 0代表海洋  1代表陆地， 2之后代表岛屿编号
    int index = 2;
    int res = 0;
    // indexAndAreas中记录每个索引岛屿的面积, 并填充岛屿编号
    HashMap<Integer, Integer> indexAndAreas = new HashMap<>();
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[0].length; c++) {
        if (grid[r][c] == 1) {
          int area = area(grid, r, c, index);
          indexAndAreas.put(index, area);
          index++;
          res = Math.max(res, area);
        }
      }
    }

    // 如果没有岛屿，直接返回1
    if (res == 0) return 1;//res=0表示没有陆地，那么造一块，则返回1即可

    // 开始处理填充
    /**
     * 遍历海洋格子，假设这个格子填充，那么就把上下左右是陆地的格子所在的岛屿连接起来
     */
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[0].length; c++) {
        if (grid[r][c] == 0) {
          HashSet<Integer> set = findNeighbour(grid, r, c);
          if (set.size() < 1) {
            continue; // 待填充的海洋四周没有岛屿相连，不做处理，继续填充下一个海洋
          }
          int twoIsLand = 1;  //填充这个格子，初始为1，这个变量记录合并岛屿后的面积
          for (Integer i : set) {
            twoIsLand += indexAndAreas.get(i);  //该格子填充，则上下左右的陆地的都连接了，通过序号获得面积，加上面积
          }
          res = Math.max(res, twoIsLand);  //比较得到最大的面积
        }
      }
    }
    return res;
  }

  /**
   * dfs方法，将格子填充为index，即表示这个格子属于哪个岛的 计算岛屿面积，上下左右，当然这个可以优化的，因为不需要计算上面的，会有重复
   *
   * @param grid
   * @param r
   * @param c
   * @param index
   * @return
   */
  private int area(int[][] grid, int r, int c, int index) {
    if (!inArea(grid, r, c)) {
      // 如果 r c 不在grid范围内，直接返回0
      return 0;
    }
    // 当r c位置的元素不为1的时候，这个格子所代表的不是   海洋   就是   已经被遍历过的被修改完岛屿编号的格子
    if (grid[r][c] != 1) {
      return 0;
    }
    // 当r c位置的元素为1的时候，修改格子所代表的索引编号，深度遍历上下左右四个方向的格子
    grid[r][c] = index;
    return 1 + area(grid, r - 1, c, index) + area(grid, r + 1, c, index) + area(grid, r, c - 1,
        index) + area(grid, r, c + 1, index);
  }

  /**
   * 判断grid[r][c]是否大小合适 换句话说：判断r、c是否在整个grid范围内
   *
   * @param grid
   * @param r
   * @param c
   * @return
   */
  private boolean inArea(int[][] grid, int r, int c) {
    return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
  }

  /**
   * 对于海洋格子，找到上下左右 每个方向，都要确保有效inArea以及是陆地格子，则表示是该海洋格子的陆地邻居
   *
   * @param grid
   * @param r
   * @param c
   * @return 返回这个海洋格子上下左右能够连接的岛屿编号，使用set的目的是为了去重
   */
  private HashSet<Integer> findNeighbour(int[][] grid, int r, int c) {
    HashSet<Integer> set = new HashSet<>();
    //上
    if (inArea(grid, r - 1, c) && grid[r - 1][c] != 0) {
      set.add(grid[r - 1][c]);
    }
    //下
    if (inArea(grid, r + 1, c) && grid[r + 1][c] != 0) {
      set.add(grid[r + 1][c]);
    }
    //左
    if (inArea(grid, r, c - 1) && grid[r][c - 1] != 0) {
      set.add(grid[r][c - 1]);
    }
    //右
    if (inArea(grid, r, c + 1) && grid[r][c + 1] != 0) {
      set.add(grid[r][c + 1]);
    }
    return set;
  }

}
