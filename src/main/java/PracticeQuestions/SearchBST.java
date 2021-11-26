package PracticeQuestions;

import java.util.ArrayDeque;
import java.util.Deque;

public class SearchBST {
    public TreeNode searchBST(TreeNode root, int val) {
        if(null == root || root.val == val){
            return root;
        }
        return root.val > val ? searchBST(root.left,val) : searchBST(root.right,val);
    }

    //以下解法适用于普通二叉树，二叉搜索树可以利用本身的特点进行搜索
    //BFS
    private TreeNode bfs(TreeNode root, int val){
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (node.val == val){
                return node;
            }
            if (null != node.left){
                queue.offer(node.left);
            }
            if (null != node.right){
                queue.offer(node.right);
            }
        }
        return null;
    }

    //DFS
    private TreeNode dfs(TreeNode root, int val){
        if (root.val == val){
            return root;
        }
        TreeNode node = null;
        if (null != root.left){
            node = dfs(root.left, val);
        }
        if (null != node) return node;
        if (null != root.right){
            node = dfs(root.right, val);
        }
        return node;
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

}


