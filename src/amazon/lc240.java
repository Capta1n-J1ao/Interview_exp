package amazon;

public class lc240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;
        if(matrix == null || row == 0) return false;
        int mRow = row - 1, mCol = 0;
        while (mRow >= 0 && mCol < col){
            if(matrix[mRow][mCol] > target) mRow--;
            else if(matrix[mRow][mCol] < target) mCol++;
            else return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] test = {{-1,3}};
        System.out.println(new lc240().searchMatrix(test, 3));
    }
}
