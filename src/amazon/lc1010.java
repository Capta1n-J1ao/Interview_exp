package amazon;

/*
这道题目一开始一定想到的是2sum的思路，但是要注意一种corner case
60,60,60
这种的话应该要想到怎么正确处理

然后进阶的其实就是2sum的思路，但是前提是要对数据进行一些处理，
因为2sum的核心就是hashset，那怎么创建一个能计数的hashset成为了一个难点
参考题解如下，参考解法二：
https://leetcode-cn.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/solution/jian-duan-you-rong-yi-li-jie-de-on-suan-fa-by-ciel/
* */

import java.util.HashMap;
import java.util.HashSet;

public class lc1010 {
//    private HashSet<Integer> hashSet = new HashSet<>();
//    private HashMap<Integer, Integer> hashMap = new HashMap<>();
//    public int numPairsDivisibleBy60(int[] time) {
//        int tLen = time.length, res = 0;
//        for(int t : time){
//            for(int songTime : hashMap.keySet()){
//                if((t + songTime) % 60 == 0 && hashMap.get(songTime) > 0) {
//                    res += hashMap.get(songTime);
////                    hashMap.put(songTime, 0);
//                }
//            }
//            hashMap.put(t, hashMap.getOrDefault(t, 0) + 1);
//        }
//        return res;
//    }

//    方法二
    public int numPairsDivisibleBy60(int[] time) {
        int tLen = time.length, res = 0;
        int[] arrayAsHashset = new int[60];
        for(int t : time){
            int mod = t % 60;
            res += mod == 0 ? arrayAsHashset[0] : arrayAsHashset[60 - mod];
            arrayAsHashset[mod]++;

        }
        return res;
    }

    public static void main(String[] args) {
//        int[] test = {30,20,150,100,40};
//        System.out.println(new lc1010().numPairsDivisibleBy60(test));

        int[] test = {60,60,60};
        System.out.println(new lc1010().numPairsDivisibleBy60(test));
    }

//[30,20,150,100,40]
}
