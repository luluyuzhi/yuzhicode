use std::cell::RefCell;
use std::rc::Rc;
// Definition for a binary tree node.
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
    pub fn flatten0(root: &mut Option<Rc<RefCell<TreeNode>>>) {
        if let Some(node) = root {
            let mut n = node.as_ptr();
            unsafe {
                Self::flatten0(&mut (*n).left);
                Self::flatten0(&mut (*n).right);
                let temp = (*n).right.clone();
                (*n).right = (*n).left.clone();
                (*n).left = None;
                while let Some(nn) = (*n).right.clone() {
                    n = nn.as_ptr();
                }
                (*n).right = temp;
            }
        }
    }

    pub fn flatten1(root: &mut Option<Rc<RefCell<TreeNode>>>) {
        let mut curr = root.as_ref().map(|n| n.clone());
        while let Some(curr_node) = curr {
            let mut curr_node = curr_node.borrow_mut();
            if let Some(next_node) = curr_node.left.take() {
                // 寻找前驱节点
                let mut predecessor = next_node.clone();
                let mut predecessor_right = predecessor.borrow().right.clone();
                while let Some(node) = predecessor_right {
                    predecessor_right = node.borrow().right.clone();
                    predecessor = node;
                }
                // 右子树当作前驱节点的右子树
                predecessor.borrow_mut().right = curr_node.right.take();
                // 当前节点的左树当作右树
                curr_node.right = Some(next_node);
            }
            // 继续遍历右子节点
            curr = curr_node.right.clone();
        }
    }
}

fn main() {
    println!("Hello, world!");
}
