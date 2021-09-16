package amazon;

import java.util.LinkedList;
import java.util.List;

public class lc417 {
    private static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int row,col;
    //大西洋A，太平洋P
    private boolean[][] reachA;
    private boolean[][] reachP;
    private int[][] heights;
    private List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.heights = heights;
        row = heights.length;
        if(row == 0 || heights == null) return res;
        col = heights[0].length;
        reachA = new boolean[row][col];
        reachP = new boolean[row][col];
        for(int i = 0; i < row; i++){
            DFS(i, 0, reachP);
            DFS(i, col - 1, reachA);
        }
        for(int i = 0; i < col; i++){
            DFS(0, i, reachP);
            DFS(row - 1, i, reachA);
        }
        for(int i = 0; i < row; i++){
            for(int k = 0; k < col; k++){
                if(reachA[i][k] && reachP[i][k]){
                    //第二个难点，怎么写？？
                    List<Integer> curRes = new LinkedList<>();
                    curRes.add(i);
                    curRes.add(k);
                    res.add(curRes);
                }
            }
        }
        return res;
    }

    private void DFS(int row1, int col1, boolean[][] reach){
        if(row1 == 0 || row1 == row - 1 ||  col1 ==0 || col1 == col - 1) {
            reach[row1][col1] = true;
//            return;
        }
        for(int[] dir : dirs){
            int x = row1 + dir[0];
            int y = col1 + dir[1];
            if(isValid(x, y)){
//                第三个判断很重要，没有这个就会陷入死循环
                if(heights[row1][col1] <= heights[x][y] && reach[row1][col1] && !reach[x][y]){
                    reach[x][y] = true;
                    DFS(x, y, reach);
                }
            }
        }

    }

    private boolean isValid(int a, int b){
        return 0 <= a && a < row && 0 <= b && b < col;
    }
}
