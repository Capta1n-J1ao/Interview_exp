package amazon;

/*
对应lc146
146/460/432 这三道题目其实本质上都是一个思路，那就是使用HashMap + 双向链表，所以一起服用

这个题解讲得很清楚了，代码参考下面评论里面Vermouth：
https://leetcode-cn.com/problems/lru-cache/solution/lru-ce-lue-xiang-jie-he-shi-xian-by-labuladong/

提示点：
1. 注意这个put函数这个命令一定要同步删除掉hashMap 和 dbList里面的key，不能留着，想一想怎么做
2. 建议先写put函数，再写get函数，这样更加轻松。
* */

import java.util.HashMap;

public class LRUCache {
//    public class Node{
//        int key;
//        int value;
////        应该写在这个类里面，而不是doubleList
//        Node next, pre;
//        public Node(int key, int value){
//            this.key = key;
//            this.value = value;
//        }
//    }
//
//    public class doubleList{
//        Node head = new Node(0,0);
//        Node tail = new Node(0,0);
//        int size;
//        public doubleList(){
//            head.next = tail;
//            tail.pre = head;
//            size = 0;
//        }
//
//        public void addFirst(Node newHead){
//            Node temp = head.next;
//            head.next = newHead;
//            newHead.pre = head;
//            newHead.next = temp;
//            temp.pre = newHead;
//            size++;
//        }
//
//        public Node pollLast(){
//            Node temp = tail.pre;
//            remove(temp);
//            return temp;
//        }
//
//        public void remove(Node removeNode){
//            Node rmpre = removeNode.pre;
//            Node rmnext = removeNode.next;
//            rmpre.next = rmnext;
//            rmnext.pre = rmpre;
//            size--;
//        }
//        private int size(){
//            return size;
//        }
//    }
//
//    private int capacity;
////    注意这里是Node，很精髓
//    private HashMap<Integer, Node> hashMap;
//    private doubleList dbList;
//    public LRUCache(int capacity) {
//        this.capacity = capacity;
//        hashMap = new HashMap<>();
//        dbList = new doubleList();
//    }
//
//    public int get(int key) {
//        if(!hashMap.containsKey(key)) return -1;
//        Node getNode = hashMap.get(key);
//        put(key, getNode.value);
//        return getNode.value;
//    }
//
////    整个数据结构这个函数最容易错，排查了半小时
//    public void put(int key, int value) {
//        Node putNode = new Node(key, value);
//        if(!hashMap.containsKey(key)){
//            if(dbList.size >= capacity){
//                Node lastNode = dbList.pollLast();
////                这句也容易错
//                hashMap.remove(lastNode.key);
//            }
//            dbList.addFirst(putNode);
//            hashMap.put(key, putNode);
//        }else {
////            查了好久才找到
////            dbList.remove(putNode);
//            dbList.remove(hashMap.get(key));
//            dbList.addFirst(putNode);
////            题目里面put的作用第一句话就说明了下面的情况
//            hashMap.put(key, putNode);
//        }
//    }

//    以下这个写法无效，主要原因还是没有搞清楚Node到底指代的是哪个
//    public int get(int key) {
//        if(!hashMap.containsKey(key)) return -1;
//        return hashMap.get(key).val;
//    }
//
//    public void put(int key, int value) {
//        Node node = new Node(key, value);
//        if(!hashMap.containsKey(key)){
//            if(capacity <= doubleLinkedList.size()){
//                doubleLinkedList.removeLast();
////                下面这句一开始忘了，要记住是对应的关系
//                hashMap.remove(key);
//            }
//            hashMap.put(key, node);
//            doubleLinkedList.addFirst(node);
//        }else {
//            doubleLinkedList.remove(node);
//            doubleLinkedList.addFirst(node);
//        }
//    }

//    三刷 2021/03/07

    public class Node{
        int key, val;
        Node pre, next;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    public class DoubleLinkedList{
        int size;
        Node head = new Node(-1, -1);
        Node tail = new Node(-1, -1);
        public DoubleLinkedList(){
            size = 0;
            head.next = tail;
            tail.pre = head;
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

    int capacity;
    HashMap<Integer, Node> hashMap;
    DoubleLinkedList dbList;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        hashMap = new HashMap<>();
        dbList = new DoubleLinkedList();
    }

    public int get(int key) {
        if(!hashMap.containsKey(key)) return -1;
        Node temp = hashMap.get(key);
        put(key, temp.val);
        return temp.val;
    }

//    这里有如下要点
//    1. hashMap的put是可以覆盖掉之前的值的
//    2. put这个命令一定要同步删除掉hashMap 和 dblist里面的key，
//    不能留着，这也是hashMap.remove(lastNode.key);这句话的作用
    public void put(int key, int value) {
        Node putNode = new Node(key, value);
        if(hashMap.containsKey(key)){
            Node rmNode = hashMap.get(key);
            dbList.remove(rmNode);
        }else {
            if(dbList.size >= capacity){
                Node lastNode = dbList.removeLast();
                hashMap.remove(lastNode.key);
            }
        }
        dbList.addFirst(putNode);
        hashMap.put(key, putNode);
    }
}
