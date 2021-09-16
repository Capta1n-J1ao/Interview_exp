package amazon;

public class lc123 {
//    方法一，常规方法
//    public int maxProfit(int[] prices) {
//        int pLen = prices.length;
//        int[][][] dp = new int[pLen][3][2];
//        dp[0][0][0] = 0;
//        dp[0][0][1] = -prices[0];
//        dp[0][1][0] = 0;
//        dp[0][1][1] = -prices[0];
//        dp[0][2][0] = 0;
//        for(int i = 1; i < pLen; i++){
//            dp[i][0][1] = Math.max(dp[i - 1][0][1], dp[i - 1][0][0] - prices[i]);
//            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][0][1] + prices[i]);
//            dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][1][0] - prices[i]);
//            dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][1][1] + prices[i]);
//        }
//        return Math.max(dp[pLen - 1][2][0], dp[pLen - 1][1][0]);
//    }

//    方法二，状态压缩
    public int maxProfit(int[] prices) {
        int pLen = prices.length;
//        int[][][] dp = new int[pLen][3][2];
        int dp_i_0_0 = 0;
        int dp_i_0_1 = -prices[0];
        int dp_i_1_0= 0;
        int dp_i_1_1 = -prices[0];
        int dp_i_2_0 = 0;
//        下面的赋值顺序注意和方法一是相反的
        for(int i = 1; i < pLen; i++){
            dp_i_2_0 = Math.max(dp_i_2_0, dp_i_1_1 + prices[i]);
            dp_i_1_1 = Math.max(dp_i_1_1, dp_i_1_0 - prices[i]);
            dp_i_1_0 = Math.max(dp_i_1_0, dp_i_0_1 + prices[i]);
            dp_i_0_1 = Math.max(dp_i_0_1, dp_i_0_0 - prices[i]);
        }
        return Math.max(dp_i_2_0, dp_i_1_0);
    }

    public static void main(String[] args) {
        int[] testBench = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(new lc123().maxProfit(testBench));

    }
}
