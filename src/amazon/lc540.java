package amazon;

/*
这道题目可以尝试官方题解的二分法，挺巧妙
* */

public class lc540 {
//    public int singleNonDuplicate(int[] nums) {
//        int numLen = nums.length;
//        int res = 0;
//        for(int num : nums){
//            res ^= num;
//        }
//        return res;
//    }

    public int singleNonDuplicate(int[] nums) {
        int numLen = nums.length;
        int left = 0, right = numLen - 1;
        while (left < right){
            int mid = left + (right - left) / 2;
            if(mid % 2 == 1) mid--;
            if(nums[mid] == nums[mid + 1]) left = mid + 2;
            else right = mid;
        }
        return nums[left];
    }
}
