package amazon;

/*
这道题目如果追求速度的话可以不用hashmap改用int[]就是'a'-'0'来得到数字
这样更快，但是时间复杂度其实和HashMap是一样的
* */

import java.util.HashMap;

public class lc409 {
    public int longestPalindrome(String s) {
//        corner case
        if(s == null ||s.length() == 0) return 0;
        int sLen = s.length(), res = 0;
        char[] sChar = s.toCharArray();
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for(int i = 0; i < sLen; i++){
            char curCh = sChar[i];
            hashMap.put(curCh,hashMap.getOrDefault(curCh, 0) + 1);
        }
        for(char ch : hashMap.keySet()){
            int count = hashMap.get(ch);
            res += (count / 2) * 2;
        }
        return res < sLen ? res + 1 : res;
    }

    public static void main(String[] args) {
        System.out.println(new lc409().longestPalindrome("abccccdd"));
    }
}
