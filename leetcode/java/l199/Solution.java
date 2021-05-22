package l199;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 二叉树的右视图
public class Solution {

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
    // 初步想法：利用BFS，按层次输出每一层的最后一个元素
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        // 边界值处理。 root为空的时候， 输出空的list
        if (root==null)
        {
            return list;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<ArrayList<TreeNode>> levelElemnts = new LinkedList<>();
        //root enter quene
        queue.offer(root);
        while (!queue.isEmpty())
        {
            //首先记录 队列queue中的元素的个数， 这个个数就是每层的节点的个数
            int levelSize = queue.size();
            ArrayList<TreeNode> subList = new ArrayList<>();
            //将每一层的元素的所有子节点 入队，进入下一层
            for (int i = 0; i < levelSize; i++) {
                //取出队列的头部
                TreeNode qh = queue.poll();
                if (qh!=null)
                {
                    subList.add(qh);
                    if (qh.left!=null)
                    {
                        queue.offer(qh.left);
                    }
                    if (qh.right!=null)
                    {
                        queue.offer(qh.right);
                    }
                }
            }
            // 记录每一层
            levelElemnts.add(subList);
        }
        levelElemnts.stream().forEach((levelElement)->{
            //将每层的最后一个元素记录到list中
            list.add(levelElement.get(levelElement.size()-1).val);
        });
        return list;
    }


    public static void main(String[] args) {

    }
}
