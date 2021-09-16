package amazon;

/*
就按照自己的思路来，和题解的写法有点区别，但是效率一样的
https://leetcode-cn.com/problems/unique-paths-iii/solution/shen-du-you-xian-suan-fa-hui-su-suan-fa-by-hundanl/
* */

public class lc980 {
    private int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
//    pathLen取值要细节，要包含起点和终点
    private int num = 0,pathLen = 2,len = 1;
    private int row, col;
    private boolean[][] visited;
    private int[][] grid;
    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        if (grid == null || grid.length == 0) return 0;
//        计算路径长度和起点位置
        int xStart = 0, yStart = 0;
        row = grid.length;
        col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int k = 0; k < col; k++) {
                if (grid[i][k] == 0) {
                    pathLen++;
                } else if (grid[i][k] == 1) {
                    xStart = i;
                    yStart = k;
                }
            }
        }
        this.visited = new boolean[row][col];
        BackTracking(xStart, yStart);
        return num;

    }

    private void BackTracking(int row1, int col1) {
        if(!isValid(row1, col1) || grid[row1][col1] == -1 || visited[row1][col1]) return;
        // 抵达终点
        if (grid[row1][col1] == 2) {
            if (len == pathLen) {
                num++;
            }
            return;
        }
        visited[row1][col1] = true;
        len++;
        for(int[] dir : dirs){
            int x = row1 + dir[0];
            int y = col1 + dir[1];
            BackTracking(x, y);
        }
        visited[row1][col1] = false;
        len--;
    }

    private boolean isValid(int a, int b){
        return 0 <= a && a < row && 0 <= b && b < col;
    }
}
