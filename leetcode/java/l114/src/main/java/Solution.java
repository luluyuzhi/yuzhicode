import java.util.ArrayList;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}


/*
给你二叉树的根结点 root ，请你将它展开为一个单链表：

展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
展开后的单链表应该与二叉树 先序遍历 顺序相同。

示例 1：


输入：root = [1,2,5,3,4,null,6]
输出：[1,null,2,null,3,null,4,null,5,null,6]
示例 2：

输入：root = []
输出：[]
示例 3：

输入：root = [0]
输出：[0]

 */


public class Solution {
    public TreeNode links = new TreeNode();

    public void flatten(TreeNode root) {
        //二叉树前序遍历非递归实现
        if (root == null) {
            return;
        }
        // 用来记录链表的头部
        TreeNode headlinks = links;
        // 借助于栈来实现非递归遍历
        Stack<TreeNode> treeNodes = new Stack<>();
        treeNodes.push(root);
        //
        TreeNode pre = null;
        while (!treeNodes.isEmpty()) {
            // 先取出栈顶元素
            TreeNode popNode = treeNodes.pop();
            if (pre !=null)
            {
                pre.left = null;
                pre.right = popNode;
            }

            // 如果有右子树，右子树先进栈，这样保证出栈的时候，左子树先出栈
            if (popNode.right != null) {
                treeNodes.push(popNode.right);
            }
            if (popNode.left != null) {
                treeNodes.push(popNode.left);
            }
            pre = popNode;
        }


    }


    public static void main(String[] args) {
        // 构造实例二叉树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        new Solution().flatten(root);
    }
}
