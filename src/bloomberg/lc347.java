package bloomberg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/*
给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

 

示例 1:

输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
示例 2:

输入: nums = [1], k = 1
输出: [1]

提示：
确实用hashmap做，但是难点在于后续的数据处理
* */

public class lc347 {
    public int[] topKFrequent(int[] nums, int k) {
        int numLen = nums.length;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int[] res = new int[k];
        for(int num : nums){
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }

//        这道题目的难点就在于放了hashmap以后怎么处理数据
//        下面这句话会有一个问题，就是当输入为{1}的时候，会有问题，所以要用下面这种情况
//        ArrayList<Integer>[] numList = new ArrayList[numLen];
        ArrayList<Integer>[] numList = new ArrayList[numLen + 1];
        for(int key : hashMap.keySet()){
            int freq = hashMap.get(key);
//            初始化绝对不能忘
            if(numList[freq] == null) numList[freq] = new ArrayList<>();
            numList[freq].add(key);
        }

        LinkedList<Integer> linkedList = new LinkedList<>();
        for(int i = numList.length - 1; i >= 0; i--){
//            注意这么写是错的，会报null pointer
//            if(numList[i].isEmpty()) continue;
            if(numList[i] == null) continue;
            linkedList.addAll(numList[i]);
        }

        for(int i = 0; i < k; i++){
            res[i] = linkedList.poll();
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] test = {1};
        int[] test = {4,1,-1,2,-1,2,3};
        System.out.println(Arrays.toString(new lc347().topKFrequent(test, 2)));
    }
}
