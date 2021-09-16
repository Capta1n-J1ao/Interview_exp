package amazon;

/*
直接AC了，如果有兴趣可以看下之前的写法，和现在的有些区别
效率的话之前那个会好一点一个11mS,一个14mS，但是时间复杂度应该是一样的
* */

import java.util.LinkedList;
import java.util.List;

public class lc131 {
    private List<List<String>> res = new LinkedList<>();
    private int sLen;
    private char[] sChar;
    public List<List<String>> partition(String s) {
        sLen = s.length();
        sChar = s.toCharArray();
        BackTracking(0, new LinkedList<>());
        return res;
    }

    private void BackTracking(int index, List<String> curRes){
        if(index == sLen){
            res.add(new LinkedList<>(curRes));
            return;
        }
        for(int i = index + 1; i <= sLen; i++){
            if(!isPalindrome(index, i - 1)) continue;
            curRes.add(String.valueOf(sChar, index, i - index));
//                System.out.println(curRes);
            BackTracking(i, curRes);
            curRes.remove(curRes.size() - 1);

        }
    }

    private boolean isPalindrome(int start, int end){
        while (start < end){
            if(sChar[start] == sChar[end]){
                start++;
                end--;
            }else return false;
        }
        return true;
    }

    public static void main(String[] args) {
//        String test = "aab";
//        List<List<String>> testRes = new lc131().partition(test);
//        System.out.println(testRes);
//        System.out.println(test.substring(0, 1));

        String test = "efe";
        List<List<String>> testRes = new lc131().partition(test);
        System.out.println(testRes);
    }
}
