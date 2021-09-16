package amazon;

/*
这道题目思路想出来就还好，参考题解：
https://leetcode-cn.com/problems/4sum-ii/solution/si-shu-xiang-jia-iijava-by-sui-ji-guo-ch-1cih/
* */

import java.util.HashMap;

public class lc454 {
    private HashMap<Integer, Integer> hashMap;
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
         hashMap = new HashMap<>();
         int res = 0;
         for(int numa : nums1){
             for(int numb : nums2){
                 int first2Sum = numa + numb;
                 hashMap.put(first2Sum, hashMap.getOrDefault(first2Sum, 0) + 1);
             }
         }

         for(int numc : nums3){
             for(int numd : nums4){
                 int last2Sum = numc + numd;
                 if(hashMap.containsKey(0 - last2Sum)){
                     res += hashMap.get(0 - last2Sum);
                 }
             }
         }
         return res;
    }
}
