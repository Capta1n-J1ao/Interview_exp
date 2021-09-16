package amazon;

/*
看Liweiwei解答：
https://leetcode-cn.com/problems/coin-change-2/solution/dong-tai-gui-hua-wan-quan-bei-bao-wen-ti-by-liweiw/
这个是基础版，另外还做了一个优化版，时间复杂度小了一级，也是在Liweiwei的讲解里面。
空间优化一定要会，而且确实不难。

建议和lc377一起做
* */

public class lc518 {
//    这个思路是不对的
//    public int change(int amount, int[] coins) {
//        int[] dp = new int[amount + 1];
//        for(int coin :coins) dp[coin] = 1;
//        for(int i = 1; i <= amount; i++){
//            for(int coin : coins){
//                if(i - coin > 0) dp[i] += dp[i - coin];
//            }
//        }
//        return dp[amount];
//    }

//    版本优化的那个主要需要搞清楚他的状态转移方程怎么得来的，tricky的地方很多
    public int change(int amount, int[] coins) {
        int cLen = coins.length;
        if (cLen == 0) {
            if (amount == 0) {
                return 1;
            }
            return 0;
        }
        int[][] dp = new int[cLen][amount + 1];
        dp[0][0] = 1;
        for(int i = coins[0]; i <= amount; i += coins[0]){
            dp[0][i] = 1;
        }
        for(int i = 1; i < cLen; i++){
//            这里是k = 0开始，原因和上面单独做第一行的dp[0][i]的含义一样
//            for(int k = 1; k <= amount; k++){
            for(int k = 0; k <= amount; k++){
                for(int m = 0; k - m * coins[i] >= 0; m++){
                    dp[i][k] += dp[i - 1][k - m * coins[i]];
                }
            }
        }
        return dp[cLen - 1][amount];
    }

    public static void main(String[] args) {
        int[] test = {1,2,5};
        System.out.println(new lc518().change(5, test));
    }
}
