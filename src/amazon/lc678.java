package amazon;

/*
这个题解最容易理解，也最直观
https://leetcode-cn.com/problems/valid-parenthesis-string/solution/you-xiao-de-gua-hao-zi-fu-chuan-xian-jian-ce-you-g/
* */

import java.util.ArrayDeque;
import java.util.Deque;

public class lc678 {
    public boolean checkValidString(String s) {
        char[] sChar = s.toCharArray();
        int sLen = sChar.length;
        Deque<Integer> leftStack = new ArrayDeque<>();
        Deque<Integer> starStack = new ArrayDeque<>();
        for(int i = 0; i < sLen; i++){
            if(sChar[i] == '(') leftStack.addFirst(i);
            else if(sChar[i] == '*') starStack.addFirst(i);
            else if(sChar[i] == ')'){
                if(leftStack.isEmpty()){
                    if(starStack.isEmpty()) return false;
                    starStack.pollFirst();
                }else leftStack.pollFirst();
            }
        }
        if(leftStack.size() > starStack.size()) return false;
        while (!leftStack.isEmpty()){
            if(leftStack.pollFirst() > starStack.pollFirst()) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new lc678().checkValidString("(())(*()())(*"));
    }
}
