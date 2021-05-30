package l25;

import java.util.LinkedList;

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
 * k个一组翻转链表
 */
public class Solution {

  /**
   * 关键性节点 dummy、pre：前驱节点、start、end
   *
   * @param head
   * @param k
   * @return
   */
  public ListNode reverseKGroup(ListNode head, int k) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode pre = dummy;
    ListNode end = dummy;

    while (end.next != null) {
      //先找尾结点end，然后通过前去节点pre找到start节点
      for (int i = 0; i < k && end != null; i++) {
        end = end.next;
      }
      if (end == null) {
        break;
      }
      ListNode start = pre.next;
      ListNode next = end.next;
      end.next = null;

      pre.next = reverse(start);  // 翻转链表，返回头结点
      start.next = next;
      pre = start;
      end = pre;
    }
    return dummy.next;
  }

  /**
   * 翻转链表时，我们将分组内的链表节点提取出来，单独处理，以避免影响后续的节点
   *
   * @param head
   * @return
   */
  private ListNode reverse(ListNode head) {
    ListNode pre = null;
    ListNode curr = head;
    while (curr != null) {
      ListNode next = curr.next;
      curr.next = pre;
      pre = curr;
      curr = next;
    }
    return pre;
  }

  public static void main(String[] args) {

  }

}
