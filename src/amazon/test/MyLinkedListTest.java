package amazon.test;

import amazon.MyLinkedList;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyLinkedListTest {

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
    MyLinkedList myLL = new MyLinkedList();
    @Test
    public void get() {
    }

    @Test
    public void addAtHead() {
        myLL.addAtHead(7);
        myLL.addAtHead(2);
        myLL.addAtHead(1);
    }

    @Test
    public void addAtTail() {
    }

    @Test
    public void addAtIndex() {
    }

    @Test
    public void deleteAtIndex() {
    }
}