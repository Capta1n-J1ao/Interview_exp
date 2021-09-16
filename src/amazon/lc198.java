package amazon;

/*
这道题目的难点是下面这个test case，要考虑清楚
* */

public class lc198 {
    public int rob(int[] nums) {
//        不需要考虑corner case——错了，需要考虑numLen < 3的情况
        int numLen = nums.length;
        if(numLen == 1) return nums[0];
        int[] dp = new int[numLen];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for(int i = 2; i < numLen; i++){
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[numLen - 1];
    }

    public static void main(String[] args) {
        int[] test = {2,1,1,2};
        System.out.println(new lc198().rob(test));
    }
}
