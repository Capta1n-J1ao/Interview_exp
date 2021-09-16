package amazon;

/*
同lc381
这道题目我一开始想到的是用Hashmap来存储元素和出现的位置
但是删除的时候的操作没细想，仔细想的话还是需要用一个ArrayList来存储val
然后这样才能够保证O(1)的删除性能
还有就是我一开始用的HashMap<Integer, Queue<Integer>> 这个Queue是不行的
原因参考第二个case，你如果进行删除操作，arrayList的参数变了，因为颠倒了顺序
所以HashMap里面的HashSet也要相应变化的

这道题目复习了以下方法
1. 熟悉一下arrayList的set方法，如果是替换的话只能用这个方法
2. 又一次熟悉了iterator的用法，而且这个方法在这道题目很重要
* */

import javax.swing.plaf.IconUIResource;
import java.util.*;

public class RandomizedCollection {
    /** Initialize your data structure here. */
//    private HashMap<Integer, Queue<Integer>> hashMap;
    private HashMap<Integer, HashSet<Integer>> hashMap;
    private List<Integer> arrayList;
    private int count;
    private Random random;
    public RandomizedCollection() {
        hashMap = new HashMap<>();
        arrayList = new ArrayList<>();
        count = 0;
        random = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        hashMap.put(val, hashMap.getOrDefault(val, new HashSet<>()));
        HashSet temp = hashMap.get(val);
        temp.add(count);
        arrayList.add(val);
        count++;
        return hashMap.get(val).size() < 2;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!hashMap.containsKey(val)) return false;
        HashSet lastEle = hashMap.get(arrayList.get(count - 1));
        HashSet curEle = hashMap.get(val);
        int index = (int) curEle.iterator().next();
        swap(index, count - 1);
        arrayList.remove(count - 1);
        curEle.remove(index);
        if(hashMap.get(val).size() == 0) hashMap.remove(val);
        lastEle.add(index);
        lastEle.remove(count - 1);
        count--;
        return true;
    }

    private void swap(int a, int b){
        int tempA = arrayList.get(a);
        int tempB = arrayList.get(b);
        arrayList.set(b, tempA);
        arrayList.set(a, tempB);
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return arrayList.get(random.nextInt(count));
    }

    public static void main(String[] args) {
//        RandomizedCollection test = new RandomizedCollection();
//        test.insert(1);
//        test.remove(2);
//        test.insert(2);
//        test.remove(1);
//        test.insert(2);
//        System.out.println(test.getRandom());

        RandomizedCollection test = new RandomizedCollection();
        test.insert(0);
        test.insert(1);
        test.remove(0);
        test.insert(2);
        test.remove(1);
        System.out.println(test.getRandom());
    }
}
