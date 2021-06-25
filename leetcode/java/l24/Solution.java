package leetcode.l24;

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

  public ListNode swapPairs(ListNode head) {
    ListNode pre = null;    //先添加一个傀儡头结点
    ListNode current = null;
    if (head == null || head.next == null) {
      return head;
    } else {
      current = head;
      head = head.next;
    }
    while (current != null && current.next != null) {
      ListNode nextNode = current.next;
      //开始进行交换
      if (pre != null) {
        pre.next = current.next;
      }
      current.next = nextNode.next;
      nextNode.next = current;
      //交换完毕后，移动pre 与 current
      pre = current;
      if (current != null) {
        current = current.next;
      }
    }
    return head;
  }
}
