package amazon;

/*
一共用了两种办法，都用的这个链接里面的思路：
https://leetcode-cn.com/problems/copy-list-with-random-pointer/solution/liang-chong-shi-xian-tu-jie-138-fu-zhi-dai-sui-ji-/

注意点：
方法一：
1. 在最后分离的时候，一定要记得对原node和新node要完全分开，通俗一点就是说要复原原node，在这个基础上生成新node
   否则会报错“Next pointer of node with label 7 from the original list was modified.”
   原因就是因为原node没有复原

方法一：
1. 要注意放入HashMap value值的Node是单独的，，需要新建
* */

import java.util.HashMap;

public class lc138 {
//    方法一：有点事省内存，速度和方法二差不多
//    public Node copyRandomList(Node head) {
//        if(head == null) return null;
//        Node temp = head;
//        while (temp != null){
//            Node copyNode = new Node(temp.val);
//            Node oriNext = temp.next;
//            temp.next = copyNode;
//            copyNode.next = oriNext;
//            temp = temp.next.next;
//        }
//        temp = head;
//        while (temp != null && temp.next != null){
//            if(temp.random == null){
//                temp.next.random = null;
//            }else{
//                temp.next.random = temp.random.next;
//            }
//            temp = temp.next.next;
//        }
//
////        这里的代码实现就是要分开这两个Node成为两个独立的Node
//        Node dummyHead = new Node(-1);
//        temp = head;
//        Node newRes = dummyHead;
//        while (temp != null){
//            newRes.next = temp.next;
//            newRes = newRes.next;
//            temp.next = newRes.next;
//            temp = temp.next;
//        }
//        return dummyHead.next;
//    }

//    方法二：优点是比较容易想到，我是先做的这个方法，在做出来的方法一
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        HashMap<Node, Node> hashMap = new HashMap<>();
        Node temp = head;
        while (temp != null){
            hashMap.put(temp, new Node(temp.val));
            temp = temp.next;
        }

        Node temp1 = head;
        Node newHead = hashMap.get(head);
        while (temp1 != null){
            Node newNode = hashMap.get(temp1);
            newNode.next = hashMap.get(temp1.next);
            newNode.random = hashMap.get(temp1.random);
            temp1 = temp1.next;
        }

        return newHead;
    }


    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static void main(String[] args) {
//        [[7,null],[13,0],[11,4],[10,2],[1,0]]
        Node n0 = new Node(7);
        Node n1 = new Node(13);
        Node n2 = new Node(11);
        Node n3 = new Node(10);
        Node n4 = new Node(1);
        n0.random = null;
        n1.random = n0;
        n2.random = n4;
        n3.random = n2;
        n4.random = n0;
        n0.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        System.out.println(new lc138().copyRandomList(n0));
    }
}
