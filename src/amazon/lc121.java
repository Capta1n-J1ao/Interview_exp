package amazon;

/*
注意这道题目说的是只能有一次操作
* */

import java.util.Arrays;

public class lc121 {
//    方法一：常规用秒杀方法做的版本
//    public int maxProfit(int[] prices) {
//        int pLen = prices.length;
//        int[][][] dp = new int[pLen][2][2];
//        dp[0][0][0] = 0;
//        dp[0][1][1] = -prices[0];
//        for(int i = 1; i < pLen; i++){
//            dp[i][0][0] = Math.max(dp[i - 1][0][0], dp[i - 1][1][1] + prices[i]);
//            dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][1][0] - prices[i]);
//        }
//        return Math.max(dp[pLen - 1][0][0], dp[pLen - 1][1][0]);
//    }
//    方法二：下面这个版本是dp优化版本
//    public int maxProfit(int[] prices) {
//        int pLen = prices.length;
//        int[] dp = new int[2];
////        Arrays.fill(dp, Integer.MIN_VALUE);
////        dp[0] = Integer.MIN_VALUE;
////        当然还能够进一步优化dp[0]和dp[1]成为常数还能够省空间
//        dp[1] = -prices[0];
//        for(int i = 1; i < pLen; i++){
//            dp[0] = Math.max(dp[0], dp[1] + prices[i]);
//            dp[1] = Math.max(dp[1], - prices[i]);
//        }
//        return Math.max(dp[0], dp[1]);
//    }

//    方法三：非dp最高效版本
    public int maxProfit(int[] prices) {
        int pLen = prices.length;
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for(int i = 0; i < pLen; i++){
            if(prices[i] < minPrice) minPrice = prices[i];
            else if(prices[i] - minPrice > maxProfit) maxProfit = prices[i] - minPrice;
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] test = {7,1,5,3,6,4};
        System.out.println(new lc121().maxProfit(test));
    }
}
