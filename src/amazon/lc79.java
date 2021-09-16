package amazon;

/*
这道题目要自己做一遍才知道里面的坑多，要自己做！！
* */

public class lc79 {
    private int[][] dirs = {{1,0}, {0,-1}, {-1,0},{0,1}};
    private int row, col, wLen;
    private boolean[][] visited;
    private char[][] board;
    private String word;
    private char[] wChar;
    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        row = board.length;
        if( board == null ||row == 0) return false;
        col = board[0].length;
        wLen = word.length();
        wChar = word.toCharArray();
        visited = new boolean[row][col];
        for(int i = 0; i < row; i++){
            for(int k = 0; k < col; k++){
                if(!visited[i][k] && BackTracking(i, k, 0)) return true;
            }
        }
        return false;
    }

    private boolean BackTracking(int row1, int col1, int index){
//        用第二部分的testcase就知道为什么要在这一层就解决，而不是index == wLen
        if(index == wLen - 1 && board[row1][col1] == wChar[index] && !visited[row1][col1]) return true;
        else if(board[row1][col1] == wChar[index]){
            visited[row1][col1] = true;
            for(int[] dir : dirs){
                int x = row1 + dir[0];
                int y = col1 + dir[1];
                if(x >= 0 && x < row && y >= 0 && y < col && !visited[x][y]){
                    if(BackTracking(x, y, index + 1)) return true;

                }
            }
            visited[row1][col1] = false;
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] test = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
//        String test1 = "ABCCED";
        String test1 = "ABCB";
//        String test1 = "SEE";
//        String test1 = "BFC";
        System.out.println(new lc79().exist(test, test1));

//        char[][] test = {{'a'}};
//        char[][] test = {{'a','a'}};
//        String test1 = "aa";
//        System.out.println(new lc79().exist(test, test1));
    }
}
