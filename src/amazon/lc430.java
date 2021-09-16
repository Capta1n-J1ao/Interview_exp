package amazon;

/*
一开始连题目都没看懂，题目中扁平化的意思其实就是将 child 指针指向的列表合并到父列表
然后还要注意Node出来一定要双向的，也就是说prev/next都得有值
个人认为官解的方法二，迭代的中序遍历深度搜索最符合我的想法，其实就是借鉴了中序遍历的解法：
https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list/solution/bian-ping-hua-duo-ji-shuang-xiang-lian-biao-by-lee/
* */

import java.util.ArrayDeque;
import java.util.Deque;

public class lc430 {
    public Node flatten(Node head) {
        if(head == null) return head;
        Deque<Node> stack = new ArrayDeque<>();
        stack.add(head);
        Node preHead = new Node(0,null, head, null);
        Node pre = preHead, next = preHead;
        while (!stack.isEmpty()){
            Node curNode = stack.poll();
            System.out.println( curNode.val);
            curNode.prev = pre;
            pre.next = curNode;
//            注意用DEQUE做Stack一定要用addFirst,不能用add，否则不是后进先出
//            或者用Push也可以，反正不能用add!!!
            if(curNode.next != null) stack.push(curNode.next);
            if(curNode.child != null) {
                stack.push(curNode.child);
//                下面这步很关键，相当于curNode不能有child了
                curNode.child = null;
            }
            pre = curNode;
        }
        preHead.next.prev = null;
        return preHead.next;
    }

    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
//        这里需要额外加一个方法
        public Node(){};
        public Node(int _val,Node _prev,Node _next,Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    };

    public static void main(String[] args) {
        Node n1 = new Node();
        Node n2 = new Node();
        Node n3 = new Node();
        Node n4 = new Node();
        n1.val = 1;
        n2.val = 2;
        n3.val = 3;
        n4.val = 4;
        n1.next = n2;
        n2.next = n3;
        n2.child = n4;
        new lc430().flatten(n1);
    }
}
