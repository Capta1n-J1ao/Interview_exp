package amazon;

/*
对应lc432

146/460/432 这三道题目其实本质上都是一个思路，那就是使用HashMap + 双向链表，所以一起服用
这道题目个人感觉理解题目意思比较难，一直没理解，看完题解以后才懂含义，这里我就详细举个例子：
["AllOne","inc","inc","getMaxKey","getMinKey","inc","getMaxKey","getMinKey","dec"]
[[],["hello"],["hello"],[],[],["leet"],[],[],[]]
这时候hello的值为2，leet的值为1，这样getMaxKey就得到hello，getMinKey就得到leet，
然后最后一个dec的话就是hello的val变为1，这样hello和leet的val都是1
这时候如果在调用getMaxKey/getMinKey，hello和leet都有可能出现
就是根据他们的值得大小进行选择的，那么这时候有一个很直观的办法， 并且也是我想到的思路：
一个hash存key，value；
一个hash存value，keyset
另外维护两个变量，最大、最小value

时间复杂度 O(1)；后面有一个 getMin() 操作 会劣化。。。
空间复杂度 O(n)，n是不重复的、现存的key的个数
而且有一个人竟然跟我同样的思路，题解：
https://leetcode-cn.com/problems/all-oone-data-structure/solution/javashuang-ha-xi-shi-xian-by-swing-8/


但是，为了和之前LRU和LFU结合起来，并且将方法统一，所以还是使用了HashMap + 双向链表
题解参考下面“金木盐”的评论，比题解本身更加清晰：
https://leetcode-cn.com/problems/all-oone-data-structure/solution/java-mapshuang-lian-biao-by-gyz147/
* */

import java.util.HashMap;
import java.util.HashSet;

public class AllOne {
//    虽然用的类是一样的，但是对于doubleList来说需要不同的方法来实现功能
    public class Node{
        int val;
        Node pre, next;
        HashSet<String> keys;
        public Node(int val){
            this.val = val;
            this.keys = new HashSet<>();
        }
    }

    public class doubleList{
        Node head, tail;
        public doubleList(){
            head = new Node(-1);
            tail = new Node(-1);
            head.next = tail;
            tail.pre = head;
        }

        public void preAdd(Node n1, Node toAdd){
            Node preN1 = n1.pre;
            preN1.next = toAdd;
            toAdd.next = n1;
            toAdd.pre = preN1;
            n1.pre = toAdd;
        }

        public void postAdd(Node n1, Node toAdd){
            Node postN1 = n1.next;
            n1.next = toAdd;
            toAdd.next = postN1;
            postN1.pre = toAdd;
            toAdd.pre = n1;
        }

        public void remove(Node n){
            n.pre.next = n.next;
            n.next.pre = n.pre;
        }
    }


    HashMap<String, Node> hashMap;
    doubleList dbList;
    /** Initialize your data structure here. */
    public AllOne() {
        hashMap = new HashMap<>();
        dbList = new doubleList();
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if(hashMap.containsKey(key)){
            Node curNode = hashMap.get(key);
            Node next = curNode.next;
            if(curNode.val + 1 != next.val){
                next = new Node(curNode.val + 1);
                dbList.postAdd(curNode, next);
            }
            curNode.keys.remove(key);
            next.keys.add(key);
            if(curNode.keys.isEmpty()) dbList.remove(curNode);
            hashMap.put(key, next);
        }else {
            Node newNode = dbList.head.next;
            if(newNode.val != 1){
                newNode = new Node(1);
                dbList.postAdd(dbList.head, newNode);
            }
            newNode.keys.add(key);
            hashMap.put(key, newNode);
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(!hashMap.containsKey(key)) return;
        Node curNode = hashMap.get(key);
        if(curNode.val == 1){
            curNode.keys.remove(key);
            if(curNode.keys.isEmpty()) {
                dbList.remove(curNode);
                hashMap.remove(key);
            }
        }else {
            Node pre = curNode.pre;
            if(pre.val != curNode.val - 1){
                pre = new Node(curNode.val - 1);
                dbList.preAdd(curNode, pre);
            }
            pre.keys.add(key);
            curNode.keys.remove(key);
            if(curNode.keys.isEmpty()) dbList.remove(curNode);
            hashMap.put(key, pre);
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        Node maxKey = dbList.tail.pre;
//        这里想了半天还真的只有iterator时最合适的
        return maxKey.val > 0 ? maxKey.keys.iterator().next() : "";
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        Node minKey = dbList.head.next;
        return minKey.val > 0 ? minKey.keys.iterator().next() : "";
    }
}
