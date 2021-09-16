package amazon;

/*
参考Liweiwei，代码里面有个很tricky的点，那就是你到底是在买入还是卖出的时候+1交易次数其实会对代码有很大的影响
比如liweiwei这里他是在每次买入的时候对交易次数+1，所以他的代码就没有问题
https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/solution/dong-tai-gui-hua-by-liweiwei1419-4/

但是我由于自己写了一遍，之前都是和liweiwei一样，但是这次我是在卖出的时候交易次数才+1
于是就产生了问题，具体的原因就是因为对于dp状态方程的初始化需要有更改
由于我是在卖出的时候交易次数+1，所以对于dp[i][0][1]的定义在本质上和liweiwei是不同的
1. liweiwei的定义：
    dp[i][0][1]为第i-1天，还没有任何次数的时候持有股票，这在他的定义上其实是不存在的，所以初始化为Integer.MIN_VALUE
2. 卖出的时候交易次数+1的定义：
    dp[i][0][1]为第i-1天，已经交易了一次的时候持有股票。这个在状态定义上其实已经是合法状态了，所以要赋予有效值

正是对于这两个状态定义的不同，所以代码不能完全套用，经过总结来说，以后还是在买入的时候就把交易次数+1即可，这样可以避免有坑

包括在状态压缩以后，在卖出的时候+1交易次数对于代码的处理也会更加复杂，所以以后还是在买入的时候就把交易次数+1即可，这样可以避免有坑
* */

