package amazon;

/*
这道题目一开始在搜索和DP里面纠结，我的思路和liweiwei一模一样，
一开始想的就是需要一个数据结构代表{当前路径所需最小血量，连续负数的最大值}
看了liweiwei的题解以下就全懂了，官解就是liweiwei的题解，但是他对状态转移方程解释的不详细，
可以看第二个题解，但是代码的话还是参考liweiwei，很漂亮：
https://leetcode-cn.com/problems/dungeon-game/solution/di-xia-cheng-you-xi-by-leetcode-solution/

下面这个是liweiwei文字版的更加详细版，状态转移很清晰，
难点就是想明白min(dp[i+1][k], dp[i][k + 1]) - dungeon[i][k]
这里有个要点就是如果dungeon[i][k]是负值，整个dp其实是增加的；反之，则是减小的：
https://leetcode-cn.com/problems/dungeon-game/solution/dong-tai-gui-hua-jie-lu-jing-zhi-di-xia-cheng-you-/

* */

import java.util.Arrays;

public class lc174 {
    public int calculateMinimumHP(int[][] dungeon) {
        int row = dungeon.length, col = dungeon[0].length;
//        用这个方法代码优雅很多
        int[][] dp = new int[row + 1][col + 1];
        for(int i = 0; i <= row; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
//        为了确定终点格子的值
        dp[row][col - 1] = 1;
        dp[row - 1][col] = 1;
        for(int i = row - 1; i >= 0; i--){
            for(int k = col - 1; k >= 0; k--){
                dp[i][k] = Math.max(Math.min(dp[i + 1][k], dp[i][k + 1]) - dungeon[i][k], 1);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] test = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        System.out.println(new lc174().calculateMinimumHP(test));
    }
}
