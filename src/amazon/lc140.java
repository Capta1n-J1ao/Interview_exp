package amazon;

/*
139/140/472这三道题目有相似点，做这道题目之前可以先做139/140，然后再做这个

如果是从lc139做过来的话，liweiwei的题解是最好懂的：
https://leetcode-cn.com/problems/word-break-ii/solution/dong-tai-gui-hua-hui-su-qiu-jie-ju-ti-zhi-python-d/
* */

import java.util.*;

class lc140{
    private HashSet<String> hashSet;
    private String s;
    private int sLen;
    private boolean[] dp;
    private List<String> res = new LinkedList<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        this.s = s;
        hashSet = new HashSet<>(wordDict);
        sLen = s.length();
        sLen = s.length();
        dp = new boolean[sLen + 1];
        dp[0] = true;
        for(int i = 1; i <= sLen; i++){
            for(int k = 0; k < i; k++){
                if(dp[k] && hashSet.contains(s.substring(k, i))) dp[i] = true;
            }
        }


        if(dp[sLen]){
//            index不用0而用sLen也是基于效率方面的考量，可以看下liweiwei题解下面的评论，有个人从0开始，效率会低
//            也正是因为从最后开始，所以要用addFirst这个方法，而不能单纯addFirst，支持这个命令有两个结构
//            1. LinkedList 而且必须是直接用 LinkedList作为初始化，用List<String>不行
//            2. Deque
            BackTracking(new LinkedList<String>(), sLen);
        }
        return res;
    }

    private void BackTracking(LinkedList<String> curRes, int index){
        if(index == 0){
            res.add(String.join(" ", curRes));
            return;
        }
        for(int i = index - 1; i >= 0; i--){
            String temp = s.substring(i, index);
            if(dp[i] && hashSet.contains(temp)){
                curRes.addFirst(temp);
                BackTracking(curRes, i);
                curRes.removeFirst();
            }
        }
    }


////    方法二：这人的题解已经算写的最清楚得了
////    https://leetcode-cn.com/problems/word-break-ii/solution/dfs-jian-zhi-by-dubulingbo/
//class lc140 {
//    private HashSet<String> words;
//    //    private String mainStr;
//    private List<String> res;
//    List<String> sentence = new ArrayList<>();
//    // canBreak[i] 从 i 开始后的字符串不能匹配到 words 中的单词
//    private boolean[] cannotBreak;
//
//    public List<String> wordBreak(String s, List<String> wordDict) {
//        // DFS + 剪枝
//        words = new HashSet<>(wordDict);
////        mainStr = s;
//        res = new ArrayList<>();
//        cannotBreak = new boolean[s.length()];
////        canBreak[0] = true;
//        // 从第一个字符开始
//        dfs(0, s);
//        return res;
//    }
//
//    /**
//     * 深度优先搜索
//     *
//     * @param start 字符开始位置
//     */
//    private void dfs(int start, String mainStr) {
//        int curResSize = res.size(); // 记录当前的结果列表的容量。如果此次匹配成功，size应该会 +1；
//        if (start == mainStr.length()) {
//            // 说明已经找到了一种可能的组合
//            res.add(String.join(" ", sentence));
//            return;
//        }
//
//        if(!cannotBreak[start]){
//            for (int i = start + 1; i <= mainStr.length(); i++) {
//                String s = mainStr.substring(start, i);
//                if (words.contains(s)) {
//                    // 当前字符串处于 字典中
//                    sentence.add(s);
//                    // 从下一个 字符开始搜索
//                    dfs(i, mainStr);
//                    // 移除上一个字符串
//                    sentence.remove(sentence.size() - 1);
//                }
//            }
//        }
//        cannotBreak[start] = res.size() == curResSize;
//    }

    public static void main(String[] args) {
        String sTest = "catsanddog";
        List<String> lTest = new LinkedList<>();
        lTest.add("cat");
        lTest.add("cats");
        lTest.add("and");
        lTest.add("dog");
        System.out.println(new lc140().wordBreak(sTest, lTest));
    }
}

