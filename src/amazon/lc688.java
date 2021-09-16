package amazon;
/*
主要感觉这个状态转移方程很难想，不常规，难点就是要搞清状态方程的意义
下面这个题解更加符合我的思路：
https://leetcode-cn.com/problems/knight-probability-in-chessboard/solution/java-hui-su-suan-fa-by-don-vito-corleone-2/

对于状态转移方程，可能这个题解说的更清楚：
https://leetcode-cn.com/problems/knight-probability-in-chessboard/solution/zhi-li-yu-ba-dpfang-cheng-jiang-qing-chu-by-what-t/

也可以结合huahua的视频，可以有空的时候用他的思路写下：
https://www.bilibili.com/video/BV1Hb411c7mu?from=search&seid=9874312495017242989
* */

public class lc688 {
    private int[][]dirs = new int[][]{{-2,-1},{-1,-2},{1,-2},{2,-1},{2,1},{1,2},{-1,2},{-2,1}};
    private double[][][] dp;
    private int n, k;
    public double knightProbability(int n, int k, int row, int column) {
        this.n = n;
        this.k = k;
        dp = new double[n][n][k];
        return helper(row, column, k);
    }

    private double helper(int r, int c, int steps2Go){
        if (steps2Go == 0) return 1.0;
//        这句话很关键，否则会有很多重复计算，不写直接超时
        if(dp[r][c][steps2Go] != 0) return dp[r][c][steps2Go];
        double curRes = 0;
        for(int[] dir : dirs){
            int x = r + dir[0];
            int y = c + dir[1];
            if(!isValid(x, y)) continue;
            curRes += 0.125 * helper(x, y, steps2Go - 1);
        }
        dp[r][c][steps2Go] = curRes;
        return curRes;
    }

    private boolean isValid(int a, int b){
        return 0 <= a && a < n && 0 <= b && b < n;
    }
}
