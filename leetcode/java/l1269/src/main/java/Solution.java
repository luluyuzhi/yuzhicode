public class Solution {
    /*
    有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。

    每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。

    给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。

    由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。

     

    示例 1：

    输入：steps = 3, arrLen = 2
    输出：4
    解释：3 步后，总共有 4 种不同的方法可以停在索引 0 处。
    向右，向左，不动
    不动，向右，向左
    向右，不动，向左
    不动，不动，不动
    示例  2：

    输入：steps = 2, arrLen = 4
    输出：2
    解释：2 步后，总共有 2 种不同的方法可以停在索引 0 处。
    向右，向左
    不动，不动
    示例 3：

    输入：steps = 4, arrLen = 2
    输出：8

     */

    /**
     *  todo
     *      1. 确定状态
     *      设dp[i][j]为 经过第i步之后，指针指向j处的方案数
     *      2. 子问题
     *      可以往左、不动、往右， 三种移动的状态
     *      dp[i][j] = dp[i-1][j+1] + dp[i-1][j] + dp[i-1][j-1]
     *                  上一步从j+1指针往左移动一步 的 方案数
     *                  上一步在j处 原地不动的 方案数
     *                  上一步从j-1指针往右移动一步 的 方案数
     *      3.边界值
     *      0<=i<= steps; 0<=j<=Matn.min(arrLen-1, steps)
     *      dp[0][0] = 1    (初始时刻， 停留在0处的方案数为1)
     *      当j-1<0时， dp[i-1][j-1]=0
     *      当j+1 > Matn.min(arrLen-1, steps) 时，dp[i-1][j+1] = 0
     *
     */

    public int numWays(int steps, int arrLen) {
        int modNum = 1000000007;
        int minLen = Math.min(arrLen - 1, steps);
        int[][] dp = new int[steps + 1][minLen + 1];
        dp[0][0] = 1;
        //外层循环控制移动的次数
        for (int i = 1; i <=steps; i++) {
            for (int j = 0; j <= minLen; j++) {
                dp[i][j] = dp[i-1][j]; // 停留在原地
                if (j+1<=minLen)
                {
                    dp[i][j] = (dp[i][j]+dp[i-1][j+1])%modNum;
                }
                if (j-1 >= 0)
                {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - 1])%modNum;
                }
            }
        }
        return dp[steps][0];
    }

    public static void main(String[] args) {
		System.out.print("add actions test!");
    }
}
