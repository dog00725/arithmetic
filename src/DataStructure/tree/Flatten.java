package DataStructure.tree;

/**
 * @Author: huqs
 * @Date: 2021/9/27 13:00
 */
public class Flatten {
    public void flatten(TreeNode root) {
        if (null == root){
            return;
        }
        flatten(root.left);
        flatten(root.right);
        
        /* 后序遍历位置 */
     // 1、左右子树已经被拉平成一条链表
        TreeNode left = root.left;
        TreeNode right = root.right;
        
     // 2、将左子树作为右子树
        root.left = null;
        root.right = left;
        //        root.left.right = root.right;  此步骤存在问题

        // 3、将原先的右子树接到当前右子树的末端
        TreeNode p = root;
        while (p.right != null){
            p = p.right;
        }
        p.right = right;
    }
}
