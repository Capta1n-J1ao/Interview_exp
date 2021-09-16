package amazon;

/*
这道题目直接挑战空间复杂度为O(1)的方法，方法很巧妙，就是用首行和首列来记录除了他们之外是不是有0
但是首行和首列的情况需要额外先判断下，是不是包含0，具体代码可以看题解
https://leetcode-cn.com/problems/set-matrix-zeroes/solution/o1kong-jian-by-powcai/
* */

import java.util.Arrays;

public class lc73 {
    public void setZeroes(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean row0 = false, col0 = false;
        for(int i = 0; i < col; i++){
            if(matrix[0][i] == 0) row0 = true;
        }
        for(int i = 0; i < row; i++){
            if(matrix[i][0] == 0) col0 = true;
        }
        for(int i = 1; i < row; i++){
            for(int k = 1; k < col; k++){
                if(matrix[i][k] == 0){
                    matrix[i][0] = 0;
                    matrix[0][k] = 0;
                }
            }
        }
//        注意这里的细节，都是i = 1，不能i =0，否则下面这个testcase会出问题
        for(int i = 1; i < col; i++){
            if(matrix[0][i] == 0) {
                for(int k = 1; k < row; k++) matrix[k][i] = 0;
            }
        }
        for(int i = 1; i < row; i++){
            if(matrix[i][0] == 0){
                for(int k = 1; k < col; k++) matrix[i][k] = 0;
            }
        }
        if(row0){
            for(int i = 0; i < col; i++) matrix[0][i] = 0;
        }
        if(col0){
            for(int k = 0; k < row; k++) matrix[k][0] = 0;
        }
    }

    public static void main(String[] args) {
        int[][] test = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        new lc73().setZeroes(test);
        for(int i = 0; i < test.length; i++) System.out.println(Arrays.toString(test[i]));
    }
}
