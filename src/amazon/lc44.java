package amazon;

/*
这道题目可以很明显看出来是一个dp的题目
但是这里有一个题目的细节需要确定，那就是给的两个String s/p
其中只有p是带*或者?的，而s是不带的，这个是一个很重要的点，如果面试的时候一定要问面试官

我的思路和下面这个题解比较像，也就是说遇到*的时候我的想法是把整个一行都变为T
但是写完以后感觉这么写确实不太方便，python能写完全是因为他有些方法很方便
但是如果用java写的话，没有那种标准解法通用，而且debug很久才能找到问题
所以完全不推荐，除非有两个小时的空闲时间，但是可以在做完了标准做法以后看下坑在哪里
https://leetcode-cn.com/problems/wildcard-matching/solution/yi-ge-qi-pan-kan-dong-dong-tai-gui-hua-dpsi-lu-by-/

但是如果是常规解法的话，遇到*就不是这么做了，但是其他都是一样的,其实有一道题目和这个极度类似
而且是liweiwei的题解，这里在解释一下状态转移方程：
（dp[i - 1][j] || dp[i][j - 1]，尤其是dp[i - 1][j]），
我的理解是：现在面对*，它可以表示空字符，也就是说直接把*去掉，看没有*是否能匹配，即保持i不动，j-1；
另一种情况是*表示多个字符，我们得把*留着，所以j不动，然后看当前字符之前的字符是否也能被*匹配，
如果之前字符能，那么再加上现在的一个字符，*就能匹配这几个连续字符了
https://leetcode-cn.com/problems/wildcard-matching/solution/44ti-tong-pei-fu-pi-pei-by-iceblood/
* */

public class lc44 {
//    严重不推荐，代码复杂而且坑巨多，用java写很麻烦
//    public boolean isMatch(String s, String p) {
////        已经考虑了s/p可能为空的情况了，这个方法自动都考虑了，所以不用写corner case了
//        int sLen = s.length(), pLen = p.length();
//        char[] sChar = s.toCharArray(), pChar = p.toCharArray();
////        创建dp+初始化,同时要注意dp[1]对应s/p的index为0
//        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
//        dp[0][0] = true;
//        for(int i = 1; i <= pLen; i++){
////            这个是为了检测p有几个*作为开头，而且不能检测?，因为?只能抵消char而不能抵消空字符串
//            if(pChar[i - 1] == '*') {
////                注意这里k要从0开始，这个debug很久
//                for(int k = 0; k <= sLen; k++){
//                    dp[k][i] = true;
//                }
//            }
//            else break;
//        }
//
////        开始填表，因为用的自己想的办法，所以代码会比常规方法复杂一点
////        而且注意这里是要pLen在前，sLen在后，因为要用一个boolean进行后续操作的
////        但是根据填表来看是要对p的那一列进行操作，如果用常规的先s再p会达不到效果
//        for (int k = 1; k <= pLen; k++) {
////            这个方法的核心
//            boolean col = false;
//            for (int i = 1; i <= sLen; i++) {
//                if (pChar[k - 1] == '*') {
//                    if(dp[i][k - 1]) col = true;
//                    if(col) dp[i][k] = true;
//                } else if (pChar[k - 1] == '?' || sChar[i - 1] == pChar[k - 1]) {
//                    dp[i][k] = dp[i - 1][k - 1];
//                }
//            }
//        }
//        return dp[sLen][pLen];
//    }


//    方法二，模板方法，就用这个写，以后都不要再去想方法一，太麻烦！
    public boolean isMatch(String s, String p) {
//        已经考虑了s/p可能为空的情况了，这个方法自动都考虑了，所以不用写corner case了
        int sLen = s.length(), pLen = p.length();
        char[] sChar = s.toCharArray(), pChar = p.toCharArray();
//        创建dp+初始化,同时要注意dp[1]对应s/p的index为0
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true;
        for(int i = 1; i <= pLen; i++){
            if(pChar[i - 1] == '*') {
                dp[0][i] = true;
            }
            else break;
        }

        for(int i = 1; i <= sLen; i++){
            for(int k = 1; k <= pLen; k++){
                if (pChar[k - 1] == '*') {
                    dp[i][k] = dp[i - 1][k] || dp[i][k - 1];
                } else if (pChar[k - 1] == '?' || sChar[i - 1] == pChar[k - 1]) {
                    dp[i][k] = dp[i - 1][k - 1];
                }
            }
        }
        return dp[sLen][pLen];
    }


    public static void main(String[] args) {
//        答案为true
        System.out.println(new lc44().isMatch("aa", "*"));
//        答案为true
        System.out.println(new lc44().isMatch("adceb", "*a*b"));
//        答案为true
        System.out.println(new lc44().isMatch("adcbe", "a*b?"));
    }
}
