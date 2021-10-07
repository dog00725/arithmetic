package DataStructure.tree;

/**
 * @Author: huqs
 * @Date: 2021/10/4 15:54
 */
public class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int rightHigh = 0;
        int leftHigh = 0;
        TreeNode temp = root;
        while(temp.left != null){
            temp = temp.left;
            leftHigh++;
        }
        temp = root;
        while(temp.right != null){
            temp = temp.right;
            rightHigh++;
        }
        if (leftHigh == rightHigh){
            return (int)Math.pow(2,rightHigh+1)-1;
        }
        return 1+countNodes(root.left)+countNodes(root.right);
    }
}
