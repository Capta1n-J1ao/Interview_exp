package amazon;

/*
建议 583/72/10/97 一起服用 特别是做完10再做97应该很简单
参考题解，其实最主要的一个思路就是这个状态转移方程的判断，就是从填表的角度来看，要么从左边要么从上面来：
https://leetcode-cn.com/problems/interleaving-string/solution/lei-si-lu-jing-wen-ti-zhao-zhun-zhuang-tai-fang-ch/
* */

public class lc97 {
    public boolean isInterleave(String s1, String s2, String s3) {
        int s1Len = s1.length(), s2Len = s2.length(), s3Len = s3.length();
//        corner case 处理
        if(s1Len + s2Len != s3Len) return false;
        boolean[][] dp = new boolean[s1Len + 1][s2Len + 1];
//        初始化
        dp[0][0] = true;
        for(int i = 1; i <= s1Len; i++){
            String str1 = s1.substring(0, i);
            String str3 = s3.substring(0, i);
            if(str1.equals(str3)) dp[i][0] = true;
            else break;
        }

        for(int i = 1; i <= s2Len; i++){
            String str2 = s2.substring(0, i);
            String str3 = s3.substring(0, i);
            if(str2.equals(str3)) dp[0][i] = true;
            else break;
        }

        char[] s1Char = s1.toCharArray();
        char[] s2Char = s2.toCharArray();
        char[] s3Char = s3.toCharArray();
        for(int i = 1; i <= s1Len; i++){
            for(int k = 1; k <= s2Len; k++){
                dp[i][k] = (dp[i - 1][k] && s1Char[i - 1] == s3Char[i + k - 1]) ||
                        (dp[i][k - 1] && s2Char[k - 1] == s3Char[i + k - 1]);
            }
        }

        return dp[s1Len][s2Len];
    }
}

