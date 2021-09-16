package amazon;

/*
印象中有两道题目都是在非有序数组运用二分法，这里可以先记录一道，等遇到另一道再加进来
官解就写的不错：
https://leetcode-cn.com/problems/find-peak-element/solution/xun-zhao-feng-zhi-by-leetcode/
* */

public class lc162 {
    public int findPeakElement(int[] nums) {
        int numLen = nums.length, left = 0, right = numLen - 1;
        while (left < right){
            int mid = left + (right - left) / 2;
//            这里的判定还是比较松的，因为只要nums[mid] = nums[mid + 1]，
//            其实都不可以继续下去了，实测下面两句都可以
//            if(nums[mid] >= nums[mid + 1]){
            if(nums[mid] > nums[mid + 1]){
                right  = mid;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }
}
