package amazon;

/*
这道题目容易先入为主，看到grid就想使用search里面的一种，但是仔细想想其实没有必要，用dp更快
* */

public class lc64 {
    public int minPathSum(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];
        for(int i = 1; i < col; i++){
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for(int i = 1; i < row; i++){
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for(int i = 1; i < row; i++){
            for(int k = 1; k < col; k++){
                dp[i][k] = Math.min(dp[i - 1][k], dp[i][k - 1]) + grid[i][k];
            }
        }
        return dp[row - 1][col - 1];
    }

    public static void main(String[] args) {
        int[][] test = {{1,2,3}, {4,5,6}};
        System.out.println(new lc64().minPathSum(test));
    }
}
