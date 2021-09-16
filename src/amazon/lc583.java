package amazon;

/*
和lc72一起服用，属于同一个类型，而且可以用583的这个格式做一个模板
这样就不用单独对dp数组做特殊处理了
而且要注意lc583和lc72还是有区别的，lc583是只能删除，就是通过word1和Word2各自删除
但是lc72是可以删除并且可以添加，这是两道题目的区别
https://leetcode-cn.com/problems/delete-operation-for-two-strings/solution/javadong-tai-gui-hua-by-cra2y-hodgkinqre-7nhe/
* */

public class lc583 {
//    public int minDistance(String word1, String word2) {
//        int w1Len = word1.length();
//        int w2Len = word2.length();
//        char[] w1char = word1.toCharArray();
//        char[] w2char = word2.toCharArray();
//        int[][] dp = new int[w1Len + 1][w2Len + 1];
//        for(int i = 0; i <= w1Len; i++){
//            for(int k = 0; k <= w2Len; k++){
//                if(i == 0 || k == 0) dp[i][k] = i + k;
////            注意模板里面的这个else if必须要有，不能是if，很重要
//                else if(w1char[i - 1] == w2char[k - 1]) dp[i][k] = dp[i - 1][k - 1];
//                else {
////                    和lc72不一样的地方
//                    dp[i][k] = Math.min(dp[i - 1][k],dp[i][k - 1]) + 1;
//                }
//            }
//        }
//        return dp[w1Len][w2Len];
//    }

//    方法二，空间优化
    public int minDistance(String word1, String word2) {
        int w1Len = word1.length();
        int w2Len = word2.length();
        char[] w1char = word1.toCharArray();
        char[] w2char = word2.toCharArray();
        int[] dp = new int[w2Len + 1];
        for(int i = 0; i <= w1Len; i++){
            int[] dp_pre = new int[w2Len + 1];
            for(int k = 0; k <= w2Len; k++){
                dp_pre[k] = dp[k];
                if(i == 0 || k == 0) dp[k] = i + k;
//            注意模板里面的这个else if必须要有，不能是if，很重要
                else if(w1char[i - 1] == w2char[k - 1]) dp[k] = dp_pre[k - 1];
                else dp[k] = Math.min(dp_pre[k],dp[k - 1]) + 1;
            }
        }
        return dp[w2Len];
    }
}
