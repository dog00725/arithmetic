package ElementaryAlgorithm.ListzNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    //-----从尾头打印链表-----
    /*
     * 是否允许修改数据结构
     * 递归本质就是一个栈结构,使用递归可能造成函数调用栈溢出
     * 直接使用栈
     */
    public static int[] reversePrint(ListNode head) {
        List<Integer> target = new ArrayList<>();
        addNode(head,target);
        int[] result = new int[target.size()];
        for (int i = 0; i < target.size(); i++) {
            result[i] = target.get(i);
        }
        return result;
    }

    private static void addNode(ListNode node, List<Integer> list){
        if (null == node) {
            return;
        }
        addNode(node.next,list);
        list.add(node.val);
    }

    public static void main(String[] args) {
        //-----从尾头打印链表-----
        ListNode head = buildListNode();
        System.out.println(Arrays.toString(reversePrint(head)));

    }
    private static ListNode buildListNode(){
        ListNode head = new ListNode(1);
        ListNode temp = head;
        for (int i = 2; i <= 5; i++) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        return head;
    }
}
