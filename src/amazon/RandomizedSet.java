package amazon;

/*
lc380

自己做的时候其实已经基本想出来使用hashmap和list了，以下是详细思路：
https://leetcode-cn.com/problems/insert-delete-getrandom-o1/solution/li-yong-hashmaphe-arraylistshi-xian-by-1-12-14-18/

这道题目最难写的就是那个remove命令，会有以下几个问题：
1. 如果list是空的话，是不能用list.set这个命令的，会错
2. 要考虑周全remove方法里面set和remove的顺序，否则会有问题
* */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomizedSet {
    Map<Integer, Integer> hashMap;
    List<Integer> list;
//    最后写完了以后发现size不一定要，直接用list.size就可以解决
//    int size;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        hashMap = new HashMap<>();
        list = new ArrayList<>();
        // size = 0;
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(hashMap.containsKey(val)) return false;
        //在ArrayList末尾添加一个数的时间复杂度为O(1)
        hashMap.put(val, list.size());
        list.add(val);
        // size++;
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!hashMap.containsKey(val)) return false;
        //由于在ArrayList中间删除一个元素的时间复杂度为O(N)
        //因此用末尾的数替换掉需要删除的数(根据map得到索引,平均时间复杂度O(1))
        //再删除掉末尾的数(时间复杂度O(1))
        int size = list.size();
        int pos = hashMap.get(val);
        int lastVal = list.get(size - 1);
//        list.remove(size - 1);
//        下面这四句话一定要按照先是set然后再是remove val的顺序，
//        否则test case里面的那种情况就会出错
        list.set(pos, lastVal);
        hashMap.put(lastVal, pos);
        hashMap.remove(val);
        list.remove(size - 1);
        // size--;
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int index;
        int size = list.size();
        //ArrayList根据索引访问时间复杂度为O(1)
        index = (int)(size * Math.random());
        return list.get(index);
    }

    public static void main(String[] args) {
        RandomizedSet test = new RandomizedSet();
        test.remove(0);
        test.remove(0);
        test.insert(0);
        int random = test.getRandom();
        test.remove(0);
        boolean res1 = test.insert(0);
        System.out.println(res1);
    }
}
