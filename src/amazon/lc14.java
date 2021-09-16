package amazon;

/*
注意这道题目说的是前缀，是prefix，也就是说固定是从第一个char开始算的
题解的话这个题解下面道长的评论和我的思路一模一样：
https://leetcode-cn.com/problems/longest-common-prefix/solution/hua-jie-suan-fa-14-zui-chang-gong-gong-qian-zhui-b/
* */

public class lc14 {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        int sLen = strs.length, minLen = Integer.MAX_VALUE;
        String minLenStr = strs[0];
        for(int i = 0; i < sLen; i++){
            int curLen = strs[i].length();
            if (curLen < minLen){
                minLen = curLen;
                minLenStr = strs[i];
            }
        }

        for(int i = 0; i < sLen; i++){
            int index = 0;
            while (index < minLen && index < minLenStr.length()){
                if(strs[i].charAt(index) != minLenStr.charAt(index)) break;
                index++;
            }
            minLenStr = minLenStr.substring(0, index);
            if(minLenStr.equals("")) return minLenStr;
        }
        return minLenStr;
    }

    public static void main(String[] args) {
//        String[] test = {"flower", "flow", "flight"};
//        System.out.println(new lc14().longestCommonPrefix(test));

        String[] test = {"flower", "fkow"};
        System.out.println(new lc14().longestCommonPrefix(test));
    }
}
