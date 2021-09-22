package amazon;

/*
lc5有Liweiwei的讲解，很清楚，如果搞不清可以先做lc5：
liweiwei的解法,其实说的很清楚，特别是填表时候的顺序，要遵从无后效性！：
https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
*/

public class lc647 {
//    public int countSubstrings(String s) {
//        int sLen = s.length(), res = 0;
//        if(s == null || sLen == 0) return 0;
//        char[] sChar = s.toCharArray();
//        boolean[][] dp = new boolean[sLen][sLen];
//        for(int k = 0; k < sLen; k++){
//            for(int i = 0; i <= k; i++){
//                if(k - i < 2 && sChar[i] == sChar[k]) {
//                    dp[i][k] = true;
//                    res++;
//                } else {
//                    if(sChar[i] == sChar[k] && dp[i + 1][k - 1]){
//                        dp[i][k] = true;
//                        res++;
//                    }else continue;
//                }
//            }
//        }
//        return res;
//    }


//    20210921二刷 twitter OA
    public int countSubstrings(String s) {
        int sLen = s.length(), res = 0;
        if(s == null || sLen == 0) return 0;
        char[] sChar = s.toCharArray();
        boolean[][] dp = new boolean[sLen][sLen];
        for(int k = 0; k < sLen; k++){
            for(int i = 0; i <= k; i++){
                if(i == k){
                    dp[i][k] = true;
                    res++;
//                    这里填表有两个要点：
//                    1. 无后效性，所以选择从左往右，从上到下的顺序
//                    2. 就是当 k - i ==1的时候，这时候表有可能是在另外没填的半边，所以两种解决办法，
//                       （1）一种是前面数据处理的时候先把表填了，
//                       （2）另一种就是直接作为一种判断条件
                }else if(sChar[i] == sChar[k] && (dp[i + 1][k - 1] || k - i == 1)){
//                    System.out.println(i);
//                    System.out.println(k);
                    dp[i][k] = true;
                    res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        答案为6
        System.out.println(new lc647().countSubstrings("aaa"));
//        System.out.println(new lc647().countSubstrings("abaa"));
    }
}
