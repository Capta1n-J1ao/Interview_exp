package amazon;

/*
其实题解都说的不太行，主要思想其实和dp的一道字符串的题目很像，就是看左上角的最小值的。
多看几个题解就懂了
其实吧dp定义为右下角其实也是为了无后效性
* */

public class lc221 {
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        if(row < 1 || matrix == null) return 0;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        int res = 0;
        for(int i = 0; i < row; i++){
            for(int k = 0; k < col; k++){
                if(matrix[i][k] == '1'){
//                    corner case
                    if(i == 0 || k == 0) dp[i][k] = 1;
                    else dp[i][k] = Math.min(Math.min(dp[i - 1][k], dp[i - 1][k - 1]), dp[i][k - 1]) + 1;
                    res = Math.max(res, dp[i][k]);
                }
            }
        }
//        易错，上面求的是边长
        return res * res;
    }
}
