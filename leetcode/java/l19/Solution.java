package leetcode.l19;

public class Solution {

  class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  /**
   * 只用一趟遍历，实现删除链表中倒数第n个元素
   * @param head
   * @param n
   * @return head
   */
  public ListNode removeNthFromEnd(ListNode head, int n) {
    //边界值处理
    if (head.next == null && n >= 1) {
      return null;
    }
    ListNode pre = head;  // 跟踪所删除节点的前驱节点
    ListNode current = head; // 跟踪最后一个节点
    int num = 1;
    // 使用双指针跟踪，pre和current中间像个n+1个节点
    while (current.next != null) {
      if (num > n) {
        pre = pre.next;
      }
      current = current.next;
      num++; //记录当前
    }
    //循环结束后，链表遍历结束
    //分为两种情况：1. pre没有移动； 2. pre移动了
    //pre没有移动的时候，说明当前删除的元素是pre
    if (num <= n) {
      head = pre.next;
      pre.next = null;
    } else {
      ListNode delNode = pre.next;
      pre.next = pre.next.next;
      delNode.next = null;  // 方便垃圾回收
      return head;
    }
    return head;
  }
}
