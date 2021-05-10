import java.util.ArrayList;
import java.util.Stack;

// 非递归实现
public class Solution2 {

    public Stack<TreeNode> stack = new Stack<>();
    public ArrayList<Integer> list = new ArrayList<>();


    public boolean leafSimilar(TreeNode root1, TreeNode root2) {

        if (root1==null || root1==null)
        {
            return false;
        }
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        list.clear();
        preOrderWithStack(root1).stream().forEach(x->{list1.add(x);});
        list.clear();
        preOrderWithStack(root2).stream().forEach(x->{list2.add(x);});

        if (list1.size()!=list2.size())
        {
            return false;
        }
        boolean flag = true;
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i)!=list2.get(i))
            {
                flag=false;
                break;
            }
        }
        return flag;
    }

    public ArrayList<Integer> preOrderWithStack(TreeNode root)
    {
        if (root==null)
        {
            return list;
        }
        stack.push(root);
        while (!stack.isEmpty())
        {
            //栈顶出栈
            TreeNode peekNode = stack.pop();
            //判断 是否存在左右孩子，入栈顺序  先右后左   ，这样就能保证 出栈的顺序先 左后右了
            if (peekNode.left==null && peekNode.right==null)
            {
                // 叶节点的情况
                list.add(peekNode.val);
                continue;
            }
            else
            {
                if (peekNode.right!=null)
                {
                    stack.push(peekNode.right);
                }
                if (peekNode.left!=null)
                {
                    stack.push(peekNode.left);
                }
            }
        }
        return list;
    }




    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(1);
        root1.left.left = new TreeNode(6);
        root1.left.right = new TreeNode(2);
        root1.right.left = new TreeNode(9);
        root1.right.right = new TreeNode(8);
        root1.left.right.left = new TreeNode(7);
        root1.left.right.right = new TreeNode(4);

        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(5);
        root2.right = new TreeNode(1);
        root2.left.left = new TreeNode(6);
        root2.left.right = new TreeNode(7);
        root2.right.left = new TreeNode(4);
        root2.right.right = new TreeNode(2);
        root2.right.right.left = new TreeNode(9);
        root2.right.right.right = new TreeNode(8);


        System.out.println(new Solution2().leafSimilar(root1, root2));
    }
}
