package amazon;

/*
题解的这个方法很妙，其他方法都比较粗糙，不太好：
https://leetcode-cn.com/problems/longest-word-in-dictionary/solution/java-hashset-pai-xu-by-chrischeng021/

这道题目有以下注意点：
1. 对String数组进行sort排序的时候，经过查询文档，如果length不一样，按照升序排列
   如果length按照字典顺序排列
2. 对于compareTo的比较和第一点说到的sort的原则一致,
   例如s1.compareTo(s2)，如果得到结果大于0，则说明s2在字典序中在s1的前面；
   反之如果结果小于0，则说明s1在字典序中在s2的前面
* */

import java.util.Arrays;
import java.util.HashSet;

public class lc720 {
    public String longestWord(String[] words) {
        int wLen = words.length;
        Arrays.sort(words);
        HashSet<String> hashSet = new HashSet<>();
        for(String word : words){
            int len = word.length();
            if(len == 1) hashSet.add(word);
            else if(hashSet.contains(word.substring(0, len - 1))) hashSet.add(word);
        }
        int count = 0;
        String res = "";
        for(String str : hashSet){
            int sLen = str.length();
            if(sLen > count){
                count = sLen;
                res = str;
            }else if(sLen == count){
                res = res.compareTo(str) > 0 ? str : res;
            }
        }
        return res;
    }
}
