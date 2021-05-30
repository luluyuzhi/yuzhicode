package l136;

/**
 * todo 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。 说明：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？ 示例
 * 1: 输入: [2,2,1] 输出: 1 示例 2: 输入: [4,1,2,1,2] 输出: 4
 */
public class Solution {

  /**
   * 解题方法有很多，比如hash，暴力解等等，但是都需要借助额外的空间。 todo  使用位操作：异或运算 一个数与0进行异或还是其本身：a⊙0=a 一个数与自身进行异或运算，其结果为0 :
   * a⊙a = 0 // TODO: 2021/5/27 异或运算支持 交换律、结合律 a⊙b⊙c = a⊙(b⊙c) 那么：这道题用此方法运算，可以不用借助其他额外的空间
   *
   * @param nums
   * @return
   */
  public int singleNumber(int[] nums) {
    if (nums == null || nums.length < 1) {
      return 0;
    }
    int result = nums[0];
    for (int i = 1; i < nums.length; i++) {
      result ^= nums[i];
    }
    return result;
  }

  public static void main(String[] args) {

  }
}
