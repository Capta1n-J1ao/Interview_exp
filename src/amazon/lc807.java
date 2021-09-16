package amazon;

/*
这道题目代码很简单，但是难在思想，基本靠自己想出来了，题解：
https://leetcode-cn.com/problems/max-increase-to-keep-city-skyline/solution/bao-chi-cheng-shi-tian-ji-xian-by-leetcode/
* */

public class lc807 {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int len = grid.length;
        int[] rowMax = new int[len];
        int[] colMax = new int[len];
        for(int i = 0; i < len; i++){
          for(int k = 0; k < len; k++){
              rowMax[i] = Math.max(rowMax[i], grid[i][k]);
              colMax[k] = Math.max(colMax[k], grid[i][k]);
          }
        }
        int res = 0;
        for(int i = 0; i < len; i++){
            for(int k = 0; k < len; k++){
                res += Math.min(rowMax[i], colMax[k]) - grid[i][k];
            }
        }
        return res;
    }
}
