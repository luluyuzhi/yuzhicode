package leetcode.l94;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {

  class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }


  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    if (root == null) {
      return list;
    }
    LinkedList<TreeNode> stack = new LinkedList<>();
    while(!stack.isEmpty() || root!=null){
      while (root != null) {
        stack.push(root);
        root=root.left;
      }
      // 当遍历到左子树的尽头的时候，即 root=null的时候，pop出栈顶元素
      root = stack.pop();
      // 记录一下中序遍历的结果
      list.add(root.val);
      //继续遍历右子树
      root=root.right;
    }
    return list;
  }

}
