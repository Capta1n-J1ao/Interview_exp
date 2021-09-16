package amazon;

/*
这道题目和lc81必须一起做，太像
这道题目的关键是怎么把mid,target还有数组边界结合起来，很考验细节,但是二分法的核心还是不变，按习惯用的左中位数模板
corner case很多，下面的都是

常规liweiwei：
https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/er-fen-fa-python-dai-ma-java-dai-ma-by-liweiwei141/
* */

public class lc33 {
    public int search(int[] nums, int target) {
        int numLen = nums.length;
        int left = 0, right = numLen - 1;
        while (left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target) return mid;
            if(nums[mid] < nums[right]){
                if(nums[mid] < target && target <= nums[right]) left = mid + 1;
                else right = mid;
            }else if(nums[mid] >= nums[left]){
                if(nums[left] <= target && target < nums[mid]) right = mid;
                else left = mid + 1;
            }
//            if(nums[mid] == target) return mid;
        }
//        针对test1 corner case
        if(nums[left] == target) return left;
        return -1;
    }

    public static void main(String[] args) {
//        int[] test = {4,5,6,7,0,1,2};
//        System.out.println(new lc33().search(test, 0));
//        System.out.println(new lc33().search(test, 3));
//        System.out.println(new lc33().search(test, 5));
//        int[] test1 = {1};
//        System.out.println(new lc33().search(test1, 1));
//        int[] test2 = {1,3};
//        System.out.println(new lc33().search(test2, 3));
        int[] test3 = {3,1};
        System.out.println(new lc33().search(test3, 0));
    }
}
