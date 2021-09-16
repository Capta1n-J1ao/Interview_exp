package amazon;

/*
一刷的方法其实并没有做到O(1),下面这个方法是O(1)的，下面这个题解最清楚
https://leetcode-cn.com/problems/implement-queue-using-stacks/solution/tu-jie-guan-fang-tui-jian-ti-jie-yong-zh-4hru/
* */

import java.util.ArrayDeque;
import java.util.Deque;

public class MyQueue {
    /** Initialize your data structure here. */
    private Deque<Integer> stack1;
    private Deque<Integer> stack2;
    public MyQueue() {
        stack1 = new ArrayDeque<>();
        stack2 = new ArrayDeque<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stack1.addFirst(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(!stack2.isEmpty()) return stack2.pollFirst();
        while (!stack1.isEmpty()) stack2.addFirst(stack1.pollFirst());
        return stack2.pollFirst();
    }

    /** Get the front element. */
    public int peek() {
        if(!stack2.isEmpty()) return stack2.peekFirst();
        while (!stack1.isEmpty()) stack2.addFirst(stack1.pollFirst());
        return stack2.peekFirst();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
