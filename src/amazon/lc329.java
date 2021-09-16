package amazon;

/*
如果只用DFS会超时，所以加了个dp来记录之前写下来的值，进行一个记忆化，
但是不要小看这个记忆化，其实思路是截然不同了，由于这道题目题解不是很多，所以我会更多来写注释
要注意的重点就是在优化版本里面，有以下几点：
1. 注意helper函数的含义
2. 注意语句 dp[row1][col1] = Math.max(dp[row1][col1], DFS(x, y) + 1); 的作用
而这个功能下面题解会说：
https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/solution/java-dfs-you-hua-di-gui-shi-pin-jiang-jie-dai-ma-j/
* */

import java.util.Arrays;

public class lc329 {
//    此方法是常规DFS，可以算easy题
//    private int[][] dirs = {{1,0},{0,-1},{-1,0},{0,1}};
//    private int[][] matrix, dp;
//    private boolean[][] visited;
//    private int res = 0, row, col;
//    public int longestIncreasingPath(int[][] matrix) {
//        this.matrix = matrix;
//        row = matrix.length;
//        col = matrix[0].length;
//        if(row == 1 && col == 1) return 1;
//        dp = new int[row][col];
////        visited = new boolean[row][col];
//        for(int i = 0; i < row; i++){
//            for(int k = 0; k < col; k++){
//                DFS(i, k, 1);
//            }
//        }
//        return res;
//    }
//
//    private void DFS(int row1, int col1, int curRes){
//        res = Math.max(res, curRes);
//        for(int[] dir : dirs){
//            int x = row1 + dir[0];
//            int y = col1 + dir[1];
//            if(isValid(x, y)){
//                if(matrix[x][y] > matrix[row1][col1]){
//                    DFS(x, y, curRes + 1);
//                }
//            }
//        }
//    }


/*
1. 时间复杂度：O(mn)，其中 m 和 n 分别是矩阵的行数和列数。
   深度优先搜索的时间复杂度是 O(V+E)O(V+E)，其中 V 是节点数，E 是边数。
   在矩阵中，O(V)=O(mn)，O(E)≈O(4mn)=O(mn)。
2. 空间复杂度：O(mn)，其中 m 和n 分别是矩阵的行数和列数。
   空间复杂度主要取决于缓存和递归调用深度，缓存的空间复杂度是 O(mn)，递归调用深度不会超过 mn。
* */


//    改进版为了让代码更加清晰，用了visited，
//    如果想要性能更加好可以用题解里面那个直接判断dp里面的数
    private int[][] dirs = {{1,0},{0,-1},{-1,0},{0,1}};
    private int[][] matrix, dp;
    private boolean[][] visited;
    private int res = 0, row, col;
    public int longestIncreasingPath(int[][] matrix) {
        this.matrix = matrix;
        row = matrix.length;
        col = matrix[0].length;
        if(row == 1 && col == 1) return 1;
        dp = new int[row][col];
        visited = new boolean[row][col];
        for(int[] curDP : dp){
            Arrays.fill(curDP, 1);
        }
        for(int i = 0; i < row; i++){
            for(int k = 0; k < col; k++){
                DFS(i, k);
            }
        }
        return res;
    }

//    注意这个函数的定义是 返回：以matrix[row1][col1]为起点的最长路径，这个含义很重要
    private int DFS(int row1, int col1){
        if(visited[row1][col1]) return dp[row1][col1];
        visited[row1][col1] = true;
        for(int[] dir : dirs){
            int x = row1 + dir[0];
            int y = col1 + dir[1];
            if(isValid(x, y) && matrix[x][y] > matrix[row1][col1]){
                dp[row1][col1] = Math.max(dp[row1][col1], DFS(x, y) + 1);
                DFS(x, y);

            }
        }
        res = Math.max(res, dp[row1][col1]);
        return dp[row1][col1];
    }

    private boolean isValid(int row1, int col1){
        return 0 <= row1 && row1 < row && 0 <= col1 && col1 < col;
    }

    public static void main(String[] args) {
        int[][] test = {{9,9,4},{6,6,8},{2,1,1}};
        System.out.println(new lc329().longestIncreasingPath(test));
    }
}
