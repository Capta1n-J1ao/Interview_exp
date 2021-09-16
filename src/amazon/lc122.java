package amazon;

public class lc122 {
//    public int maxProfit(int[] prices) {
//        int pLen = prices.length, res = 0;
//        if(pLen < 2) return res;
//        int[][] dp = new int[pLen + 1][2];
//        dp[0][0] = 0;
//        dp[0][1] = -prices[0];
//        for(int i = 1; i < pLen; i++){
//            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
//            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
//        }
//        return dp[pLen][0];
//    }

//    状态压缩
    public int maxProfit(int[] prices) {
        int pLen = prices.length, res = 0;
        if(pLen < 2) return res;
        int buy = -prices[0], hold = 0;
        for(int i = 1; i < pLen; i++){
            int preBuy = buy, preHold = hold;
            hold = Math.max(preHold, preBuy + prices[i]);
            buy = Math.max(preHold - prices[i], preBuy);
        }
        return hold;
    }
}
