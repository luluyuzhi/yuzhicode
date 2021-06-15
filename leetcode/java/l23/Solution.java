package leetcode.l23;

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
    int val;
    ListNode node1;

    public Status(int val, ListNode node1) {
      this.val = val;
      this.node1 = node1;
    }

    @Override
    public int compareTo(Status node2) {
      return this.val-node2.val;
    }
  }

  /**
   * 朴素合并法， 直接按照两两链表进行合并，时间复杂度O(K^2 N)， 空间复杂度O(1)
   * @param lists
   * @return 合并后链表的头部
   */
  public ListNode mergeKLists1(ListNode[] lists) {
    ListNode ans = null;
    for (int i=0; i<lists.length;i++)
    {
      ans = mergeTwoLists(ans, lists[i]);
    }
    return ans;
  }

  /**
   * 分治法，优化第一种方法
   * 时间复杂度为 O(kn \times \log k)O(kn×logk)
   * 递归会使用到 O(\log k)O(logk) 空间代价的栈空间
   * @param lists
   * @return
   */
  public ListNode mergeKLists2(ListNode[] lists) {
    return merge(lists,0,lists.length-1);
  }
  public ListNode merge(ListNode[] lists, int left, int right)
  {
    if (left==right)
    {
      return lists[left];
    }
    else if(left>right)
    {
      return null;
    }
    int mid = left + (right-left)/2;
    return mergeTwoLists(merge(lists,left,mid),merge(lists,mid+1, right));
  }

  /**
   * 按顺序合并两个链表
   * @param a
   * @param b
   * @return 返回合并后链表的头部
   */
  public ListNode mergeTwoLists(ListNode a, ListNode b){
    if (a==null||b==null)
    {
      return a!=null?a:b;
    }
    ListNode head = new ListNode(0);
    ListNode tail = head;
    ListNode aPtr = a;
    ListNode bPtr = b;
    while (aPtr!=null&&bPtr!=null)
    {
      if (aPtr.val<bPtr.val)
      {
        tail.next = aPtr;
        aPtr = aPtr.next;
      }else{
        tail.next = bPtr;
        bPtr=bPtr.next;
      }
      tail = tail.next;
    }
    return head.next;
  }
  // 使用优先级队列，来进行合并
  /**
   * 首先，我们按照每个链表中元素的val值作为优先级，
   * 首先每个链表的头元素入队（不要切断与后面元素的连接）
   * 然后（流程类似于BFS），从队列中取出头元素进行合并，（头元素取出之后，还需要将后续第一个节点入队）
   * 直至队列为空
   */
  public ListNode mergeKLists3(ListNode[] lists) {
    if (lists==null || lists.length<0)
    {
      return null;
    }
    PriorityQueue<Status> priorityQueue = new PriorityQueue<>();
    for(ListNode node:lists)
    {
      //每个链表的第一个元素入队，入队后，优先级队列会自动排序
      priorityQueue.offer(new Status(node.val, node));
    }
    // 定义一个临时的头结点
    ListNode head = new ListNode(0);
    ListNode tail = head;
    while (!priorityQueue.isEmpty())
    {
      Status tempPollStatus = priorityQueue.poll();
      ListNode node = tempPollStatus.node1;
      tail.next = node;
      tail = tail.next;
      if (node.next!=null)
      {
        priorityQueue.offer(new Status(node.next.val, node.next));
      }
    }
    return head.next;
  }

}
