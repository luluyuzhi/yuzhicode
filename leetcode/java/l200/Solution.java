package leetcode.l200;

public class Solution {

  class UnionFind {

    public int count;
    public int[] fa;
    public int[] rank;

    public UnionFind(char[][] grid) {
      count = 0;
      int m = grid.length;
      int n = grid[0].length;
      fa = new int[m * n];
      rank = new int[m * n];
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (grid[i][j] == '1') {
            fa[i * n + j] = i * n + j;  // 设置默认值
            count++;
          }
          rank[i * n + j] = 1;
        }
      }
    }

    public int find(int x) {
      if (fa[x] == x) {
        return x;
      } else {
        //路径压缩
        fa[x] = find(fa[x]);
        return fa[x];
      }
    }

    public void union(int x, int y) {
      int rootx = find(x);
      int rooty = find(y);
      if (rootx != rooty) { // 让个合并的元素的父节点不一致时才进行合并
        if (rank[rootx] <= rank[rooty]) {
          fa[rootx] = rooty;
        } else {
          fa[rooty] = rootx;
        }
        if (rank[rootx] == rank[rooty]) {
          rank[rooty]++;
        }
        count--;
      }
    }

  }


  // 使用并查集的思想求岛屿的数量
  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
      return 0;
    }
    UnionFind uf = new UnionFind(grid);
    int m = grid.length;
    int n = grid[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1') {
          grid[i][j] = '0';  // 避免循环指向,所以每次处遍历到1的元素，需要置为0
          // 然后将其四周为1的元素的父节点都指向本元素
          if (i - 1 >= 0 && grid[i - 1][j] == '1') {
            uf.union((i - 1) * n + j, i * n + j);
          }
          if (i + 1 < m && grid[i + 1][j] == '1') {
            uf.union((i + 1) * n + j, i * n + j);
          }
          if (j - 1 >= 0 && grid[i][j - 1] == '1') {
            uf.union(i * n + j - 1, i * n + j);
          }
          if (j + 1 < n && grid[i][j + 1] == '1') {
            uf.union(i * n + j + 1, i * n + j);
          }
        }
      }
    }
    return uf.count;
  }
}
