package DataStructure.ListzNode;

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

    //剑指 Offer 23. 链表中环的入口节点
    /*
     * 先判断是否有环 - 两个指针，一个前进的快一个前进的慢。当快的指针到达末尾时如果没有追上慢的指针则不存在环.存在环时返回两指针相遇的节点
     * 利用环中的节点计算环的节点数量，绕环当回到远点则环循环完一圈
     * 两个指针p1、p2，p2前进n（环中的节点数）葛节点，两个节点同时前进，当两者相遇时此时的节点就是环的开始节点
     */
    public static ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode nodeInLoop = meetingNode(head);
        if (nodeInLoop == null) return null;
        Integer loopCount = getLoopCount(nodeInLoop);
        ListNode p1 = head;
        ListNode p2 = head;
        for (int i = 0; i < loopCount; i++) {
            p2 = p2.next;
        }
        while (p2 != p1){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    private static ListNode meetingNode(ListNode head){
        ListNode p1 = head;
        ListNode p2 = head;

        while (p2 !=null){
            p1 = p1.next;
            if (p2.next == null || p2.next.next == null) return null;
            p2 = p2.next.next;
            if (p1 == p2) return p1;
        }
        return null;
    }

    private static Integer getLoopCount(ListNode node){
        ListNode flag = node;
        Integer count = 0;
        while (node != null){
            count++;
            node = node.next;
            if (node == flag) break;
        }
        return count;
    }

    public static void main(String[] args) {
        //-----从尾头打印链表-----
        ListNode head = buildListNode();
        System.out.println(Arrays.toString(reversePrint(head)));
        //-----链表中环的入口节点-----
        head = buildListNode2();
        System.out.println(detectCycle(head).val);
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

    private static ListNode buildListNode2(){
        ListNode head = new ListNode(-1);
        ListNode n1 = new ListNode(-7);
        ListNode n2 = new ListNode(7);
        ListNode n3 = new ListNode(-4);
        ListNode n4 = new ListNode(19);
        ListNode n5 = new ListNode(6);
        ListNode n6 = new ListNode(-9);
        ListNode n7 = new ListNode(-5);
        ListNode n8 = new ListNode(-2);

        head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n7;
        return head;
    }
}
