package amazon;

/*
这道题目是典型的回溯问题，我用的方法是之前做的lc51_ver2里面的方法，并且是自己想的，没有任何提示
个人认为这道题目的难点在于回溯的方法，一开始在思考的时候一直在纠结回溯是一起回溯row和col
还是只回溯row，这个是本题比较难的地方，要仔细思考
* */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class lc51 {
    private List<List<String>> res = new LinkedList<>();
    private char[][] arrRes;
    private int n;
    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        arrRes = new char[n][n];
        for(char[] str : arrRes) Arrays.fill(str, '.');
        BackTracking(0);
        return res;
    }

    private void BackTracking(int row){
        if(row == n){
            List<String> curRes = new LinkedList<>();
            for(char[] str : arrRes){
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < str.length; i++){
                    sb.append(str[i]);
                }
                curRes.add(sb.toString());
//                curRes.add(",");
            }
            res.add(new LinkedList<>(curRes));
            return;
        }
        for(int i = 0; i < n; i++){
            if(isValid(row, i)){
                arrRes[row][i] = 'Q';
                BackTracking(row + 1);
                arrRes[row][i] = '.';
            }else continue;
        }
    }

    private boolean isValid(int row1, int col1){
        for(int i = 0; i < row1; i++){
            if(arrRes[i][col1] == 'Q') return false;
        }

        for(int i = row1 - 1, k = col1 + 1; i >= 0 && k < n; i--, k++){
            if(arrRes[i][k] == 'Q') return false;
        }

        for(int i = row1 - 1, k = col1 - 1; i >= 0 && k >= 0; i--,k--){
            if(arrRes[i][k] == 'Q') return false;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new lc51().solveNQueens(4));
    }
}
