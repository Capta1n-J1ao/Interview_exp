package amazon;

/*
这里labuladong的写法和liweiwei之前的写法很接近，直接套用即可：
https://leetcode-cn.com/problems/longest-palindromic-subsequence/solution/zi-xu-lie-wen-ti-tong-yong-si-lu-zui-chang-hui-wen/
类似题目 5/516/647，可以一起服用
* */

public class lc516 {
    public int longestPalindromeSubseq(String s) {
        char[] sChar = s.toCharArray();
        int sLen = sChar.length;
        if(sLen < 2) return sLen;
        int[][] dp = new int[sLen][sLen];
        for(int i = 0; i < sLen; i++){
            dp[i][i] = 1;
        }
        for(int i = sLen - 1; i >= 0; i--){
            for(int k = i + 1; k < sLen; k++){

                int curLen = k - i + 1;
                if(sChar[i] == sChar[k]){
//                下面这个判断其实是为了和lc5一致，这样可以让填表的思路更加清晰，其实就应该这么写
                    if(curLen <= 2) dp[i][k] = 2;
                    else dp[i][k] = dp[i + 1][k - 1] + 2;
                }else {
                    dp[i][k] = Math.max(dp[i + 1][k], dp[i][k - 1]);
                }
            }
        }
        return dp[0][sLen - 1];
    }

    public static void main(String[] args) {
        System.out.println(new lc516().longestPalindromeSubseq("bbbab"));
    }
}
