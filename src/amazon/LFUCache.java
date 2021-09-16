package amazon;

/*
对应lc460
146/460/432 这三道题目其实本质上都是一个思路，那就是使用HashMap + 双向链表，所以一起服用

这道题目感觉liweiwei讲的也不是很清楚，但是另一个倒是用图把所有问题都将清楚了，可以参考
https://leetcode-cn.com/problems/lfu-cache/solution/chao-xiang-xi-tu-jie-dong-tu-yan-shi-460-lfuhuan-c/

代码的话参考另一个，更符合我的逻辑：
https://leetcode-cn.com/problems/lfu-cache/solution/java-13ms-shuang-100-shuang-xiang-lian-biao-duo-ji/

这种题目最好就是要先写伪代码，把逻辑理顺，这样会清晰很多
* */

import java.util.HashMap;
import java.util.Map;

public class LFUCache {
    public class Node{
        int key, val, freq;
        Node pre, next;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
            freq = 1;
        }
    }

    public class DoubleLinkedList{
        private Node head = new Node(-1, -1);
        private Node tail = new Node(-1, -1);
        private int size;
        public DoubleLinkedList(){
            head.next = tail;
            tail.pre = head;
            size = 0;
        }

        public void addFirst(Node newHead){
            Node temp = head.next;
            head.next = newHead;
            newHead.pre = head;
            newHead.next = temp;
            temp.pre = newHead;
            size++;
        }

        public void remove(Node removeNode){
            Node tempPre = removeNode.pre;
            Node tempNext = removeNode.next;
            tempPre.next = tempNext;
            tempNext.pre = tempPre;
            size--;
        }

        public Node removeLast(){
            Node tempRemove = tail.pre;
            remove(tempRemove);
            return tempRemove;
        }

        public int size(){
            return size;
        }
    }

//    注意这个size不是dbList的size，而是整个LFU的size，
//    因为和LRU不同的是LFU的doubleList其实是分散在不同的count里面的，所以要用另一个全局size去控制
    private int capacity, size, minFreq;
    private HashMap<Integer, Node> hashMap;
    private HashMap<Integer, DoubleLinkedList> count;
//    private DoubleLinkedList dbList;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        size = 0;
        minFreq = 0;
        hashMap = new HashMap<>();
        count = new HashMap<>();
//        dbList = new DoubleLinkedList();
    }

    public int get(int key) {
        if(!hashMap.containsKey(key)) return -1;
        Node curNode = hashMap.get(key);
        addFreq(curNode);
        return curNode.val;
    }

    public void put(int key, int value) {
        if(capacity == 0) return;
        Node curNode = hashMap.get(key);
        Node newNode = new Node(key, value);
        if(hashMap.containsKey(key)){
            curNode.val = value;
            addFreq(curNode);
        }else {
            if(size >= capacity){
                DoubleLinkedList rmList = count.get(minFreq);
                Node rmNode = rmList.tail.pre;
//                易错
                hashMap.remove(rmNode.key);
                rmList.remove(rmNode);
                size--;
            }
            hashMap.put(key, newNode);
            DoubleLinkedList newList = count.get(1);
            if(newList == null){
                newList = new DoubleLinkedList();
                count.put(1, newList);
            }
            newList.addFirst(newNode);
            size++;
            minFreq = 1;
        }
    }

//    这是和lc146最大的不同,而且这里仅仅是处理count，
//    不要处理hashMap，这个会在put里面处理的
    private void addFreq(Node node){
        int curFreq = node.freq;
        DoubleLinkedList curList = count.get(curFreq);
        curList.remove(node);
        if(minFreq == curFreq && curList.head.next == curList.tail) minFreq++;
        node.freq++;
        int newFreq = node.freq;
        DoubleLinkedList newList = count.get(newFreq);
        if(newList == null){
            newList = new DoubleLinkedList();
            count.put(newFreq, newList);
        }
        newList.addFirst(node);
    }





    public static void main(String[] args) {
//        LFUCache test = new LFUCache(2);
//        test.put(1,1);
//        test.put(2,2);
//        System.out.println(test.get(1) + " 预期 1");    //预期 1
//        test.put(3,3);
//        System.out.println(test.get(2) + " 预期 -1");    //预期 -1
//        System.out.println(test.get(3) + " 预期 3");    //预期 3
//        test.put(4,4);
//        System.out.println(test.get(1) + " 预期 -1");    //预期 -1
//        System.out.println(test.get(3) + " 预期 3");    //预期 3
//        System.out.println(test.get(4) + " 预期 4");    //预期 4

        LFUCache test = new LFUCache(1);
        test.put(2,1);
        System.out.println(test.get(2) + " 预期 1");    //预期 1
        test.put(3,2);
//        注意这里出-1是对的，因为根据逻辑来说，如果超出了capacity需要先删除里面的元素，
//        所以(2,1)这个node是被删除的，然后在添加了(3,2)
        System.out.println(test.get(2) + " 预期 -1");    //预期 -1
        System.out.println(test.get(3) + " 预期 2");    //预期 3
    }
}
