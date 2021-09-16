package amazon;

/*
有两种方法，一种是直接用sort，另一种是用char来做对应，一刷用的是第二种方法。两种方法时间效率差不多

这道题目有以下注意点：
1. 在把char[]转为hashkey的时候，要用valueof，或者干脆new String，不能用toString
2. 这道题目最后的结果返回比较特殊，是直接把hashMap里面的values返回的，这个方法要学会
* */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class lc49 {
    private HashMap<String, List<String>> hashMap = new HashMap<>();
    private List<List<String>> res = new LinkedList<>();
    public List<List<String>> groupAnagrams(String[] strs) {
        int sLen = strs.length;
        if(sLen == 0 || strs == null) return res;
        for(int i = 0; i < sLen; i++){
            int sl = strs[i].length();
            char[] sChar = strs[i].toCharArray();
//            这里不能用int[]，而是用char[]
            char[] key = new char[26];
            for(int k = 0; k < sl; k++){
                key[sChar[k] - 'a']++;
            }
//            注意这里不能用toString，否则是地址，不是String
            String hashKey = String.valueOf(key);

            if(!hashMap.containsKey(hashKey)) {
                hashMap.put(hashKey, hashMap.getOrDefault(hashKey, new LinkedList<>()));
            }
            hashMap.get(hashKey).add(strs[i]);
        }
        return new ArrayList<>(hashMap.values());
    }

    public static void main(String[] args) {
        String[] test = {"eat","tea","tan"};
        System.out.println(new lc49().groupAnagrams(test));
    }

}
