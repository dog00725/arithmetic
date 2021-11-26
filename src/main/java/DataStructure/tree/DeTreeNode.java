package DataStructure.tree;

public class DeTreeNode<T> extends TreeNode{
    T val;
    DeTreeNode left;
    DeTreeNode right;
    DeTreeNode parent;

    DeTreeNode(T x) {
        super();
        val = x;
    }
}
