package amazon;

/*
自己想的时候已经很接近答案了，就是个套路，记住即可
* */

public class lc48 {
    private int[][] matrix;
    public void rotate(int[][] matrix) {
        this.matrix = matrix;
        int row = matrix.length;
        for(int i = 0; i < row; i++){
            for(int k = i + 1; k < row; k++){
                int temp = matrix[i][k];
                matrix[i][k] = matrix[k][i];
                matrix[k][i] = temp;
            }
        }
        for(int i = 0; i < row; i++){
            int left = 0, right = row - 1;
            while (left < right){
                swap(i, left, right);
                left++;
                right--;
            }
        }
    }

    private void swap(int row, int left, int right){
        int temp = matrix[row][left];
        matrix[row][left] = matrix[row][right];
        matrix[row][right] = temp;
    }
}
