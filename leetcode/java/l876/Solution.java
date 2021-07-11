package leetcode.l876;

import java.util.List;

public class Solution {

class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

  /**
   * 使用快慢指针找到链表的中间节点
   *  如果存在两个中间节点，通过控制先移动快指针，还是先移动慢指针来控制两个中间节点的特殊指向
   *    如果返回第二个中间节点，那么就先移动慢指针
   *    如果返回第一个中间节点，那么久先移动快指针，当快指针到达终点时，立即退出迭代，这样就可以保证慢指针少走一次
   *
   * @param head
   * @return
   */
  public ListNode middleNode(ListNode head) {
    if (head==null || head.next==null){
      return head;
    }
    ListNode slow = head;
    ListNode fast = head;
    while (fast!=null && fast.next!=null){
      slow = slow.next;
      fast = fast.next;
      if (fast!=null){
        fast = fast.next;
      }
    }
    return slow;
  }

}
