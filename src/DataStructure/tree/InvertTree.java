package DataStructure.tree;

/**
 * @Author: huqs
 * @Date: 2021/9/27 12:55
 */
public class InvertTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;
        invertTree(root.right);
        invertTree(root.left);
        return root;
    }

}
