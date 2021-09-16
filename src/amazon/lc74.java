package amazon;

/*
说一下思路即可，可以不写代码，很简单
* */

public class lc74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length, curRow = row - 1, curCol = 0;
        while (0 <= curRow && curRow < row && 0 <= curCol && curCol < col){
            int curNum = matrix[curRow][curCol];
            if(curNum == target) return true;
            else if(curNum > target) curRow--;
            else if(curNum < target) curCol++;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] test = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        System.out.println(new lc74().searchMatrix(test, 3));
    }
}
