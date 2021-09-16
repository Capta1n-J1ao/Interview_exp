package amazon;

/*
先做lc55，再做lc45
要思考清除用dp或者贪心，这个可以算是这个tag里面最难的了，主要是一些具体实施的细节难。

题解这个讲的最清楚：
https://leetcode-cn.com/problems/jump-game-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-10/

二刷的时候第一个就排除了贪心，感觉效率不高，感觉应该用dp，所以找了个用dp的题解，而且性能还更好
https://leetcode-cn.com/problems/jump-game-ii/solution/hao-xiang-xie-cheng-liao-dong-tai-gui-hu-tilg/
* */

public class lc45 {
//    public int jump(int[] nums) {
//        int numLen = nums.length;
//        int step = 0;
////        注意下面两个参数的区别
////        maxPos代表的是在这一个step中能跳到的最远距离,在同一个step内，最远跳到maxPos
//        int maxPos = 0;
////        end代表的是在上一个step内，能到达的最远距离，然后在这一个step中，由maxPos更新
//        int end = 0;
//        for(int i = 0; i < numLen - 1; i++){
////            这句话代表当前这个nums[i]最多能跳到的右边的值，也就是这一个step内能够到达的最大值
//            maxPos = Math.max(maxPos, nums[i] + i);
////            如果for循环遍历到end，说明上一个step最多也只能跑到end，上一个step结束，
////            进入下一个step，此时需要由maxPos更新这一个step
//            if(i == end){
//                end = maxPos;
//                step++;
//            }
//        }
//        return step;
//    }

//    二刷 2021/3/25
    public int jump(int[] nums) {
        int numLen = nums.length;
        if(nums == null || numLen == 0) return 1;
        if(nums[0] == 0) return 0;
        int[] dp = new int[numLen];
//        dp[0] = 1;
        for(int i = 0; i < numLen; i++){
            if(i + 1 < numLen){
                for(int k = i + 1; k <= i + nums[i] && k < numLen; k++){
                    if(dp[k] == 0) dp[k] = dp[i] + 1;
                    else dp[k] = Math.min(dp[k], dp[i] + 1);
//                if(k >= numLen - 1) break;
                }
            }
        }
        return dp[numLen - 1];
    }


    public static void main(String[] args) {
//        int[] test = {2,3,1,1,4};
//        System.out.println(new lc45().jump(test));
        int[] test = {2,1};
        System.out.println(new lc45().jump(test));
    }
}
