package leetcode.l215;

public class Solution {

  /**
   * 交换数组中 i位置和j位置的数
   *
   * @param arr
   * @param i
   * @param j
   */
  public void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public void buildHeap(int[] arr, int heapsize) {
    for (int i = heapsize / 2 - 1; i >= 0; i--) {
      adjHeap(arr, i, heapsize);  // 传入的heapsize 是为了处理子树的子树的时候，限定索引值不会超过整个数组的长度
    }
  }

  /**
   * 调整以 i 位置为根节点， 调整子树，使其满足堆的要求
   *
   * @param arr
   * @param i
   * @param heapsize
   */
  public void adjHeap(int[] arr, int i, int heapsize) {
    int leftIndex = 2 * i + 1;
    int rightIndex = 2 * i + 2;
    int largest = i; // 记录 根 左 右 三个节点中的最大值
    if (leftIndex < heapsize && arr[leftIndex] > arr[largest]) {
      largest = leftIndex;
    }
    if (rightIndex < heapsize && arr[rightIndex] > arr[largest]) {
      largest = rightIndex;
    }
    if (largest != i) {
      //与根节点进行交换
      swap(arr, i, largest);
      // 递归调整 下层子树是否满足堆的特点
      adjHeap(arr, largest, heapsize);
    }
  }
  // todo 返回数组总第K的大的元素
  public int findKthLargest(int[] nums, int k) {
    int heapsize = nums.length;
    buildHeap(nums, heapsize);  // 这已经是一个大顶堆了
    for (int i = nums.length - 1; i >= nums.length - k + 1; i--) {
      // 交换对顶和最后一个元素，然后调整堆
      swap(nums, 0, i);
      --heapsize;
      adjHeap(nums, 0, heapsize);
    }
    return nums[0];
  }

}
