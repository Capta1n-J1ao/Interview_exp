package amazon;

/*
一见到nlog(n)就要想到二分法，但是二分法一般是用在有序数组中，极个别题目也可以用在无序数组，但很少见
有空的时候可以吧O(n2)的方法做一下。
题解两种方法都可以参考Liweiwei：
https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/dong-tai-gui-hua-er-fen-cha-zhao-tan-xin-suan-fa-p/
* */

import java.util.Arrays;

public class lc300 {
//    public int lengthOfLIS(int[] nums) {
//        int numLen = nums.length;
//        if(numLen < 2) return numLen;
//        int[] dp = new int[numLen];
//        dp[0] = nums[0];
//        int dpSize = 0;
//        for(int i = 1; i < numLen; i++){
//            if(nums[i] > dp[dpSize]){
//                dpSize++;
//                dp[dpSize] = nums[i];
//            }else {
//                int left = 0, right = dpSize;
//                while (left < right){
//                    int mid = left + (right - left) / 2;
//                    if(dp[mid] < nums[i]) left = mid + 1;
//                    else right = mid;
//                }
//                dp[left] = nums[i];
//            }
//        }
//        return dpSize + 1;
//    }

//    二刷，2021/6/22
    private int[] nums, dp;
    public int lengthOfLIS(int[] nums) {
        this.nums = nums;
        int numLen = nums.length;
        int dpLen = 0;
        if(numLen < 2) return numLen;
        this.dp = new int[numLen];
        dp[0] = nums[0];
        int index = 0;
        for(int i = 1; i < numLen; i++){
            if(nums[i] > dp[index]) {
                index++;
                dp[index] = nums[i];
            }
            else {
                int insert = helper(index, nums[i]);
                dp[insert] = nums[i];
            }
        }
        return index + 1;
    }

    private int helper(int index, int target){
        int left = 0, right = index;
        while (left < right){
            int mid = left + (right - left) / 2;
            if(target > dp[mid]) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    public static void main(String[] args) {
//        答案为4
        int[] test = {10,9,2,5,3,7,101,18};
//        答案为3
//        int[] test = {4,10,4,3,8,9};
        System.out.println(new lc300().lengthOfLIS(test));
    }
}
