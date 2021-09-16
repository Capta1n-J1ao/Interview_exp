package amazon;

/*
对应lc622,这道题目的核心精髓就是有head和tail两个node来形成循环数据结构
这个题解和我的思路一样，都是用双链表来做：
https://leetcode-cn.com/problems/design-circular-queue/solution/shuang-xiang-lian-biao-by-javalj-3-sd81/
但是虽然做出来了，但是好像没有用到循环的结构

所以改了另一个题解，他用到的是循环队列，而且单链表就够了，代码参考评论里面“这有一只秀儿”：
https://leetcode-cn.com/problems/design-circular-queue/solution/she-ji-xun-huan-dui-lie-by-leetcode/
* */

import java.util.List;

public class MyCircularQueue {
//    private class doubleListNode{
//        private doubleListNode pre, next;
//        private int val;
//        public doubleListNode(){
//        }
//
//        public doubleListNode(int value){
//            this.val = value;
//        }
//    }
//
//    private int size, capacity;
//    private doubleListNode head, tail;
//    public MyCircularQueue(int k) {
//        size = 0;
//        capacity = k;
//        head = new doubleListNode();
//        tail = new doubleListNode();
//    }
//
//    public boolean enQueue(int value) {
////        为了节省一个全局变量的node，让每次新加进来的node都放在head的后面
//        if(size >= capacity) return false;
//        doubleListNode cur = new doubleListNode(value);
//        if(head.next == null){
//            head.next = cur;
//            cur.pre = head;
//            tail.pre = cur;
//            cur.next = tail;
//        }else {
//            doubleListNode temp = head.next;
//            head.next = cur;
//            cur.pre = head;
//            cur.next = temp;
//            temp.pre = cur;
//        }
//        size++;
//        return true;
//    }
//
//    public boolean deQueue() {
//        if(size <= 0) return false;
//        if(size == 1){
//            head.next = null;
//            head.pre = null;
//            tail.next = null;
//            tail.pre = null;
//        }else {
//            doubleListNode temp = tail.pre;
//            tail.pre = temp.pre;
//            temp.pre.next = tail;
//        }
//        size--;
//        return true;
//    }
//
//    public int Front() {
//        if(size <= 0) return -1;
//        return tail.pre.val;
//    }
//
//    public int Rear() {
//        if(size <= 0) return -1;
//        return head.next.val;
//    }
//
//    public boolean isEmpty() {
//        return size == 0;
//    }
//
//    public boolean isFull() {
//        return size == capacity;
//    }

//    方法二，真正的循环链表
    public class ListNode {
        int val;
        ListNode next;

        public ListNode(int x){
            val = x;
            next = null;
        }
    }

    private ListNode head, tail;
    private int size, capacity;
    public MyCircularQueue(int k) {
        capacity = k;
        size = 0;
    }

    public boolean enQueue(int value) {
        if(size >= capacity) return false;
        if(size == 0){
            ListNode cur = new ListNode(value);
            head = cur;
            tail = cur;
        }else {
//            这里其实就是实现循环队列的关键语句
            if(tail.next == null) tail.next = new ListNode(value);
            else tail.next.val = value;
            tail = tail.next;
            if(size == capacity - 1) tail.next = head;
        }
        size++;
        return true;
    }

    public boolean deQueue() {
        if(size <= 0) return false;
//        这里其实只需要改变head的顺序就行，
//        不用删除节点或者其他，因为是循环的
        head = head.next;
        size--;
        return true;
    }

    public int Front() {
        if(size <= 0)return -1;
        return head.val;
    }

    public int Rear() {
        if(size <= 0)return -1;
        return tail.val;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}
