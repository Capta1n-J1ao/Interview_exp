package amazon;

/*
代码写完测试最后那两个case，就能看出问题

为了和之前滑动窗口模板契合，所以二刷以后借鉴了以下模板，出处是labuladong，而且和liweiwei的思想相同
https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/hua-dong-chuang-kou-jie-fa-da-bai-hua-ji-drex/

滑动窗口系列题目：
3/76/209/159/424/992， 其中76/992最难留到最后做
* */

import java.util.HashMap;
import java.util.HashSet;

public class lc3 {
//    private HashMap<Character, Integer> hashMap = new HashMap<>();
//    public int lengthOfLongestSubstring(String s) {
//        if(s.length() == 0 || s == null) return 0;
//        int res = 1;
//        char[] sChar = s.toCharArray();
//        int start = 0;
//        for(int i = 0; i < sChar.length; i++){
//            if(hashMap.containsKey(sChar[i])){
//                start = Math.max(start, hashMap.get(sChar[i]) + 1);
//            }
//            hashMap.put(sChar[i], i);
//            res = Math.max(res, i - start + 1);
//        }
//        return res;
//    }


//    2021/3/19 二刷
    private HashMap<Character, Integer> hashMap;
    public int lengthOfLongestSubstring(String s) {
        hashMap = new HashMap<>();
        int sLen = s.length(), left = 0, right = 0, res =0, curRes = 0;
        if(sLen == 0 || s == null) return 0;
        char[] sChar = s.toCharArray();
        while (right < sLen){
            hashMap.put(sChar[right], hashMap.getOrDefault(sChar[right], 0) + 1);
            right++;
            while (hashMap.get(sChar[right - 1]) > 1){
                int temp = hashMap.get(sChar[left]);
                hashMap.put(sChar[left], temp - 1);
                left++;
            }
            curRes = right - left;
            res = Math.max(res, curRes);
        }
        return res;
    }
    public static void main(String[] args) {
//        System.out.println(new lc3().lengthOfLongestSubstring("pwwkew"));
//        System.out.println(new lc3().lengthOfLongestSubstring("bbbbb"));
//        System.out.println(new lc3().lengthOfLongestSubstring("abcabcbb"));
//        System.out.println(new lc3().lengthOfLongestSubstring("au"));

//        下面这两个例子最能够测试出代码是否可行
        System.out.println(new lc3().lengthOfLongestSubstring("dvdf"));
        System.out.println(new lc3().lengthOfLongestSubstring("abba"));
    }
}
