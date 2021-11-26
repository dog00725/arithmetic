package DataStructure.tree;

import java.util.ArrayDeque;
import java.util.Deque;

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

    //-----重建二叉树-----
    /*
     * 前序遍历列表的第一个是根节点
     * 在中序遍历中找到根节点，根节点前面的是左子树，后面的右子树
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return construct(preorder,0, preorder.length-1,inorder,0, inorder.length-1);
    }

    private static TreeNode construct(int[] preorder,int preStart,int preEnd, int[] inorder,int inStart, int inEnd){
        if(preStart>preEnd||inStart>inEnd){
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);
        //查找中序遍历中根节点的小标
        int index = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == preorder[preStart]){
                index = i;
                break;
            }
        }
        root.left = construct(preorder,preStart+1,preStart+index-inStart,inorder,inStart,index-1);
        root.right = construct(preorder,preStart+index-inStart+1,preEnd,inorder,index+1,inEnd);
        return root;
    }

    //给定双向二叉树和一个节点，找出中序遍历的下一个节点
    /*
     * 可分成四种情况
     * 1.目标节点有右子树：下一个节点是右子树的最左子节点
     * 2.目标节点没有右子树且是父节点的左子节点：下一个节点就是父节点
     * 3.目标节点没有右子树且是父节点的右子树：向上遍历直到某节点是它父节点的左子节点，下一个节点则是改父节点
     */
    public static DeTreeNode getNextInOrder(DeTreeNode root,DeTreeNode target){
        if (target == null) return null;

        if (target.right != null){
            DeTreeNode nextNode = target.right;
            while (nextNode.left != null){
                nextNode = nextNode.left;
            }
            return nextNode;
        }
        if (target.right == null && target == target.parent.left){
            return target.parent;
        }
        if (target.right == null && target == target.parent.right) {
            DeTreeNode nextNode = target.parent;
            while (nextNode != nextNode.parent.left){
                nextNode = nextNode.parent;
            }
            return nextNode.parent;
        }

        return null;
    }

    public static void main(String[] args) {
        TreeNode root = buildTreeNode();
        System.out.println("===============前序遍历=================");
        preOrderRecursion(root);
        System.out.println();
        preOrderLoop(root);
        System.out.println();
        System.out.println("===============中序遍历=================");
        midOrerRecursion(root);
        System.out.println();
        midOrerLoop(root);
        System.out.println();
        System.out.println("===============后序遍历=================");
        postOrderRecursion(root);
        System.out.println();
        postOrderLoop(root);
        System.out.println();

        System.out.println("===============重建二叉树================");
        int[] preOrder = {1,2,4,5,3,6,7};
        int[] inOrder = {4,2,5,1,6,3,7};
        root = buildTree(preOrder,inOrder);
        System.out.print("前序遍历：");
        preOrderRecursion(root);
        System.out.println();
        System.out.print("中序遍历：");
        midOrerRecursion(root);
        System.out.println();

        System.out.println("=======================================");
        System.out.println("给定双向二叉树和一个节点，找出中序遍历的下一个节点");
        DeTreeNode deRoot = buildDeTreeNode();
        System.out.println(deRoot.left.val + " next " + getNextInOrder(deRoot,deRoot.left).val);
        System.out.println(deRoot.left.left.val + " next " + getNextInOrder(deRoot,deRoot.left.left).val);
        System.out.println(deRoot.left.right.right.val + " next " + getNextInOrder(deRoot,deRoot.left.right.right).val);
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

    private static DeTreeNode buildDeTreeNode(){
        DeTreeNode<Character> root = new DeTreeNode('a');
        root.left = new DeTreeNode<Character>('b');
        root.left.parent = root;
        root.right = new DeTreeNode<Character>('c');
        root.right.parent = root;

        root.left.left = new DeTreeNode<Character>('d');
        root.left.left.parent = root.left;
        root.left.right = new DeTreeNode<Character>('e');
        root.left.right.parent = root.left;

        root.left.right.left = new DeTreeNode<Character>('h');
        root.left.right.left.parent = root.left.right;
        root.left.right.right = new DeTreeNode<Character>('i');
        root.left.right.right.parent = root.left.right;

        root.right.left = new DeTreeNode<Character>('f');
        root.right.left.parent = root.right;
        root.right.right = new DeTreeNode<Character>('g');
        root.right.right.parent = root.right;

        return root;
    }

}
