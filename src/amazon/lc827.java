package amazon;

/*
主要是思路，如果思路想出来了应该挺好做,题解如下:
https://leetcode-cn.com/problems/making-a-large-island/solution/dao-yu-wen-ti-mei-you-na-yao-nan-du-li-x-cgbv/
* */

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class lc827 {
    private int[][] matrix, dirs = {{1,0}, {0,-1}, {-1,0},{0,1}};
    private int row, col, res = 0;
    private HashMap<Integer, Integer> hashMap;
    public int largestIsland(int[][] grid) {
        row = grid.length;
        col = grid[0].length;
        if(grid == null || row == 0) return 1;
        matrix = new int[row][col];
//        复习了一下数组拷贝的命令，注意Arrays.copyOf返回的是T[]
        for(int i = 0; i < row; i++){
            matrix[i] = Arrays.copyOf(grid[i], grid[i].length);
        }
//        不用visited的作用就是被index替代了
        int index = 2;
        hashMap = new HashMap<>();
        for(int i = 0; i < row; i++){
            for(int k = 0; k < col; k++){
                int curRes = 0;
                if(matrix[i][k] == 1) curRes = DFS(i, k, index);
                hashMap.put(index, curRes);
                index++;
                res = Math.max(res, curRes);
//                System.out.println(curRes);
            }
        }
        if(res == 0) return 1;

        for(int i = 0; i < row; i++){
            for(int k = 0; k < col; k++){
                int curAddArea = 0;
                if(matrix[i][k] == 0){
                    curAddArea = searchJoint(i, k);
                    res = Math.max(res, curAddArea);
                }
            }
        }
        return res;
    }

    private int DFS(int row1, int col1, int index){
        if(matrix[row1][col1] != 1) return 0;
        matrix[row1][col1] = index;
        int curArea = 1;
        for(int[] dir : dirs){
            int x = row1 + dir[0];
            int y = col1 + dir[1];
            if(isValid(x, y)) {
                curArea += DFS(x, y, index);
//                System.out.println( index+ " " + curArea);
            }
        }
        return curArea;
    }

    private int searchJoint(int row1, int col1){
        int curRes = 1;
//        用这个方法就是防止重复
        HashSet<Integer> hashSet = new HashSet<>();
        for(int[] dir : dirs){
            int x = row1 + dir[0];
            int y = col1 + dir[1];
            if(isValid(x, y) && matrix[x][y] != 0) {
                hashSet.add(matrix[x][y]);
            }
        }
        for(int num : hashSet){
            curRes += hashMap.get(num);
        }
        return curRes;
    }

    private boolean isValid(int row1, int col1){
        return 0 <= row1 && row1 < row && 0 <= col1 && col1 < col;
    }

    public static void main(String[] args) {
        int[][] test = {{1,0,0},{0,1,1},{0,1,0}};
        System.out.println(new lc827().largestIsland(test));
    }
}
