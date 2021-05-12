import com.sun.tools.doclint.Env;

import java.util.ArrayList;
import java.util.Comparator;

class Envelope{
    public int width;
    public int height;

    public Envelope(int width, int height) {
        this.width = width;
        this.height = height;
    }


}

class MyComparator implements Comparator<Envelope>
{
    public int compare(Envelope o1, Envelope o2) {
        if (o1.width != o2.width) {
            // 按照 width 进行 升序排序
            return o1.width - o2.width;
        } else {
            // 按照 height 进行降序排序， 当w值相同时，可以保证只取一个信封
            return o2.height - o1.height;
        }
    }
}


public class Solution {

    /**
     * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
     * <p>
     * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
     * <p>
     * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
     * <p>
     * 注意：不允许旋转信封。
     * <p>
     *  
     * 示例 1：
     * <p>
     * 输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
     * 输出：3
     * 解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
     * 示例 2：
     * <p>
     * 输入：envelopes = [[1,1],[1,1],[1,1]]
     * 输出：1
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/russian-doll-envelopes
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public int maxEnvelopes(int[][] envelopes) {

        if (envelopes==null || envelopes.length==0)
        {
            return 0;
        }

        ArrayList<Envelope> envs = new ArrayList<Envelope>();
        for (int i = 0; i < envelopes.length; i++) {

            envs.add(new Envelope(envelopes[i][0], envelopes[i][1]));
        }
        envs.sort(new MyComparator());

        int[] dp = new int[envs.size()];
        // dp[i] : 经历第 i封信封之后，的最大俄罗斯套娃数
        dp[0] = 1;
        int maxEnvs = 1;
        for (int i=1;i<envs.size();i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (envs.get(j).height<envs.get(i).height)
                {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxEnvs = Math.max(maxEnvs, dp[i]);
        }

        return maxEnvs;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().maxEnvelopes(new int[][]{
                {2, 200},
                {3, 200},
                {4, 300},
                {5, 500},
                {5, 400},
                {5, 250},
                {6, 370},
                {6, 360},
                {7, 380}
        }));
    }
}
