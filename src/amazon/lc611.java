package amazon;

/*
这道题目完全是自己想的，然后代码可以参考liweiwei，很标准：
https://leetcode-cn.com/problems/valid-triangle-number/solution/er-fen-cha-zhao-python-dai-ma-java-dai-ma-by-liwei/
* */

import java.util.Arrays;

public class lc611 {
    private int[] nums;
    public int triangleNumber(int[] nums) {
        this.nums = nums;
        if(nums == null || nums.length < 3) return 0;
        int numLen = nums.length, res = 0;
        Arrays.sort(nums);
        for(int i = 0; i < numLen - 2; i++){
            for(int k = i + 1; k < numLen - 1 && nums[i] != 0; k++){
                int end = helper(k + 1, numLen- 1, nums[i] + nums[k]);
                if(end == -1) res += numLen - 1 - k;
                else res += end - k - 1;
            }
        }
        return res;
    }
    private int helper(int l, int r,int target){
        while (l < r){
            int mid = l + (r - l) / 2;
            if(nums[mid] < target){
                l = mid + 1;
            }else r = mid;
        }
        if(nums[l] < target) return -1;
        return l;
    }

    public static void main(String[] args) {
        int[] test = {2,2,3,4};
        System.out.println(new lc611().triangleNumber(test));
    }
}
