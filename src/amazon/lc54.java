package amazon;

/*
lc54/59 可以同时服用，很类似
题解,注意第二个test case极度tricky：
https://leetcode-cn.com/problems/spiral-matrix/submissions/
* */

import java.util.LinkedList;
import java.util.List;

public class lc54 {
//  模拟四种移动行为
    private int[][] dirs=new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    private boolean[][] visited;
    public List<Integer> spiralOrder(int[][] matrix) {
//        已经对row和col做了定义，所以就不额外判定corner case了
        int count = 1, row = matrix.length, col = matrix[0].length,
                status = 0, row1 = 0, col1 = 0;
        visited = new boolean[row][col];
        List<Integer> res = new LinkedList<>();
        while (count <= row * col){
//            System.out.println("row1 = " + row1 + " " + "col1 = " + col1);
            res.add(matrix[row1][col1]);
            visited[row1][col1] = true;
            int[] dir = dirs[status];
            int x = row1 + dir[0];
            int y = col1 + dir[1];
            if(x < 0 || x >= row || y < 0 || y >= col || visited[x][y]) status = (status + 1) % 4;
            dir = dirs[status];
            row1 += dir[0];
            col1 += dir[1];
            count++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] test = {{1,2,3},{4,5,6}};
        System.out.println(new lc54().spiralOrder(test));

//        int[][] test1 = {{2},{3}};
//        System.out.println(new lc54().spiralOrder(test));

//        int[][] test2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
//        System.out.println(new lc54().spiralOrder(test));
    }
}
