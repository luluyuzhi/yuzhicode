package leetcode.l4;

public class Solution {
  // 4 求两个升序序列的中位数

  /**
   *
   * @param nums1
   * @param nums2
   * @return  nums1和nums2的中位数
   *
   * todo 说明
   *      1. 我们最直观的想法就是将两个数组进行合并，然后排序，最后取中位数；这种方法可取，但是题目要求时间复杂度为O(log(m+n))
   *      2. 第二种方法就是：长度确定，所以中位数的位置也是确定的（K，或者 K和K+1）。
   *                      这是可以使用两个指针， 同时遍历两个序列，每次移动两个指针指向的最小元素的那个指针，移动K次后，即为中位数的位置
   *      3.使用二分查找：
   *                  第二种方法，我们可以换一个角度来看这个问题，在求得中位数的位置之前，上述操作所做的实质上是排除小于中位数位置的数；
   *                  也可以称之为【求第K小的数， 这个K=(m+n)/2,或者 K=(m+n)/2 和 K=(m+n)/2 + 1】  分奇数和偶数的情况
   *                  寻找第K小的数，就可以使用二分查找：
   *                              计算出K，对两个序列求前 k/2小的数，对比两个序列次位置的值，最小数所在的序列之前的数都可以丢弃。
   *                                然后 K = k - 丢弃元素的个数
   *                                直至 K=1为止，或者其中一个序列已经全部抛弃为止
   *                  特别说明：如果其中一个序列经过移动后，超出了其长度外围，那么就可以认定这个序列的所有元素全部可以丢弃，只需考虑剩余的另一个序列即可。
   */
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int totalLength = nums1.length + nums2.length;
    if (totalLength%2==1)
    {
      //奇数
      int midIndex = totalLength/2;
      return getKthMinElement(nums1,nums2,midIndex+1);
    }else{
      int midIndex1 = totalLength/2 - 1;
      int midIndex2 = totalLength/2;
      return (getKthMinElement(nums1,nums2,midIndex1+1) + getKthMinElement(nums1,nums2,midIndex2+1)) / 2.0;
    }
  }

  public int getKthMinElement(int[] nums1, int[] nums2, int k)
  {
    int length1 = nums1.length;
    int length2 = nums2.length;
    int index1 = 0;
    int index2 = 0;
    while(true)
    {
      if (index1==length1)
      {
        return nums2[index2 + k - 1];
      }
      if (index2 == length2)
      {
        return nums1[index1 + k - 1];
      }
      if (k==1)
      {
        return Math.min(nums1[index1], nums2[index2]);
      }
      int half = k/2;
      int nextIndex1 = Math.min(index1 + half, length1) - 1;
      int nextIndex2 = Math.min(index2 + half, length2) - 1;
      int pivot1 = nums1[nextIndex1];
      int pivot2 = nums2[nextIndex2];
      if (pivot1<=pivot2)
      {
        k -= (nextIndex1 - index1 + 1);
        index1 = nextIndex1+1;
      }else
      {
        k -= (nextIndex2 - index2 + 1);
        index2 = nextIndex2+1;
      }
    }
  }
}
