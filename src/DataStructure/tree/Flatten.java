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
        TreeNode temp = root.left;
        root.left = null;
        root.right = root.left;
//        root.left.right = root.right;  此不走有问题

    }
}
