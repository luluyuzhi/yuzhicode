use std::cell::RefCell;
use std::rc::Rc;

#[derive(Debug, PartialEq, Eq)]
pub struct TreeNode {
    pub val: i32,
    pub left: Option<Rc<RefCell<TreeNode>>>,
    pub right: Option<Rc<RefCell<TreeNode>>>,
}

impl TreeNode {
    #[inline]
    pub fn new(val: i32) -> Self {
        TreeNode {
            val,
            left: None,
            right: None,
        }
    }
}
struct Solution;

impl Solution {
    pub fn deep_search(root: Option<Rc<RefCell<TreeNode>>>, leaf: &mut Vec<i32>) {
        let mut point = root;
        let mut v = vec![];

        while point.is_some() || !v.is_empty() {
            while point.is_some() {
                if let Some(p) = point {
                    v.push(p.clone());
                    point = p.borrow_mut().left.take();
                    if point.is_none() && p.borrow().right.is_none() {
                        leaf.push(p.borrow().val);
                    }
                }
            }
            point = v.pop();
            if let Some(p) = point {   
                point = p.borrow_mut().right.take();
            }
        }
    }

    pub fn leaf_similar(
        root1: Option<Rc<RefCell<TreeNode>>>,
        root2: Option<Rc<RefCell<TreeNode>>>,
    ) -> bool {
        let mut v1 = vec![];
        let mut v2 = vec![];

        Self::deep_search(root1, &mut v1);
        Self::deep_search(root2, &mut v2);

        if v1.len() != v2.len() {
            return false;
        }

        for i in 0..v1.len() {
            if v1[i] != v2[i] {
                return false;
            }
        }

        true
    }
}

fn main() {
    println!("Hello, world!");
}
