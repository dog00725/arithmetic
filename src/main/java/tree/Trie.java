package tree;

import org.apache.commons.lang3.StringUtils;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 前缀树
 */
public class Trie {

    private TrieNode root;
    public Trie(){
        root = new TrieNode();
    }

    //插入一个字符串到前缀树
    public void insertString(String str){
        if (StringUtils.isBlank(str)){
            return;
        }
        TrieNode node = root;
        TrieNode tempNode = null;
        for (int i = 0; i < str.length(); i++) {
            Character word = str.charAt(i);
            tempNode = node.content.get(word);
            if (tempNode == null){
                tempNode = new TrieNode();
                node.content.put(word,tempNode);
            }
            node = tempNode;
        }
        node.isLeaf = true;
    }

    //搜索一个字符串是否存在在前缀树
    public boolean search(String str){
        if (str == null || str.length() == 0){
            return false;
        }
        int length = str.length();
        TrieNode node = root;
        for (int i = 0; i < length; i++) {
            char word = str.charAt(i);
            node = node.content.get(word);
            if (node == null){
                return false;
            }
        }
        return node.isLeaf;
    }

    public boolean startWith(String prefix){
        if (StringUtils.isBlank(prefix)){
            return false;
        }
        int length = prefix.length();
        TrieNode node = root;
        for (int i = 0; i < length; i++) {
            char word = prefix.charAt(i);
            node = node.content.get(word);
            if (null == node){
                return false;
            }
        }
        return true;
    }

    /**
     * 前缀树节点
     */
    class TrieNode{
        boolean isLeaf;
        Map<Character, TrieNode> content;

        TrieNode(){
            this.content = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        Trie tree = new Trie();
        tree.insertString("apple");
        System.out.println(tree.search("appl"));
        System.out.println(tree.startWith("app"));
    }
}