public class lc188 {
//    一开始常规想到的是这个版本，但是分类讨论其实有点多，而且我忽略了下面这种情况
//    dp[i][m][1] = Math.max(dp[i - 1][m][1], -prices[i]);
    //这一班是liweiwei的版本，但是和习惯的版本有区别，就是不希望有分类讨论
//    public int maxProfit(int k, int[] prices) {
//        int priceLen = prices.length;
//        if(prices == null || priceLen == 0 || k ==0) return 0;
//        if (k >= priceLen / 2) {
//            return greedy(prices, priceLen);
//        }
//        int[][][] dp = new int[priceLen][k][2];
//        for(int i = 0; i < priceLen; i++){
//            for(int m = 0; m < k; m++){
//                dp[i][m][1] = Integer.MIN_VALUE;
//            }
//        }
//
//        for(int i = 0; i < priceLen; i++){
//            for(int m = 0; m < k; m++){
//                if(i == 0){
//                    //这步很关键，否则会错之前写的会导致有些case会有错误
//                    //虽然i = 0 && m > 0的情况不可能，但是一定要这么写，否则就是错
//                    //等水平高了可以找原因，现在还不懂
//                    //dp[0][0][1]= -prices[0];
//                    //dp[0][0][0]= 0;
//                    dp[0][m][1]= -prices[0];
//                    dp[0][m][0]= 0;
//                }else {
//                    if (m == 0) {
//                        //这里不能合并，因为上面初始化的时候，是-prices[0]
//                        //但是下面这个却是-prices[i]
//                        dp[i][m][1] = Math.max(dp[i - 1][m][1], -prices[i]);
//                    } else {
//                        //写状态方程的时候要注意要不在买入的时候算交易次数，或者在卖出的时候算交易次数
//                        //千万不要在买入和卖出的时候都算交易次数
//                        dp[i][m][1] = Math.max(dp[i - 1][m - 1][0] - prices[i], dp[i - 1][m][1]);
//                    }
//                    dp[i][m][0] = Math.max(dp[i - 1][m][1] + prices[i], dp[i - 1][m][0]);
//                }
//            }
//        }
//        return dp[priceLen - 1][k - 1][0];
//    }

//    在看了题解以后决定对代码进行优化，让代码更加简洁，但是要注意有坑
//    对于交易次数增加的时机很有讲究
//    public int maxProfit(int k, int[] prices) {
//        if(k == 0 || prices.length == 0) return 0;
//        int pLen = prices.length;
//        if(k > pLen / 2) return helper(prices, pLen);
//        int[][][] dp = new int[pLen + 1][k + 1][2];
////        这里这个初始化是精髓，为什么这么初始化要想清楚
//        for(int i = 0; i <= pLen; i++){
//            for(int m = 0; m <= k; m++){
//                dp[i][m][1] = Integer.MIN_VALUE;
////                这句话很关键，debug很久才找到问题，针对上面的讲解体会一下
////                以后还是建议在买入的时候就把交易次数+1
//                if(i > 0) dp[i][0][1] = Math.max(dp[i-1][0][1], -prices[i - 1]);
//            }
//        }
//
//        for(int i = 1; i <= pLen; i++){
//            for(int m = 1; m <= k; m++){
////                这里有一个很奇怪的点就是如果在买入的时候，交易次数+1就没问题，但是如果在卖出的时候
////                就想下面这种写法就会错误，原因在最上面，一定要看清楚！
////                System.out.println("i = " + i + " m = " + m);
//                dp[i][m][0] = Math.max(dp[i - 1][m][0], dp[i - 1][m - 1][1] + prices[i - 1]);
//
////                System.out.println( " dp["+ (i) + "]["+m+"][0] = " + dp[i][m][0]);
////                System.out.println( " dp["+ (i - 1) + "]["+m+"][0] = " + dp[i - 1][m][0]);
////                System.out.println(" dp["+(i - 1)+"]["+(m - 1)+"][1] + prices["+(i - 1)+"] = " + (dp[i - 1][m - 1][1] + prices[i - 1]));
//
//                dp[i][m][1] = Math.max(dp[i - 1][m][1], dp[i - 1][m][0] - prices[i - 1]);
//
////                System.out.println( " dp["+ (i) + "]["+m+"][1] = " + dp[i][m][1]);
////                System.out.println( " dp["+(i - 1)+"]["+m+"][1] = " + dp[i - 1][m][1]);
////                System.out.println( " dp["+(i - 1)+"]["+m+"][0] - prices["+(i - 1)+"] = " + (dp[i - 1][m][0] - prices[i - 1]));
////                System.out.println();
//            }
//        }
//        return dp[pLen][k][0];
//    }

//    用贪心来做
    private int helper(int[] prices, int pLen){
        if(pLen < 2) return 0;
        int res = 0;
        for(int i = 1; i < pLen; i++){
            int diff = prices[i] - prices[i - 1];
            if(diff > 0) res += diff;
        }
        return res;
    }

//    状态压缩，和上面的代码共用helper，这里还是沿用卖出的时候交易次数+1
//    但是根据上面的总结，显然这个没有买入的时候加来得好
    public int maxProfit(int k, int[] prices) {
        if(k == 0 || prices.length == 0) return 0;
        int pLen = prices.length;
        if(k > pLen / 2) return helper(prices, pLen);
        int[][] dp = new int[k + 1][2];

        for(int m = 0; m <= k; m++){
            dp[m][1] = Integer.MIN_VALUE;
        }

        for(int i = 1; i <= pLen; i++){
            for(int m = 1; m <= k; m++){
            /*
            还是和上面的总结一样，二维数组简化了以后，对于初始化的要求更高了
            所以以后还是在买入的时候交易次数+1比较好，这样可以少很多麻烦
            核心问题还是在于和上面三维数组一样，dp[0][1]的定义问题，
            每次都需要更新一下在第一次买入的时候的状态，因为在卖出交易次数才+1，
            所以这是个合法状态要实时更新
            */
            dp[0][1] = Math.max(dp[0][1], -prices[i - 1]);
            dp[m][1] = Math.max(dp[m][1], dp[m][0] - prices[i - 1]);
            dp[m][0] = Math.max(dp[m][0], dp[m - 1][1] + prices[i - 1]);
            }
        }
        return dp[k][0];
    }

    public static void main(String[] args) {
//        答案为7
        int[] testBench = {3, 2, 6, 5, 0, 3};
        System.out.println(new lc188().maxProfit(2, testBench));

//        答案为11
        int[] testBench1 = {2,1,4,5,2,9,7};
        System.out.println(new lc188().maxProfit(2, testBench1));
    }
}
