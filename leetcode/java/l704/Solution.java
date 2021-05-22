package l704;

public class Solution {
    // 基础版的二分查找
    /*
    给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     */
    public int search(int[] nums, int target) {
        int len = nums.length;
        int l=0;
        int r=len-1;
        int getIndex = -1;
        while (l <= r)
        {
            int mid = l+(r-l)/2;  // 禁止使用(l+r)/2 ，防止溢出
            if(nums[mid]==target)
            {
                getIndex = mid;
                break;
            }else if (nums[mid]>target)
            {
                r = mid-1;
            }else{
                l = mid+1;
            }
        }
        return getIndex;
    }

    public static void main(String[] args) {

    }

}
