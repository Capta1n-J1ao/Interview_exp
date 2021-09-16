package amazon;

public class lc53 {
//    public int maxSubArray(int[] nums) {
//        int numLen = nums.length;
//        if(numLen < 2) return nums[0];
//        int[] dp = new int[numLen];
//        dp[0] = nums[0];
//        int res = nums[0];
//        for(int i = 1; i < numLen; i++){
//            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
//            res = Math.max(res, dp[i]);
//        }
//        return res;
//    }

//    二刷，2021/6/22
    public int maxSubArray(int[] nums) {
        int numLen = nums.length;
        if(numLen < 2) return nums[0];
        int[] dp = new int[numLen];
        dp[0] = nums[0];
        int res = nums[0];
        for(int i = 1; i < numLen; i++){
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
