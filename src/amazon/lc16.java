package amazon;

/*
和lc15本质上一样，必须会！
* */

import java.util.Arrays;

public class lc16 {
    public int threeSumClosest(int[] nums, int target) {
        int numLen = nums.length;
        Arrays.sort(nums);
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < numLen; i++){
            if(i > 0 && nums[i] == nums[i - 1])continue;
            int left = i + 1;
            int right = numLen - 1;
            while (left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum > target) {
                    right--;
                    if(Math.abs(sum - target) < Math.abs(res - target)) res = sum;
                }
                else if(sum < target) {
                    left++;
                    if(Math.abs((double) sum - target) < Math.abs((double)res - target)) res = sum;
                }
                else return sum;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] test = {-3,-2,-5,3,-4};
        System.out.println(new lc16().threeSumClosest(test, -1));
    }
}
