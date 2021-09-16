package amazon;

/*
这道题目一开始看结果，以为要用回溯，但是写了半天还是解决不了重复值的问题，然后看了眼答案发现思路完全错了


应该用lc1的思路做这道题目，建议做这道题目之前先做下lc1，体会下2sum的解题思路
这个题解已经解释的很清楚了，在不清楚的话直接看题解里面的图：
https://leetcode-cn.com/problems/3sum/solution/hua-jie-suan-fa-15-san-shu-zhi-he-by-guanpengchn/
* */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class lc15 {
    private List<List<Integer>> res = new LinkedList<>();
    private int[] nums;
    private boolean[] visted;
    private int numLen;
    public List<List<Integer>> threeSum(int[] nums) {
        this.nums = nums;
        numLen = nums.length;
        if(numLen < 3) return res;
        Arrays.sort(nums);
        for(int i = 0; i < numLen; i++){
//            去重
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            int one = nums[i];
            int left = i + 1;
            int right = numLen - 1;
            while (left < right){
                int sum = one + nums[left] + nums[right];
                if(sum > 0) right--;
                else if(sum < 0) left++;
                else if(sum == 0){
                    res.add(Arrays.asList(one, nums[left], nums[right]));
//                    还是去重
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                }
            }
            continue;
        }
        return res;
    }



    public static void main(String[] args) {
        int[] test = {-1,0,1,-4};
        System.out.println(new lc15().threeSum(test));
    }
}
