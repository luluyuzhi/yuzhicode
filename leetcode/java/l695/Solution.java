package leetcode.l695;

import java.util.HashMap;

public class Solution {

  /**
   * 定义并查集类
   */
  class UnionFind {

    public int count; // 记录岛屿的数量
    public int[] fa;  // 记录每个位置的元素的父元素
    public int[] rank;  // 记录每个元素的深度
    public UnionFind(int[][] grid) {
      int m = grid.length;
      int n = grid[0].length;
      fa = new int[m * n];
      rank = new int[m * n];
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          // 初始化fa数组，使其grid[i][j]==1的时候，i*n+j位置的父元素为其本身
          if (grid[i][j] == 1) {
            fa[i * n + j] = i * n + j;
            count++;
            rank[i * n + j] = 1;
          } else {
            rank[i * n + j] = 0;
            // 如果grid[i][j]==0，将其父元素设置为-1，并在find和union的时候需要单独处理
            fa[i * n + j] = -1;
          }
        }
      }
    }

    public int find(int x) {
      if (fa[x] == x) {
        return x;
      } else if (fa[x] != -1) {  // 此处需要单独处理fa[x]=-1的情况
        // 路径压缩
        fa[x] = find(fa[x]);
        return fa[x];
      } else {
        // 当fa[x]==-1的时候，直接返回-1
        return -1;
      }
    }

    // 按秩合并
    public void union(int x, int y) {
      int rootx = find(x);
      int rooty = find(y);
      if (rootx != rooty && rootx != 1 & rooty != -1) {
        if (rootx <= rooty) {
          // 秩小的合并到秩大的节点上
          fa[rootx] = rooty;
        } else {
          fa[rooty] = rootx;
        }
        if (rootx == rooty) {
          // 当秩相同的时候，合并完之后，根节点的秩需要+1
          rank[rooty]++;
        }
        count--;
      }
    }

  }

  /**
   * 求得岛屿的最大面积
   * 基本思路：
   *        类似于 求岛屿的数量
   *        使用并查集的思想实现求岛屿的最大面积
   *              1. 并查集中的父元素集合 用int[] fa表示，其中的元素为 grid网格中的元素实际的位置值（行索引*列宽 + 列索引）
   *              2. 实现基本的并查集核心方法：
   *                                      find(int x)           使用路径压缩
   *                                      union(int x, int y)   使用按秩合并
   *              3. 在进行元素合并的时候，每遍历到grid中元素为1的时候，就将这个元素的上下左右4个方向的为1的元素合并到当前元素所在岛屿
   *              4. 最后通过hash来计算每个父节点下有多少个元素，进而求得最大的岛屿的面积
   * @param grid
   * @return
   */
  public int maxAreaOfIsland(int[][] grid) {
    // 处理边界值
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
      return 0;
    }
    UnionFind uf = new UnionFind(grid);
    int m = grid.length;
    int n = grid[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) {
          // 遍历到元素为1时，就立即将其设置为0，避免后续处理为1的元素，出现环的情况
          grid[i][j] = 0;
          // 将其四周所有为1的元素都与当前元素进行union
          if (i - 1 >= 0 && grid[i - 1][j] == 1) {
            // 上
            uf.union((i - 1) * n + j, i * n + j);
          }
          if (i + 1 < m && grid[i + 1][j] == 1) {
            // 下
            uf.union((i + 1) * n + j, i * n + j);
          }
          if (j - 1 >= 0 && grid[i][j - 1] == 1) {
            // 左
            uf.union(i * n + j - 1, i * n + j);
          }
          if (j + 1 < n && grid[i][j + 1] == 1) {
            // 右
            uf.union(i * n + j + 1, i * n + j);
          }
        }
      }
    }
    // 没有岛屿，直接返回0
    if (uf.count == 0) {
      return 0;
    }
    int maxArea = Integer.MIN_VALUE;
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int key = uf.find(i * n + j);
        if (key != -1) {
          int value = map.getOrDefault(key, 0);
          value++;
          map.put(key, value);
          maxArea = Math.max(maxArea, value);
        }

      }
    }
    return maxArea;
  }


  public static void main(String[] args) {
    System.out.println(new Solution().maxAreaOfIsland(new int[][]{
        {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
        {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
        {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
    }));
  }
}
