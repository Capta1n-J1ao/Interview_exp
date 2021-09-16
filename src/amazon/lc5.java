package amazon;

/*
类似题目 5/516/647，可以一起服用
* */

public class lc5 {
//    public String longestPalindrome(String s) {
//        int sLen = s.length();
//        boolean[][] dp = new boolean[sLen][sLen];
//        for(int i = 0; i < sLen; i++){
//            dp[i][i] = true;
//        }
//        int count = 0, begin = 0, end = 0;
//        StringBuilder res = new StringBuilder();
//        for(int k = 0; k < sLen; k++){
//            for(int i = 0; i <= k; i++){
////                ((k - i) < 2 || dp[i + 1][k - 1])这个判断很重要！
//                if(s.charAt(i) == s.charAt(k) && ((k - i) < 2 || dp[i + 1][k - 1])){
//                    dp[i][k] = true;
//                    if(k - i + 1 > count){
//                        count = k - i + 1;
//                        begin = i;
//                        end = k + 1;
//                    }
//                }
//            }
//        }
//        return s.substring(begin, end);
//    }

//    二刷，2021/4/4
    public String longestPalindrome(String s) {
        char[] sChar = s.toCharArray();
        int sLen = sChar.length, start = 0, end = 0, maxLen = 0;
        boolean[][] dp = new boolean[sLen][sLen];
        for(int i = 0; i < sLen; i++) dp[i][i] = true;
        for(int i = sLen - 1; i >= 0; i--){
            for(int k = i + 1; k < sLen; k++){
                int curLen = k - i + 1;
                if(sChar[i] == sChar[k]){
                    if(curLen <= 2){
                        dp[i][k] = true;
                    }else {
                        dp[i][k] = dp[i + 1][k - 1];
                    }
                    if (dp[i][k] && curLen > maxLen) {
                        maxLen = curLen;
                        start = i;
                        end = k;
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }

    public static void main(String[] args) {
//        答案：aba
        System.out.println(new lc5().longestPalindrome("cabad"));
//        答案：aca
        System.out.println(new lc5().longestPalindrome("aacabdkacaa"));
//        答案：bb
        System.out.println(new lc5().longestPalindrome("cbbd"));
    }
}
