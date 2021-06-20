package leetcode.l21;


import java.util.PriorityQueue;


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
  class Status implements Comparable<Status>
  {

    public int val;
    public ListNode node;

    public Status(ListNode node)
    {
      this.node = node;
      this.val = node.val;
    }

    public int compareTo(Status o)
    {
      return this.val - o.val;  // 升序
    }

  }


  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

    if(l1 == null && l2==null)
    {
      return null;
    }

    if(l1 == null || l2==null)
    {
      return l1!=null?l1:l2;
    }

    ListNode head = new ListNode(0);
    ListNode tail = head;
    // 定义一个优先级队列
    PriorityQueue<Status> priorityQueue = new PriorityQueue<>();
    // 两个链表头部入队
    priorityQueue.offer(new Status(l1));
    priorityQueue.offer(new Status(l2));
    while(!priorityQueue.isEmpty())
    {
      // 取出对头元素
      Status tempStatus = priorityQueue.poll();
      ListNode node = tempStatus.node;
      tail.next = node;
      tail = tail.next;
      if (node.next !=null)
      {
        priorityQueue.offer(new Status(node.next));
      }
    }
    return head.next;
  }
}
