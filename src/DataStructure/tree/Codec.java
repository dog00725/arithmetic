package DataStructure.tree;

import java.util.*;
/**
 * @Author: huqs
 * @Date: 2021/10/4 16:32
 */
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> list = new ArrayList<>();
        codeTraversePost(root,list);
        StringBuilder builder = new StringBuilder();
        for(String str : list){
            builder.append(str+",");
        }
        return builder.substring(0,builder.length()-1);
    }
    //前序遍历实现
//    private void codeTraversePre(TreeNode root, List<String> list){
//        if(root == null) {
//            list.add("null");
//            return;
//        }else{
//            list.add(String.valueOf(root.val));
//        }
//        codeTraversePre(root.left,list);
//        codeTraversePre(root.right,list);
//    }
    //后序遍历实现
    private void codeTraversePost(TreeNode root, List<String> list){
        if(root == null) {
            list.add("null");
            return;
        }
        codeTraversePost(root.left,list);
        codeTraversePost(root.right,list);

        list.add(String.valueOf(root.val));
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        LinkedList<String> list = new LinkedList<>(Arrays.asList(data.split(",")));
        return decodeTraversePost(list);
    }

//    //前序遍历
//    private TreeNode decodeTraversePre(LinkedList<String> list){
//        String str= list.removeFirst();
//        TreeNode root = null;
//        if (str.equals("null")){
//            return root;
//        }else{
//            root = new TreeNode(Integer.parseInt(str));
//        }
//        root.left = decodeTraversePre(list);
//        root.right = decodeTraversePre(list);
//        return root;
//    }

    //后序遍历
    private TreeNode decodeTraversePost(LinkedList<String> list){
        String str= list.removeLast();
        TreeNode root = null;
        if (str.equals("null")){
            return root;
        }else{
            root = new TreeNode(Integer.parseInt(str));
        }
        root.right = decodeTraversePost(list);
        root.left = decodeTraversePost(list);
        return root;
    }

    public static void main(String[] args) {
        Codec codec = new Codec();

    }
}
