package amazon;

/*
完全自己想的方法，没有参考任何东西
思路就是一个岛屿边长算不算由两个东西决定，针对的是岛屿的四周来说
1. 如果岛屿的某一条边就在边界，那么这个岛屿的这条边界的边一定能加到结果里
2. 如果岛屿的某一条边上是海水而不是岛屿，那么这个岛屿的这条边界能加到结果里
* */

public class lc463 {
    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int row, col;
    public int islandPerimeter(int[][] grid) {
        row = grid.length;
        col = grid[0].length;
        int res = 0;
        for(int i = 0; i < row; i++){
            for(int k = 0; k < col; k++){
                if(grid[i][k] == 1){
                    for(int[] dir : dirs){
                        int x = i + dir[0];
                        int y = k + dir[1];
                        if(isValid(x, y) && grid[x][y] == 1) continue;
                        res++;
                    }
                }
            }
        }
        return res;
    }

    private boolean isValid(int row1, int col1){
        return 0 <= row1 && row1 < row && 0 <= col1 && col1 < col;
    }
}
