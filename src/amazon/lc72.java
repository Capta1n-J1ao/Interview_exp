package amazon;

/*
建议 583/72/10/97 一起服用
属于同一个类型，而且可以用583的这个格式做一个模板
而且要注意lc583和lc72还是有区别的，lc583是只能删除，就是通过word1和Word2各自删除
但是lc72是可以删除并且可以添加，这是两道题目的区别

liweiwei的题解:
https://leetcode-cn.com/problems/edit-distance/solution/dong-tai-gui-hua-java-by-liweiwei1419/

但是在这次做的时候，由于间隔了很久，所以对于不相等的时候为什么是那三个取最小值有点不清楚
所以结合了huahua的视频，看前几分钟就行了：
https://www.bilibili.com/video/BV1cb411u7uX?from=search&seid=3019706525389281184
这里总结一下就是，如果拿 abbc和adc比较
1. 因为最后的c相等，所以结果就等于abb变化到ad的编辑次数

由于上面的结果依赖于前面的编辑次数，接下来对比abb和ad，有三种情况
1. 替换：ab变为a的编辑次数 + b替换为d的次数（1次）
2. 删除abb中最后一个b，那就是ab变为ad的编辑次数 + abb删掉最后一个b的次数（1次）
3. 删除ad中最后一个d，那就是用abb变为a的编辑次数 + ad删掉d的次数（1次）

* */

public class lc72 {
//    public int minDistance(String word1, String word2) {
//        int w1Len = word1.length();
//        int w2Len = word2.length();
//        char[] w1char = word1.toCharArray();
//        char[] w2char = word2.toCharArray();
//        int[][] dp = new int[w1Len + 1][w2Len + 1];
////        建议套用lc583的模板，这样可以简洁很多
//        for(int i = 0; i <= w1Len; i++) dp[i][0] = i;
//        for(int i = 0; i <= w2Len; i++) dp[0][i] = i;
//        for(int i = 1; i <= w1Len; i++){
//            for(int k = 1; k <= w2Len; k++){
//                if(w1char[i - 1] == w2char[k - 1]) dp[i][k] = dp[i - 1][k - 1];
//                else {
//                    dp[i][k] = Math.min(dp[i - 1][k],Math.min(dp[i][k - 1], dp[i - 1][k - 1])) + 1;
//                }
//            }
//        }
//        return dp[w1Len][w2Len];
//    }

//    二刷,2021/06/18，套用lc583简洁模板
    public int minDistance(String word1, String word2) {
        int w1Len = word1.length();
        int w2Len = word2.length();
        char[] w1char = word1.toCharArray();
        char[] w2char = word2.toCharArray();
        int[][] dp = new int[w1Len + 1][w2Len + 1];
        for(int i = 0; i <= w1Len; i++){
            for(int k = 0; k <= w2Len; k++){
                if(i == 0 || k == 0) dp[i][k] = i + k;
    //            注意模板里面的这个else if必须要有，不能是if，很重要
                else if(w1char[i - 1] == w2char[k - 1]) dp[i][k] = dp[i - 1][k - 1];
                else {
                    dp[i][k] = Math.min(dp[i - 1][k],Math.min(dp[i][k - 1], dp[i - 1][k - 1])) + 1;
                }
            }
        }
        return dp[w1Len][w2Len];
    }
}
