package amazon;

/*
注意DFS会超时，记得不要用这个办法。
* */

public class lc63 {
//    DFS超时
//    private int[][] dirs = {{0,1},{1,0}};
//    private int[][] obstacleGrid;
//    private int row, col, res = 0;
//    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
//        this.obstacleGrid = obstacleGrid;
//        row = obstacleGrid.length;
//        if(row == 0) return 0;
//        col = obstacleGrid[0].length;
//        if(obstacleGrid[0][0] == 1 ||obstacleGrid[row - 1][col - 1] == 1) return 0;
//        DFS(0, 0);
//        return res;
//    }
//
//    private void DFS(int row1, int col1){
////        System.out.println(row1 + " " + col1);
//        if(row1 == row - 1 && col1 == col - 1){
//            res++;
//            return;
//        }
//        for(int[] dir : dirs){
//            int x = row1 + dir[0];
//            int y = col1 + dir[1];
//            if(isValid(x, y) && obstacleGrid[row1][col1] == 0) DFS(x, y);
//        }
//    }
//
//    private boolean isValid(int a, int b){
//        return 0 <= a && a < row && 0 <= b && b < col;
//    }


    private int[][] dirs = {{-1,0},{0,-1}};
    private int[][] obstacleGrid;
    private int row, col, res = 0;
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        this.obstacleGrid = obstacleGrid;
        row = obstacleGrid.length;
        if(row == 0) return 0;
        col = obstacleGrid[0].length;
        if(obstacleGrid[0][0] == 1 ||obstacleGrid[row - 1][col - 1] == 1) return 0;
        int[][] dp = new int[row][col];
        for(int i = 0; i < col; i++){
            //这个的逻辑核心就在于如果在一行或者一列里面发现了有一个障碍物，
            // 那么这个一行或者一列之后的格子都没法到达
            if(obstacleGrid[0][i] == 1) break;
            dp[0][i] = 1;
        }
        for(int i = 0; i < row; i++){
            if(obstacleGrid[i][0] == 1) break;
            dp[i][0] = 1;
        }
        for(int i = 1; i < row; i++){
            for(int k = 1; k < col; k++){
                if(obstacleGrid[i][k] == 1){
                    dp[i][k] = 0;
                }else {
                    dp[i][k] = dp[i - 1][k] + dp[i][k - 1];
                }
            }
        }
        return dp[row - 1][col - 1];
    }

    public static void main(String[] args) {
        int[][] test = {{0, 0, 0},{0, 1, 0},{0, 0,0}};
        System.out.println(new lc63().uniquePathsWithObstacles(test));
    }
}
