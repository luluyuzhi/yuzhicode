package leetcode.l148;

import java.util.concurrent.locks.Lock;

/**
 * l148: 排序链表 自顶向下归并排序 自底向上归并排序
 */


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

  public ListNode sortList(ListNode head) {
    return sortList(head, null);
  }

  public ListNode sortList(ListNode head, ListNode tail) {
    if (head == null) {
      return null;
    }
    if (head.next == tail) {
      // 当head.next == tail的时候，把链断开， 设置next为null，这是关键性的一步；
      head.next = null;
      return head;
    }
    // 通过快慢指针，查找整个链表的中间节点， 看指针每次只移动一次， 快指针移动两次, 当快指针指向尾节点时，慢指针指向中间节点
    ListNode tempNode = new ListNode(0);
    ListNode slow = head;
    ListNode fast = head;
    while (fast != tail) {
      slow = slow.next;
      fast = fast.next;
      if (fast != tail) {
        fast = fast.next; // 需要移动两次
      }
    }
    // 当快指针到达尾结点时，慢指针指向的就是中间节点
    ListNode mid = slow;
    ListNode leftNode = sortList(head, mid);
    ListNode rightNode = sortList(mid, tail);
    ListNode mergeNode = merge(leftNode, rightNode);
    return mergeNode;
  }

  public ListNode merge(ListNode head1, ListNode head2) {
    ListNode tempHead = new ListNode(0);
    ListNode tempHead1 = head1;
    ListNode tempHead2 = head2;
    // 开始合并两个链表
    while (tempHead1 != null && tempHead2 != null) {
      if (tempHead1.val < tempHead2.val) {
        tempHead.next = tempHead1;
        tempHead1 = tempHead1.next;
      } else {
        tempHead.next = tempHead2;
        tempHead2 = tempHead2.next;
      }
      tempHead = tempHead.next;
    }
    if (tempHead1 != null) {
      tempHead.next = tempHead1;
    } else if (tempHead2 != null) {
      tempHead.next = tempHead2;
    }
    return tempHead.next;
  }
}
