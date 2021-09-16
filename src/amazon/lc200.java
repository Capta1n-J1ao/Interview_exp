package amazon;

/*
这道题目一定要注意循环跳出的条件！！！我在DFS里面排查了很久
* */

public class lc200 {
    private char[][] grid;
    private int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
    private boolean[][] isReach;
    private int res, row, col;
    public int numIslands(char[][] grid) {
        this.grid = grid;
        row = grid.length;
        col = grid[0].length;
        isReach = new boolean[row][col];
        res = 0;
        for(int i = 0; i < row; i++){
            for(int k = 0; k < col; k++){
                if(!isReach[i][k] && grid[i][k] == '1'){
                    DFS(i, k);
                    res++;
                }
            }
        }
        return res;
    }

    private void DFS(int row1, int col1){
        if(row1 < 0 || row1 >= row || col1 < 0 || col1 >= col || grid[row1][col1] == '0') return;
        isReach[row1][col1] = true;
        for(int[] pos : dir){
            int nextRow = row1 + pos[0];
            int nextCol = col1 + pos[1];
            if(nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col && !isReach[nextRow][nextCol]){
                DFS(nextRow, nextCol);
            }
        }
    }

    public static void main(String[] args) {
        char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        System.out.println(new lc200().numIslands(grid));
    }
}
