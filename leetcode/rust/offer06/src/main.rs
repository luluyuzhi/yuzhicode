// Definition for singly-linked list.
#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
    pub val: i32,
    pub next: Option<Box<ListNode>>,
}

impl ListNode {
    #[inline]
    fn new(val: i32) -> Self {
        ListNode { next: None, val }
    }
}

struct Solution;
//*! 顺序反转
impl Solution {
    pub fn reverse_print(head: Option<Box<ListNode>>) -> Vec<i32> {
        let mut v = vec![];
        let mut b = &head;

        while let Some(x) = b {
            v.push(x.val);
            b = &x.next;
        }
        v.reverse();
        v
    }
}


impl Solution {
    //! 构造一个新的链表
    pub fn reverse_print1(head: Option<Box<ListNode>>) -> Vec<i32> {
        if head.is_none() {
            return vec![]
        }
        let mut r = Some(Box::new(ListNode {
            val: head.as_ref().unwrap().val,
            next: None,
        }));
        let mut fast = head.as_ref().unwrap().next.as_ref();
        while let Some(x) = fast {
            r = Some(Box::new(ListNode {
                val: x.val,
                next: r,
            }));
            fast = fast.unwrap().next.as_ref();
        }
        let mut v = vec![];

        let mut b = &r;

        while let Some(x) = b {
            v.push(x.val);
            b = &x.next;
        }

        v
    }
}


impl Solution {
    pub fn reverse_print2(head: Option<Box<ListNode>>) -> Vec<i32> {
        let mut result = Vec::new();

        let mut head = head.as_ref();

        while head.is_some() {
            result.insert(0, head.unwrap().val);
            head = head.unwrap().next.as_ref();
        }

        result
    }
}



fn main() {
    println!("Hello, world!");
}
