package amazon;

/*
还有就是这道题目有一个很大的难点就是怎么判断棋盘是不是符合要求，但是一旦想到难度会瞬间降低很多

这道题目思路看labuladong的，很清晰，但是由于他用的C++，所以要找个java的题解
https://leetcode-cn.com/problems/sliding-puzzle/solution/java-zhuan-zi-fu-chuan-chu-li-bfs-chao-guo-98-si-l/
* */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class lc773 {
    int[][] dirs = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
    public int slidingPuzzle(int[][] board) {
        int row = board.length;
        int col = board[0].length;
        StringBuilder src = new StringBuilder();
        for(int i = 0; i < row; i++){
            for(int k = 0; k < col; k++){
                src.append(board[i][k]);
            }
        }
        String cur = src.toString();
        String target = "123450";
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        queue.add(cur);
        visited.add(cur);
        int res = 0;
        while (!queue.isEmpty()){
            int qLen = queue.size();
            for(int i = 0; i < qLen; i++){
                String temp = queue.poll();
                if(temp.equals(target)) return res;
                int zeroPos = temp.indexOf('0');
                for(int dir : dirs[zeroPos]){
                    String nextStep = swap(temp, zeroPos, dir);
                    if(visited.contains(nextStep)) continue;
                    queue.add(nextStep);
                    visited.add(nextStep);
                }
            }
            res++;
        }
        return -1;
    }

    private String swap(String str, int curPos, int nextPos){
        char[] sChar = str.toCharArray();
        char temp = sChar[curPos];
        sChar[curPos] = sChar[nextPos];
        sChar[nextPos] = temp;
//        这里用toString就不对！一定要养成习惯少用这个语句！！
        return String.valueOf(sChar);
    }

    public static void main(String[] args) {
        int[][] test = {{1,2,3},{4,0,5}};
        System.out.println(new lc773().slidingPuzzle(test));
    }
}
