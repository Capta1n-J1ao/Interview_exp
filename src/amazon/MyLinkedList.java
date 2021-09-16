package amazon;

/*
没有难度，时间不够可以跳过
* */

public class MyLinkedList {
    public class Node{
        public int val;
        public Node pre, next;
        public Node(){};
        public Node(int val) {
            this.val = val;
        }
        public Node (int val, Node pre, Node next){
            this.val = val;
            this.pre = pre;
            this.next = next;
        }
    }

    /** Initialize your data structure here. */
    private Node headNode, tailNode;
    private int size;
    public MyLinkedList() {
        headNode = new Node();
        tailNode = new Node();
        headNode.next = tailNode;
        tailNode.pre = headNode;
        size = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(index > size - 1 || index < 0) return -1;
        Node curRes = headNode.next;
        for(int i = 0; i < index; i++){
            curRes = curRes.next;
        }
        return curRes.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node preHead = headNode.next;
        Node curNode = new Node(val);
        insertNode(headNode, curNode, preHead);
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node preTail = tailNode.pre;
        Node curNode = new Node(val);
        insertNode(preTail, curNode, tailNode);
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index > size) return;
        else if(index < 0) addAtHead(val);
        else if(index == size) addAtTail(val);
        else {
            Node node2Insert = new Node(val);
            Node curRes = headNode.next;
            for(int i = 0; i < index; i++){
                curRes = curRes.next;
            }
            Node preNode = curRes.pre;
            insertNode(preNode, node2Insert, curRes);
        }
        return;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(index > size - 1 || index < 0) return;
        Node curNode = headNode.next;
        for(int i = 0; i < index; i++){
            curNode = curNode.next;
        }
        Node preNode = curNode.pre;
        Node nextNode = curNode.next;
        preNode.next = nextNode;
        nextNode.pre = preNode;
        size--;
    }

    private void insertNode(Node preNode, Node toInsert, Node nextNode){
        preNode.next = toInsert;
        toInsert.pre = preNode;
        toInsert.next = nextNode;
        nextNode.pre = toInsert;
        size++;
    }

    /*
    ["MyLinkedList"[],
    "addAtHead"[7],
    "addAtHead"[2],
    "addAtHead"[1],
    "addAtIndex"[3,0],
    "deleteAtIndex"[2],
    "addAtHead"[6],
    "addAtTail"[4],
    "get"[4],
    "addAtHead"[4],
    "addAtIndex"[5,0],
    "addAtHead"[6]
    * */

    public static void main(String[] args) {
        MyLinkedList myLL = new MyLinkedList();
        myLL.addAtHead(7);
        myLL.addAtHead(2);
        myLL.addAtHead(1);
        myLL.addAtIndex(3,0);
        myLL.deleteAtIndex(2);
        myLL.addAtHead(6);
        myLL.addAtTail(4);
        myLL.get(4);
    }
}
