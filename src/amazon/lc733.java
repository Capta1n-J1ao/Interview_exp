package amazon;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class lc733 {
    private int[][] res;
    private boolean[][] visited;
    private int row, col;
    private int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        row = image.length;
        if(image == null || row == 0) return null;
        col = image[0].length;
        res = image;
        visited = new boolean[row][col];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sr,sc});
        visited[sr][sc] = true;
        int originColor = image[sr][sc];
        if(originColor == newColor) return res;
        res[sr][sc] = newColor;
        while (!queue.isEmpty()){
            int qLen = queue.size();
            for(int i = 0; i < qLen; i++){
                int[] temp = queue.poll();
                for(int[] dir : dirs){
                    int x = temp[0] + dir[0];
                    int y = temp[1] + dir[1];
                    if(isValid(x, y) && image[x][y] == originColor){
                        res[x][y] = newColor;
                        queue.add(new int[]{x,y});
                    }
                }
            }
        }
        return res;
    }

    private boolean isValid(int row1, int col1){
        return 0 <= row1 && row1 < row && 0 <= col1 && col1 < col && !visited[row1][col1];
    }

    public static void main(String[] args) {
        int[][] test = {{0,0,1},{0,1,1}};
        int[][] testRes = new lc733().floodFill(test, 1,1,1);
        for(int[] tr : testRes){
            System.out.println(Arrays.toString(tr));
        }
    }
}
