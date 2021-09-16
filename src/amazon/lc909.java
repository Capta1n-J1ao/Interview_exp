package amazon;

/*
https://leetcode-cn.com/problems/snakes-and-ladders/solution/ben-zhi-bfszhao-zui-duan-lu-jing-java-by-mattychen/
* */

import java.util.LinkedList;
import java.util.Queue;

public class lc909 {
    public int snakesAndLadders(int[][] board) {
        int row = board.length;
        int[] decodeBoard = new int[row * row + 1];
        boolean reverse = false;
        int index = 1;
        for(int i = row - 1; i >= 0; i--){
            for(int k = 0; k < row; k++){
                if(reverse){
//                    根据题解来的，太巧妙
                    decodeBoard[index] = board[i][(row - 1) - (k % row)];
                }else {
                    decodeBoard[index] = board[i][k];
                }
                index++;
            }
            reverse = !reverse;
        }

        Queue<Integer> queue = new LinkedList<>();
        int start = 1, depth = 0;
        boolean[] visited = new boolean[row * row + 1];
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()){
            int qLen = queue.size();
            for(int i = 0; i < qLen; i++){
                int temp = queue.poll();
//                visited[temp] = true;
                if(temp == row * row) return depth;
                for(int k = 1; k <= 6; k++){
                    if(temp + k > row * row) continue;
//                    这句话是精髓，不能写在if里面，排查了很久，下面visited的判断应该判断decodeBoard里面的内容，
//                    而不是判断temp + k!!!
//                    以下面test case里面的第四个数里面8为例，如果写在if里面，虽然把8放进了queue，
//                    但是但是同时对visited进行了操作，那么当queue poll出8的时候不会对他进行操作，
//                    经过第一轮循环的时候，queue里面是2,3,8,6，在对2进行操作的时候2+6得到8的时候，
//                    按理说这时候应该到56的，但是由于visited[8]被置T了，所以被跳过了，也就没有了去56的途径。
//                    这就是最tricky的地方
                    int nextStep = decodeBoard[temp + k] == -1 ? temp + k : decodeBoard[temp + k];
                    if(!visited[nextStep]){
//                        int nextStep = decodeBoard[temp + k] == -1 ? temp + k : decodeBoard[temp + k];
                        if(!visited[nextStep]){
                            queue.add(nextStep);
                            visited[nextStep] = true;
                        }
                    }
                }
            }
            depth++;
        }
        return -1;
    }

    public static void main(String[] args) {
//        int[][] test = {{-1,-1},{-1,-1}};
//        System.out.println(new lc909().snakesAndLadders(test));
        int[][] test = {{-1,-1,-1,46,47,-1,-1,-1},{51,-1,-1,63,-1,31,21,-1},
                        {-1,-1,26,-1,-1,38,-1,-1},{-1,-1,11,-1,14,23,56,57},
                        {11,-1,-1,-1,49,36,-1,48},{-1,-1,-1,33,56,-1,57,21},
                        {-1,-1,-1,-1,-1,-1,2,-1},{-1,-1,-1,8,3,-1,6,56}};
        System.out.println(new lc909().snakesAndLadders(test));
    }
}
