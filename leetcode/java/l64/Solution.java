package leetcode.l64;

public class Solution {

  /**
   * 二维动态规划，求解最小路径和
   *  设 dp[i][j] 表示 到达(i,j)位置的最小路径和
   *  子问题： dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
   *  边界值： 如果 i-1 与 j-1 同时小于0，说明此时是第一个元素，直接赋值为dp[i][j]
   *          如果 i-1或者j-1 其中一方小于0，那么此处原来的上方元素或者左方元素设为最大值，是指不参与运算
   *          当 i-1 与 j-1 均大于等于0
   *          dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
   *  最终返回 dp[XLEN-1][YLEN-1]
   * @param grid
   * @return  dp[XLEN-1][YLEN-1]
   */
  public int minPathSum(int[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
      return 0;
    }
    int result = 0;
    int XLEN = grid.length;
    int YLEN = grid[0].length;
    //
    int[][] dp = new int[XLEN][YLEN];
    // dp[0][0]=grid[0][0];
    for (int i = 0; i < XLEN; i++) {
      for (int j = 0; j < YLEN; j++) {
        //边界值处理
        // 第一个元素
        int left = 0;
        int top = 0;
        if (i - 1 < 0 && j - 1 < 0) {
          dp[i][j] = grid[i][j];
        } else {
          if (i - 1 < 0) {
            top = Integer.MAX_VALUE;
            left = dp[i][j - 1];
          } else if (j - 1 < 0) {
            top = dp[i - 1][j];
            left = Integer.MAX_VALUE;
          } else {
            top = dp[i - 1][j];
            left = dp[i][j - 1];
          }
          dp[i][j] = Math.min(top, left) + grid[i][j];
        }
      }
    }
    return dp[XLEN - 1][YLEN - 1];
  }

  public static void main(String[] args) {
    System.out.println(new Solution().minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
  }
}
