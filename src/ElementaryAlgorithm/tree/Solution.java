package ElementaryAlgorithm.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Stack;

public class Solution {
    //前序遍历
    //递归
    public static void preOrderRecursion(TreeNode root){
        if (root == null) return;
        System.out.print(root.val + " ");
        preOrderRecursion(root.left);
        preOrderRecursion(root.right);
    }
    //循环
    public static void preOrderLoop(TreeNode root) {
        if (root == null) return;
        Deque<TreeNode> stack = new ArrayDeque();

        while (stack.size() != 0 || root != null){
            while (root != null) {
                System.out.print(root.val + " ");
                stack.addFirst(root);
                root = root.left;
            }
            root = stack.pollFirst();
            root = root.right;
        }

    }

    //中序遍历
    //递归
    public static void midOrerRecursion(TreeNode root){
        if (root == null) return;
        if (root.left != null) midOrerRecursion(root.left);
        System.out.print(root.val + " ");
        if (root.right != null) midOrerRecursion(root.right);
    }
    //循环
    public static void midOrerLoop(TreeNode root){
        if (root == null) return;

        Deque<TreeNode> stack = new ArrayDeque();

        while (stack.size() != 0 || root != null){
            while (root != null){
                stack.addFirst(root);
                root = root.left;
            }
            root = stack.pollFirst();
            System.out.print(root.val + " ");
            root = root.right;
        }
    }

    //后序遍历
    //递归
    public static void postOrderRecursion(TreeNode root){
        if (root == null) return;
        if (root.left != null) postOrderRecursion(root.left);
        if (root.right != null) postOrderRecursion(root.right);
        System.out.print(root.val + " ");
    }
    //循环
    public static void postOrderLoop(TreeNode root) {
        if (root == null) return;

        Deque<TreeNode> stack = new ArrayDeque<>();
        //记录返回的父节点是左还是右
        Deque<Character> help = new ArrayDeque<>();

        while (stack.size() != 0 || root != null){
            while (root != null){
                stack.addFirst(root);
                help.addFirst('L');
                root = root.left;
            }
            while (!stack.isEmpty() && help.getFirst() == 'R'){
                help.pollFirst();
                System.out.print(stack.pollFirst().val + " ");
            }
            if (!stack.isEmpty() && help.getFirst() == 'L') {
                help.pollFirst();
                help.addFirst('R');
                root = stack.getFirst().right;
              }

        }

    }

    public static void main(String[] args) {
        TreeNode root = buildTreeNode();
        preOrderRecursion(root);
        System.out.println();
        preOrderLoop(root);
        System.out.println();
        System.out.println("================================");
        midOrerRecursion(root);
        System.out.println();
        midOrerLoop(root);
        System.out.println();
        System.out.println("================================");
        postOrderRecursion(root);
        System.out.println();
        postOrderLoop(root);

    }

    private static TreeNode buildTreeNode(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        return root;
    }
}
