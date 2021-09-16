package amazon;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class lc994 {
    private int[][] grid;
    private int[][] dirs = {{1,0},{0,-1},{-1,0},{0,1}};
//    本来用的boolean，但是实际写代码的时候会发现不方便，所以改了
    private int[][] visited;
    private int row, col;
    public int orangesRotting(int[][] grid) {
        this .grid = grid;
        row = grid.length;
        col = grid[0].length;
        visited = new int[row][col];
        int res = 0;
        for(int i = 0; i < row; i++){
            System.arraycopy(grid[i], 0, visited[i], 0, col);
        }
        Queue<int[]> queue = new LinkedList<>();
//        这里是针对下面第三个test case的情况，也就是如果上来就没有可传染的橙子
        int count = 0;
        for(int i = 0; i < row; i++){
            for(int k = 0; k <col; k++){
                if(grid[i][k] == 2) {
                    queue.add(new int[]{i, k});
                    count++;
                }else if(grid[i][k] == 0) count++;
            }
        }
        if(count == row * col) return res;
        while (!queue.isEmpty()){
            int qLen = queue.size();
            for(int i = 0; i < qLen; i++){
                int[] temp = queue.poll();
                for(int[] dir : dirs){
                    int x = temp[0] + dir[0];
                    int y = temp[1] + dir[1];
                    if(isValid(x, y) && visited[x][y] == 1){
                        visited[x][y] = 2;
                        queue.add(new int[]{x, y});
                    }
                }
            }
//            if(queue.isEmpty()) return res;
            res++;
//            System.out.println("res = " + res + " queueSize = " + queue.size());
        }
        for(int i = 0; i < row; i++){
            for(int k = 0; k <col; k++){
                if(visited[i][k] == 1) return -1;
            }
        }
        return res - 1;
    }

    private boolean isValid(int a, int b){
        return 0 <= a && a < row && 0 <= b && b < col;
    }

    public static void main(String[] args) {
//        int[][] test = {{2,1,1},{0,1,1},{1,0,1}};
//        System.out.println(new lc994().orangesRotting(test));

        int[][] test = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(new lc994().orangesRotting(test));

//        专门针对corner case
//        int[][] test = {{2}};
//        System.out.println(new lc994().orangesRotting(test));
    }
}
