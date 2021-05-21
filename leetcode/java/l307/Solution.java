package l307;

public class Solution {
    public int[] BIT;
    public int[] nums;

    public Solution(int[] nums) {
        BIT = new int[nums.length + 1];
        //todo 树状数组初始化
        for (int i = 0; i < nums.length; i++) {
            this.add(i, nums[i]);
        }
        //todo 记录原始数组
        this.nums = nums;
    }
    //todo 更新指定索引处的值
    public void update(int index, int val) {
        int diff = val - this.nums[index];
        //todo 每次修改完之后，原数组中相应位置的元素也需要进行修改
        this.nums[index] = val;
        this.add(index, diff);
    }

    // todo 求区间的和
    public int sumRange(int left, int right) {
        //todo 求区间和 等价于  求 right的前缀和 - （left-1）前缀的和
        int right_res = sumIndex(right);
        int left_res = sumIndex(left - 1);
        return right_res - left_res;
    }
    //todo  关键的二进制位运算
    public int lowBit(int x) {
        return x & (-x);
    }

    //todo 索引index处 + X；  X可正可负
    public void add(int index, int x) {
        index = index + 1;
        while (index <= BIT.length - 1) {
            BIT[index] += x;
            index += lowBit(index);
        }
    }

    //todo 计算前缀和
    public int sumIndex(int index) {
        int res = 0;
        index = index + 1;
        while (index > 0) {
            res += BIT[index];
            index -= lowBit(index);
        }
        return res;
    }
}
