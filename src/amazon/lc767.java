package amazon;

/*
这道题目主要在于思想，第一次做的时候基本想的差不多，最tricky的地方就在于test case

题解：
https://leetcode-cn.com/problems/reorganize-string/solution/javadai-ma-ji-bai-liao-100de-yong-hu-by-sdwwld/

整个题目最难得地方我感觉就是对于index的判断
if(index >= sLen) index = 1;
* */

public class lc767 {
    public String reorganizeString(String S) {
        int sLen = S.length(), maxCount = 0;
        int[] count = new int[26];
        char[] sChar = S.toCharArray();
        char maxCountChar = 'a';
        for(int i = 0; i < sLen; i++){
            count[sChar[i] - 'a']++;
            if(count[sChar[i] - 'a'] > maxCount){
                maxCount = count[sChar[i] - 'a'];
                if(maxCount > (sLen + 1) / 2) return "";
                maxCountChar = sChar[i];
            }
        }

        char[] res = new char[sLen];
        int index = 0;
        while (count[maxCountChar - 'a'] != 0){
            count[maxCountChar - 'a']--;
            res[index] = maxCountChar;
            index += 2;
        }

        for(int i = 0; i < count.length; i++){
            while (count[i] > 0){
                count[i]--;
//                这句话很重要，其实不是在数目最多的那个数分配完以后index就变到最前面，
//                而是把index放到能放到的最后一个数的时候再转index，很关键
                if(index >= sLen) index = 1;
                res[index] = (char) ('a' + i);
                index += 2;
            }
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        System.out.println(new lc767().reorganizeString("aaaaabbbbcc"));
    }
}
