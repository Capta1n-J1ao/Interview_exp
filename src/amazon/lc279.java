package amazon;

/*
一开始想了个方法，但是不对，因为如果n = 13就不对

dp[i]：表示完全平方数和为i的 最小个数
初始状态dp[i]均取最大值i，即 1+1+...+1，i个1; dp[0] = 0
dp[i] = min(dp[i], dp[i-j*j]+1)，其中, j是平方数, j=1~k,其中k*k要保证 <= i
意思就是：完全平方数和为i的 最小个数 等于 当前完全平方数和为i的 最大个数
与 （完全平方数和为 i - j * j 的 最小个数 + 完全平方数和为 j * j的 最小个数）其中 dp[j*j] 是等于1的
* */

public class lc279 {
//    错误方法
//    public int numSquares(int n) {
//        int[] dp = new int[n + 1];
//        for(int i = 1; i <= n; i++) dp[i] = i;
//        int sqr = n / 2;
//        for(int i = 2; i <= sqr; i++){
//            int sqrNum = i * i;
//            if(sqrNum <= n) dp[sqrNum] = 1;
//            int mul = 2;
//            while (sqrNum * mul <= n){
//                dp[sqrNum * mul] = mul;
//                mul++;
//            }
//        }
//        for(int i = 2; i <= n; i++){
//            dp[i] = Math.min(dp[i], dp[i - 1] + 1);
//        }
//        return dp[n];
//    }

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            dp[i] = i;
            for(int k = 1; k * k <= i; k++){
                dp[i] = Math.min(dp[i], dp[i - k * k] + 1);
            }
        }
        return dp[n];
    }
}
