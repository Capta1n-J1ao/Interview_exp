package amazon;

/*
139/140/472这三道题目有相似点，做这道题目之前可以先做139/140，然后再做这个


这道题目不要犹豫，就按照直觉的想法做。做完以后其实有一个剪枝优化，别忘了试试。
非优化版本一定要写，优化版本不写也不要紧，看一下就懂了

感觉之前二刷的for循环不是很容易懂，这次写的这个是完全独立写的，应该更好懂
* */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class lc139 {
//    private HashSet<String> hashSet;
//    public boolean wordBreak(String s, List<String> wordDict) {
//        hashSet = new HashSet<>(wordDict);
//        int sLen = s.length();
//        boolean[] dp = new boolean[sLen + 1];
//        dp[0] = true;
////        优化版本代码
//        int longestLen = -1;
//        for(String str : hashSet){
//            longestLen = Math.max(longestLen, str.length());
//        }
//
//
//        for(int i = 1; i <= sLen; i++){
//            for(int k = 0; k < i; k++){
//                if(i - k> longestLen) continue;
//                if(dp[k] && hashSet.contains(s.substring(k, i))) dp[i] = true;
//            }
//        }
//        return dp[sLen];
//    }

//    二刷：2021/2/16,但是没用优化版本
    private HashSet<String> hashSet;
    private boolean[] dp;
    private String s;
    private int sLen;
    public boolean wordBreak(String s, List<String> wordDict) {
        this.s = s;
        hashSet = new HashSet<>(wordDict);
        sLen = s.length();
        dp = new boolean[sLen + 1];
        dp[0] = true;
        for(int i = 1; i <= sLen; i++){
            for(int k = 0; k < i; k++){
                if(dp[k] && hashSet.contains(s.substring(k, i))) dp[i] = true;
            }
        }
        return dp[sLen];
    }


    public static void main(String[] args) {
//        String test = "leetcode";
//        List<String> testList = new LinkedList<>();
//        testList.add("leet");
//        testList.add("code");
//        System.out.println(new lc139().wordBreak(test, testList));

        String test = "aaaaaaa";
        List<String> testList = new LinkedList<>();
        testList.add("aaaa");
        testList.add("aaa");
        System.out.println(new lc139().wordBreak(test, testList));
    }
}
