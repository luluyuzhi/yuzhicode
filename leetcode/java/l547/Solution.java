package l547;


import java.util.HashSet;

/**
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * <p>
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * <p>
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * <p>
 * 返回矩阵中 省份 的数量。
 * <p>
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 * <p>
 * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 */

public class Solution {

    int fa[];
    int rank[];

    public int findCircleNum(int[][] isConnected) {
        int len = isConnected.length;
        fa = new int[len];
        rank = new int[len];
        for (int i = 0; i < fa.length; i++) {
            //初始状态， 每个元素的根节点都是它本身
            fa[i] = i;
        }
        for (int i = 0; i < rank.length; i++) {
            //初始状态，每个节点的深度都为1
            rank[i] = 1;
        }
        /*todo 遍历二维数组，为每个节点进行根节点的构建
              假如 二维数组[[1,1,0],[1,1,0],[0,0,1]]
              我们按顺序读取每行数据，由于二维矩阵isConnected[i][j] = isConnected[j][i]
              我们每次遇见isConnected[i][j]=1的时候，都将相应的isConnected[][]置为0，这就保证了两个节点之间有且仅有唯一的方向
              即 1-->2, 2-->3, 3-->3
              这样就完美符合了  并查集的要求啦
                下面我们开始搞代码！
         */
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i; j < isConnected[i].length; j++) {
                if (isConnected[i][j]==1)
                {
                    //合并节点i，j   使得节点i指向节点j
                    union(i, j);
                    //反向置0
                    isConnected[j][i]=0;
                }
            }
        }

        //利用hashSet存储每个省份的根节点
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < isConnected.length; i++) {
            set.add(find(i));
        }

        return set.size();
    }

    //todo 并查集两种主要的操作：find和union

    //压缩查找, 查找给定节点的根节点
    public int find(int x) {
        if (x == fa[x]) {
            return x;
        } else {
            // 压缩路径查找， 使得每次查找，每个节点的根节点都直接指向帮主
            fa[x] = find(fa[x]);
            return fa[x];
        }
    }

    //按秩合并， 根据每个节点的rank的深度，往深度大的节点上合并
    public void union(int x, int y) {
        //现获取每个x，y的根节点
        int rootx = find(x);
        int rooty = find(y);
        if (rank[rootx] <= rank[rooty]) {
            fa[rootx] = rooty;
        } else {
            fa[rooty] = rootx;
        }
        if (rootx != rooty && rank[rootx] == rank[y]) {
            rank[rooty]++;
        }
    }


    public static void main(String[] args) {

    }
}
