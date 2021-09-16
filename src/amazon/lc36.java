package amazon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class lc36 {
    public boolean isValidSudoku(char[][] board) {
        List<HashSet<Integer>> rowHash = new ArrayList<>();
        List<HashSet<Integer>> colHash = new ArrayList<>();
        List<HashSet<Integer>> matrixHash = new ArrayList<>();
        for(int i = 0; i < 9; i++){
            for(int k = 0; k < 9; k++){
                if(i == 0) colHash.add(k, new HashSet<>());
                if(k == 0) rowHash.add(i, new HashSet<>());
                int matrixIndex = (i / 3) * 3 + k / 3;
                if(i % 3 == 0 && k % 3 == 0) matrixHash.add(matrixIndex, new HashSet<>());
                if(board[i][k] == '.') continue;
                int curNum = board[i][k] - '0';
                if(colHash.get(k).contains(curNum) || rowHash.get(i).contains(curNum) || matrixHash.get(matrixIndex).contains(curNum)) return false;
                colHash.get(k).add(curNum);
                rowHash.get(i).add(curNum);
                matrixHash.get(matrixIndex).add(curNum);
            }
        }
        return true;
    }
}
