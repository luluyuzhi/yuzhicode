package l146;

import java.util.HashMap;

/**
 * LRU缓存机制，要求时间复杂度为O(1) 解决思路：哈希+双向链表
 */
public class LRUCache {

  class DLinkNode {

    int key;
    int value;
    DLinkNode prev;
    DLinkNode next;

    public DLinkNode() {
    }

    public DLinkNode(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }

  public HashMap<Integer, DLinkNode> cache = new HashMap<>();
  public int capacity; // 容量
  public int size;    // 当前大小
  public DLinkNode head;  // 双向链表  头指针
  public DLinkNode tail; // 双向链表  尾指针

  public LRUCache(int capacity) {
    this.capacity = capacity;
    size = 0;
    head = new DLinkNode();
    tail = new DLinkNode();
    head.next = tail;
    tail.prev = head;
  }

  public void moveNodeToHead(DLinkNode node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
    node.next = head.next;
    head.next.prev = node;
    node.prev = head;
    head.next = node;
  }

  public int get(int key) {
    if (!cache.containsKey(key)) {
      return -1;
    } else {
      // todo 取出当前节点
      DLinkNode node = cache.get(key);
      // todo 将当前节点移动至双向链表的头部
      moveNodeToHead(node);
      return node.value;
    }
  }

  public void put(int key, int value) {
    //先判断双向链表中是否存在当前节点的key，如果存在，则更新相应的值，如果不存在，就插入；插入就需要判断是否容量满了
    if (cache.containsKey(key)) {
      // 如果存在就更新这个节点的value
      DLinkNode node = cache.get(key);
      node.value = value;
      cache.put(key, node);
      moveNodeToHead(node);
    } else {
      //如果不存在，就需要创建新的节点，插入到当前双向链表中
      if (size >= capacity) {
        //取出队尾元素
        DLinkNode node = tail.prev;
        //移除尾元素
        tail.prev.prev.next = tail;
        tail.prev = tail.prev.prev;
        --size;
        //cache中也需要移除这个元素
        cache.remove(node.key);
      }
      //添加元素
      DLinkNode node = new DLinkNode(key, value);
      cache.put(key, node);
      node.next = head.next;
      head.next.prev = node;
      head.next = node;
      node.prev = head;
      size++;
    }
  }

}
