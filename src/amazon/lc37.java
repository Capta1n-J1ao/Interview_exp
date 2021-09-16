package amazon;

/*
这道题的话还是labuladong的讲解最清楚：
https://mp.weixin.qq.com/s/VCirGskFGPln-S2LGFTgKg
上面这个连接里面还有个回溯的链接也不错，也可以参考
https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484709&idx=1&sn=1c24a5c41a5a255000532e83f38f2ce4&chksm=9bd7fb2daca0723be888b30345e2c5e64649fc31a00b05c27a0843f349e2dd9363338d0dac61&scene=21#wechat_redirect

2021/8/17更新：
个人感觉labuladong那个有点繁琐了，下面这个题解更加清晰：
https://leetcode-cn.com/problems/sudoku-solver/solution/hui-su-jie-jue-by-sdwwld/

方法二就是增加了boolean的判断条件，比方法一的小矩阵判断简化了很多，
相当于直接调用，所以速度快了很多，参考题解：
https://leetcode-cn.com/problems/sudoku-solver/solution/hui-su-fa-jie-shu-du-by-i_use_python/
* */

import java.util.Arrays;

public class lc37 {
////   方法一就是很暴力，一看就会，思路也很清晰，性能也尚可
//    private char[][] board;
//    public void solveSudoku(char[][] board) {
//        this.board = board;
//        BackTracking(0,0);
//    }
//
//    private boolean BackTracking(int row, int col){
//        if(row == 9) return true;
//        if(col == 9) return BackTracking(row + 1, 0);
////        System.out.println(row);
////        System.out.println(col);
//        if(board[row][col] != '.') return BackTracking(row, col + 1);
//        for(char ch = '1'; ch <= '9'; ch++){
//            if(!isValid(row, col, ch)) continue;
//            board[row][col] = ch;
//            if(BackTracking(row, col)) return true;
//            board[row][col] = '.';
//        }
//        return false;
//    }
////  这个判断相当于要重新把行、列、以及小方块整个算一遍，每次都需要重新算，所以速度会慢
//    private boolean isValid(int row, int col, char ch){
//        for(int i = 0; i < 9; i++){
//            if(     board[row][i] == ch ||
//                    board[i][col] == ch ||
//                    board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == ch
//            ) return false;
//        }
//        return true;
//    }


//      加boolean数组来解，效率高很多
        private char[][] board;
        private boolean[][] rowUsed, colUsed;
        private boolean[][][] smallGridUsed;
        public void solveSudoku(char[][] board) {
            rowUsed = new boolean[9][10];
            colUsed = new boolean[9][10];
            smallGridUsed = new boolean[3][3][10];
            this.board = board;
            for(int i = 0; i < 9; i++) {
                for(int k = 0; k < 9; k++){
                    if(board[i][k] != '.'){
                        int num = board[i][k] - '0';
//                    System.out.println(num);
                        rowUsed[i][num] = true;
                        colUsed[k][num] = true;
                        smallGridUsed[i / 3][k / 3][num] = true;
                    }
                }
            }
            BackTracking(0,0);
        }

        private boolean BackTracking(int row, int col){
            if(row == 9) return true;
            if(col == 9) return BackTracking(row + 1, 0);
//        System.out.println(row);
//        System.out.println(col);
            if(board[row][col] != '.') return BackTracking(row, col + 1);
            for(char ch = '1'; ch <= '9'; ch++){
                int ch2Num = ch - '0';
                if(rowUsed[row][ch2Num] || colUsed[col][ch2Num] || smallGridUsed[row / 3][col / 3][ch2Num]) continue;
                board[row][col] = ch;
                rowUsed[row][ch2Num] = true;
                colUsed[col][ch2Num] = true;
                smallGridUsed[row / 3][col / 3][ch2Num] = true;
                if(BackTracking(row, col + 1)) return true;
                board[row][col] = '.';
                rowUsed[row][ch2Num] = false;
                colUsed[col][ch2Num] = false;
                smallGridUsed[row / 3][col / 3][ch2Num] = false;
            }
            return false;
        }

    public static void main(String[] args) {
        char[][] test = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'},
        };
        new lc37().solveSudoku(test);
        for(int i = 0; i < test.length; i++){
            System.out.println(Arrays.toString(test[i]));
        }
    }
}
