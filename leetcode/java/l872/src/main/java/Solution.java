import java.util.ArrayList;

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


public class Solution {

    public ArrayList<Integer> lList1 = new ArrayList<Integer>();
    public ArrayList<Integer> lList2 = new ArrayList<Integer>();
    public ArrayList<Integer> lList = new ArrayList<Integer>();



    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        boolean flag = true;
        lList.clear();
        lList = preOrder(root1);
        lList.stream().forEach(x->{lList1.add(x);});

        lList.clear();
        lList = preOrder(root2);
        lList.stream().forEach(x->{lList2.add(x);});


        for (int i = 0; i < lList1.size(); i++) {
            if (lList1.get(i) != lList2.get(i))
            {
                flag = false;
                break;
            }
        }

        return flag;
    }

    public ArrayList<Integer> preOrder(TreeNode root)
    {
        if (root==null)
        {
            return lList;
        }
        // 叶子节点， 存入
        if (root.left==null && root.right==null)
        {
            lList.add(root.val);
        }
        else
        {
            preOrder(root.left);
            preOrder(root.right);
        }
        return lList;
    }


    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(7);

        System.out.println(new Solution().leafSimilar(root1, root1));

    }
}
