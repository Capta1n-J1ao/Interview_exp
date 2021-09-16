package amazon;

/*
可以尝试只用一个queue来实现
* */

import java.util.LinkedList;
import java.util.Queue;

public class lc225 {
    /** Initialize your data structure here. */
    Queue<Integer> queue;

    //和之前一样，如果加了void就不能够编译
    public void MyStack() {
        //public MyStack() {
        queue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        int qLen = queue.size();
        queue.add(x);
        for(int i = 0; i < qLen; i++){
            queue.add(queue.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.remove();
    }

    /** Get the top element. */
    public int top() {
        return queue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
