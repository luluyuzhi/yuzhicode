package leetcode.l234;

import java.util.ArrayList;
import java.util.LinkedList;
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
   * 回文链表，判断一个链表中的元素是否构成回文
   * @param head
   * @return
   */
  public boolean isPalindrome(ListNode head) {
    boolean flag = true;
    ArrayList<Integer> list = new ArrayList<>();
    while (head!=null){
      list.add(head.val);
      head = head.next;
    }
    int length = list.size();
    int i=0;
    int j=length-1;
    while (i<=j){
      if (list.get(i++)!=list.get(j--)){
        flag = false;
        break;
      }
    }
    return flag;
  }
}
