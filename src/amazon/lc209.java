package amazon;

/*
滑动窗口系列题目：
3/76/209/159/424/992， 其中76/992最难留到最后做
在做了前面几道题目以后，这道题目秒做出来，没有参考任何代码，但是labuladong的关于滑动窗口的文章写的挺好的
她给出了滑动窗口代码的最核心思路


* */

public class lc209 {
    public int minSubArrayLen(int target, int[] nums) {
        int numLen = nums.length;
        if(nums == null || numLen == 0) return 0;
        int left = 0, right = 0, add = 0, res = numLen + 1;
        while (right <numLen){
            add += nums[right];
            right++;
            while (add >= target){
                res = Math.min(res, right - left);
                add -= nums[left];
                left++;
            }
        }
        if(res == numLen + 1) return 0;
        return res;
    }

    public static void main(String[] args) {
//        int[] test = {2,3,1,2,4,3};
//        System.out.println(new lc209().minSubArrayLen(7, test));

        int[] test = {1,4,4};
        System.out.println(new lc209().minSubArrayLen(4, test));
    }
}
