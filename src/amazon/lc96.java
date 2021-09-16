package amazon;

/*
先做lc95，再做96，下面这个视频说的很清楚：
https://www.bilibili.com/video/BV12C4y1h7Ao?t=609
* */

public class lc96 {
//    public int numTrees(int n) {
//        int[] dp = new int[n + 1];
//        dp[0] = 1;
//        dp[1] = 1;
//        for(int i = 2; i <= n; i++){
//            for(int k = 0; k < i; k++){
//                dp[i] += dp[k] * dp[i - k - 1];
//            }
//        }
//        return dp[n];
//    }

//    二刷,2021/08/02
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            for(int k = 1; k <= i; k++){
                dp[i] += dp[k - 1] * dp[i - k];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new lc96().numTrees(3));
    }
}
