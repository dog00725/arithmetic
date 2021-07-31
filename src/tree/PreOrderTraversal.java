package tree;

import java.util.ArrayList;
import java.util.List;

public class PreOrderTraversal {


    public List<Integer> preorderTraversal_Iteration(TreeNode root) {
        List list = new ArrayList();
        recursion(root,list);
        return list;
    }



    /**
     * 二叉树前序遍历-递归实现
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal_Recursion(TreeNode root) {
        List list = new ArrayList();
        recursion(root,list);
        return list;
    }

    public void recursion(TreeNode root,List list){
        list.add(root.val);
        if (root.left != null){
            recursion(root.left,list);
        }
        if (root.right != null){
            recursion(root.right,list);
        }
    }
}
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
