package amazon;

/*
一开始想用ArrayList， 但是有一个问题就是他是不能随意放置数进去的，
必须从index = 0开始放，然后按顺序，所以此方法行不通
* */

import java.util.ArrayList;

public class MyHashMap {
    private int[] keys;
    private boolean[] contains;
    /** Initialize your data structure here. */
    public MyHashMap() {
        keys = new int[1000001];
        contains = new boolean[1000001];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        keys[key] = value;
        contains[key] = true;
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        return contains[key] ? keys[key] : -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        contains[key] = false;
    }

    public static void main(String[] args) {
        MyHashMap test = new MyHashMap();
        test.put(1,1);
        test.put(2,2);
        System.out.println(test.get(1));
    }
}
