package L69;

public class Solution {


    /**
     * 实现int sqrt(int x)函数。 求x的平方根
     * <p>
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     * <p>
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 4
     * 输出: 2
     * 示例 2:
     * <p>
     * 输入: 8
     * 输出: 2
     * 说明: 8 的平方根是 2.82842...,
     *      由于返回类型是整数，小数部分将被舍去。
     * todo 使用二分查找实现求一个数的平方根
     * 设 x 为待求平方根的数
     * 如果一个数a的平方小于x，那么x的平方根一定在 a~x之间
     * 如果一个数a的平方大于x，那么x的平方根一定在 0~a之间
     *
     * @param x
     * @return sqrt(x)
     */


    // 二分查找
    public int mySqrt(int x) {
        int l = 0;
        int r = x;
        int ans = -1;  //存储平方根的整数部分
        while (l <= r) {
            int mid = l + (r - l) / 2;  // 取mid 使用二分查找进行计算， 避免使用（l+r）/2,防止溢出
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            }
            else
            {
                r = mid - 1;
            }
        }
        return ans;
    }

    /**
     * todo 牛顿迭代法 求 x的平方根
     *      通过 f(x) = X^2 - C求零根，进而求得 C的平方根
     *      得到递推式： Xn+1 = （Xn+C/Xn）/2
     * @param x
     * @return
     */

    public int mySqrt2(int x) {
        if (x == 0)
        {
            return 0;
        }

        double C = x;
        double x0 = x;
        while (true)
        {
            double xi = (x0 + C/x0)/2;
            if (Math.abs(xi - x0)<1e-7)
            {
                break;
            }
            x0 = xi;
        }
        return (int) x0;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().mySqrt2(4));
    }
}
