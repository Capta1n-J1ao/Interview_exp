package amazon;

/*
用常规方法即可，下次可以直接跳过，挺简单的
唯一的难点就是dupBoard的复制，不能直接dupBoard = board，这样还是board，要重新新建一个
* */

import java.util.Arrays;

public class lc289 {
    private int[][] dupBoard;
    private int[][] board;
    private int[][] dirs = {{1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}};
    int row, col;
    public void gameOfLife(int[][] board) {
        this.board = board;
        row = board.length;
        if(board == null || row == 0) return;
        col = board[0].length;
        dupBoard = new int[row][col];
        for(int i = 0; i < row; i++){
            for(int k = 0; k < col; k++){
                dupBoard[i][k] = board[i][k];
            }
        }
        for(int i = 0; i < row; i++){
            for(int k = 0; k < col; k++){
                BFS(i, k,0);
            }
        }
    }

    private void BFS(int row1, int col1, int cellCount){
        for(int[] dir : dirs){
            int x = row1 + dir[0];
            int y = col1 + dir[1];
            if(x >= 0 && x < row && y >= 0 && y < col && dupBoard[x][y] == 1){
                cellCount++;
            }
        }
//        System.out.println("cellCount = " + cellCount);
        if(dupBoard[row1][col1] == 1){
            if(cellCount < 2 || cellCount > 3) board[row1][col1] = 0;
        }else if(dupBoard[row1][col1] == 0){
            if(cellCount == 3) board[row1][col1] = 1;
        }
    }

    public static void main(String[] args) {
        int[][] test = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        new lc289().gameOfLife(test);
        for(int[] res : test){
            System.out.println(Arrays.toString(res));
        }
    }
}
