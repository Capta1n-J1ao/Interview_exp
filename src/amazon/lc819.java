package amazon;

/*
和我思路接近的题解：
https://leetcode-cn.com/problems/most-common-word/solution/java-hashmap-hashset-by-coolking/

本题两个注意点：
1. 要掌握.toLowerCase()这个方法，很方便
2. 下面三个testcase是corner case，要通过
* */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class lc819 {
    private HashSet<String> banList = new HashSet<>();
    private HashMap<String, Integer> freq = new HashMap<>();
    public String mostCommonWord(String paragraph, String[] banned) {
        paragraph += ".";
        for (String ban : banned){
//            关键要全转为小写
            banList.add(ban.toLowerCase());
        }
        int sLen = paragraph.length();
        int index = 0;
        for(int i = 0; i < sLen; i++){
            if(isChar(paragraph.charAt(i))) continue;
            else {
                String temp = paragraph.substring(index, i);
                if(temp.equals("")) {
                    index++;
                    continue;
                }
                if(!banList.contains(temp.toLowerCase())){
                    freq.put(temp.toLowerCase(), freq.getOrDefault(temp.toLowerCase(), 0) + 1);
                }
            }
            index = i + 1;
        }
        int maxCount = Integer.MIN_VALUE;
        String res = new String();
        for(String word : freq.keySet()){
            maxCount = Math.max(maxCount, freq.get(word));
            if(maxCount == freq.get(word)){
                res = word;
            }
        }
        return res;
    }

    private boolean isChar(char ch){
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }

    public static void main(String[] args) {
//        String para = "Bob hit a ball, the hit BALL flew far after it was hit.";
//        String[] banList = {"hit"};
//        System.out.println(new lc819().mostCommonWord(para, banList));
        String para = "Bob";
        String[] banList = {""};
        System.out.println(new lc819().mostCommonWord(para, banList));
//        String para = "Bob. hit, ball";
//        String[] banList = {"bob","hit"};
//        System.out.println(new lc819().mostCommonWord(para, banList));
    }
}
