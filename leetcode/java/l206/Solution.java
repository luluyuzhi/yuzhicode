package leetcode.l206;

import java.util.LinkedList;

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

  // 反转链表
  public ListNode reverseList(ListNode head) {
    ListNode tempHead = head;
    LinkedList<ListNode> stack = new LinkedList<>();
    while(tempHead!=null){
      ListNode tempNode = tempHead;
      tempHead = tempHead.next;
      tempNode.next = null;
      stack.push(tempNode);
    }
    ListNode newHead = new ListNode(0);
    ListNode newHead2 = newHead;
    while (!stack.isEmpty()){
      newHead2.next = stack.pop();
      newHead2 = newHead2.next;
    }
    return newHead.next;
  }

}
