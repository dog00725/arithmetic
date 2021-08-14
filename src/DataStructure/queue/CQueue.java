package DataStructure.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 使用两个栈实现队列
 * 两个队列实现栈原理相同
 */
public class CQueue {
    private Deque<Integer> stack1;
    private Deque<Integer> stack2;
    public CQueue() {
        stack1 = new ArrayDeque<>();
        stack2 = new ArrayDeque<>();
    }

    public void appendTail(int value) {
        stack1.addFirst(value);
    }

    public int deleteHead() {
        if (stack1.isEmpty()){
            return -1;
        }
        while (!stack1.isEmpty()){
            stack2.addFirst(stack1.pollFirst());
        }
        int value = stack2.pollFirst();
        while (!stack2.isEmpty()){
            stack1.addFirst(stack2.pollFirst());
        }
        return value;
    }
}
