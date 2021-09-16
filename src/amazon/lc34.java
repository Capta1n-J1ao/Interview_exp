package amazon;

/*
三刷这道题目的时候，其实想到的是直接用一个二分法，找到target，然后直接从这个target往外散开
参考下面这个题解的方法一：
https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/javaer-fen-fa-gai-zao-3chong-fang-shi-du-ji-bai-li/

但是还是希望掌握以下这个写法,因为右边界的写法其实是之前反复复习过的右中位数的写法
但是之前都没有题目是反复考察的，而这道题目恰好再次考察了这个知识点：
https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/si-lu-hen-jian-dan-xi-jie-fei-mo-gui-de-er-fen-cha/
* */

import java.util.Arrays;

public class lc34 {
    private int[] nums;
    private int numLen;
    public int[] searchRange(int[] nums, int target) {
        this.nums = nums;
        if(nums == null || nums.length == 0) return new int[]{-1, -1};
        this.numLen = nums.length;
        int left = searchL(target);
        if(left == -1) return new int[]{-1, -1};
        int right = searchR(target);
        return new int[]{left, right};
    }

    private int searchL(int target){
        int left = 0, right = numLen - 1;
        while (left < right){
            int mid = left + (right - left) / 2;
            int curNum = nums[mid];
            if(curNum < target) left = mid + 1;
            else right = mid;
        }
        return nums[left] == target ? left : -1;
    }

//    注意这里要用右中位数
    private int searchR(int target){
        int left = 0, right = numLen - 1;
        while (left < right){
            int mid = left + (right - left + 1) / 2;
            int curNum = nums[mid];
            if(curNum > target) right = mid - 1;
            else left = mid;
        }
        return nums[left] == target ? left : -1;
    }

    public static void main(String[] args){
        int[] test = {5,7,7,8,8,10};

        System.out.println(Arrays.toString(new lc34().searchRange(test, 8)));
    }
}
