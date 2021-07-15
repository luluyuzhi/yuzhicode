package leetcode.l221;

public class Solution {

  /**
   * 最大正方形
   * @param matrix
   * @return
   */
  public int maximalSquare(char[][] matrix) {
    int maxSide = 0;
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return maxSide;
    }
    int rowlen = matrix.length;
    int columnlen = matrix[0].length;
    // dp数组中的元素表示： i，j 为右下角的最长的正方形边长（正方形内的方块都为1）
    int[][] dp = new int[rowlen][columnlen];
    for (int i = 0; i < rowlen; i++) {
      for (int j = 0; j < columnlen; j++) {
        if (matrix[i][j] == '1') {
          if (i == 0 || j == 0) {
            dp[i][j] = 1;
          } else {
            // 上方，左方，左上方最小值
            dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
          }
          maxSide = Math.max(maxSide, dp[i][j]);
        }
      }
    }
    return maxSide * maxSide;
  }

}
