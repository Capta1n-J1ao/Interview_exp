package amazon;

/*
想到了用两个量去分别存储当nums[i]为整数和负数，但是没有思考完备，差一点：
https://leetcode-cn.com/problems/maximum-product-subarray/solution/dong-tai-gui-hua-li-jie-wu-hou-xiao-xing-by-liweiw
* */

public class lc152 {
//    public int maxProduct(int[] nums) {
//        int numLen = nums.length;
//        if(numLen == 0 || nums == null) return 0;
//        int[][] dp = new int[numLen][2];
//        dp[0][1] = nums[0];
//        dp[0][0] = nums[0];
//        for(int i = 1; i < numLen; i++){
//            if(nums[i] > 0){
//                dp[i][0] = Math.min(dp[i - 1][0] * nums[i], nums[i]);
//                dp[i][1] = Math.max(dp[i - 1][1] * nums[i], nums[i]);
//            }else {
//                dp[i][0] = Math.min(dp[i - 1][1] * nums[i], nums[i]);
//                dp[i][1] = Math.max(dp[i - 1][0] * nums[i], nums[i]);
//            }
//        }
//        int res = Integer.MIN_VALUE;
//        for(int i = 0; i < numLen; i++){
//            res = Math.max(res, dp[i][1]);
//        }
//        return res;
//    }

//    二刷，20210719
    public int maxProduct(int[] nums) {
        int numLen = nums.length;
        if(numLen == 0 || nums == null) return 0;
        int[][] dp = new int[numLen][2];
        dp[0][1] = nums[0];
        dp[0][0] = nums[0];
        for(int i = 1; i < numLen; i++){
            if(nums[i] > 0){
                dp[i][0] = Math.min(dp[i - 1][0] * nums[i], nums[i]);
                dp[i][1] = Math.max(dp[i - 1][1] * nums[i], nums[i]);
            }else {
                dp[i][0] = Math.min(dp[i - 1][1] * nums[i], nums[i]);
                dp[i][1] = Math.max(dp[i - 1][0] * nums[i], nums[i]);
            }
        }
        int res = Integer.MIN_VALUE;
        for(int i = 0; i < numLen; i++){
            res = Math.max(res, dp[i][1]);
        }
        return res;
    }
}
