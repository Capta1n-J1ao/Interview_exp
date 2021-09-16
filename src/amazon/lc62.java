package amazon;

import java.util.Arrays;

public class lc62 {
    private int[][] dp;
    public int uniquePaths(int m, int n) {
        if(m == 0 || n == 0) return 0;
        else if(m == 1 || n == 1) return 1;
        dp = new int[m][n];
        for(int i = 0; i < m; i++){
            Arrays.fill(dp[i], 1);
        }
        for(int i = 1; i < m; i++){
            for(int k = 1; k < n; k++){
                dp[i][k] = dp[i - 1][k] + dp[i][k - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new lc62().uniquePaths(3,7));
    }
}
