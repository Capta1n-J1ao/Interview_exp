package amazon;

/*
此题解虽然不是java，但是思路说的是比较清楚的，代码不难，难在思想
还有就是要考虑好怎样去实现映射这个功能：
https://leetcode-cn.com/problems/random-pick-with-blacklist/solution/710-hei-ming-dan-zhong-de-sui-ji-shu-by-je78v/
* */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class lc710 {
//  这个方法虽然简单，但是会时间超时
//    private HashMap<Integer, Integer> hashMap;
//    private ArrayList<Integer> whiteLis;
//    private HashSet<Integer> hashSet;
//    public lc710(int N, int[] blacklist) {
//        hashMap = new HashMap<>();
//        whiteLis = new ArrayList<>();
//        hashSet = new HashSet<>();
//        int bLen = blacklist.length;
//        for(int num : blacklist) hashSet.add(num);
//        for(int i = 0; i < N; i++){
//            if(!hashSet.contains(i)) whiteLis.add(i);
//        }
//    }
//
//    public int pick() {
//        Random random = new Random();
//        int index = random.nextInt(whiteLis.size());
//        return whiteLis.get(index);
//    }


//    方法二： HastMap映射，主要就是想一下映射的方法
    private HashMap<Integer, Integer> hashMap;
    private ArrayList<Integer> whiteLis;
    private HashSet<Integer> hashSet;
    private int bound;
//    注意提交的时候要改成下面这行
//    public Solution(int N, int[] blacklist) {
    public lc710(int N, int[] blacklist) {
        hashMap = new HashMap<>();
        hashSet = new HashSet<>();
        int bLen = blacklist.length;
        for(int num : blacklist) hashSet.add(num);
        int index = N - bLen;
        bound = N - bLen;
        for(int num : blacklist){
//            这个剪枝很关键
            if(num >= bound) continue;
            while (hashSet.contains(index) && index < N) index++;
            hashMap.put(num, index);
            index++;
        }
    }

    public int pick() {
        Random random = new Random();
        int index = random.nextInt(bound);
        if(hashMap.containsKey(index)) return hashMap.get(index);
        return index;
    }
}
