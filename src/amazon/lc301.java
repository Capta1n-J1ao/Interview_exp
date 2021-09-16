package amazon;

/*
常规liweiwei题解,其实感觉下面“CHerrY”的评论更加好懂一些：
https://leetcode-cn.com/problems/remove-invalid-parentheses/solution/shan-chu-wu-xiao-de-gua-hao-by-leetcode/
* */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class lc301 {
    private HashSet<String> hashSet = new HashSet<>();
    private char[] sChar;
    private int sLen, rightRedundant = 0, leftRedundant = 0;
    public List<String> removeInvalidParentheses(String s) {
        sLen = s.length();
        sChar = s.toCharArray();
        for(char ch : sChar){
            if(ch == '(') leftRedundant++;
            else if(ch == ')'){
                if(leftRedundant > 0) leftRedundant--;
                else rightRedundant++;
            }
        }
//        对于BackTracking的参数要想清楚，要考虑到比如下面testcase的情况，会不会答案有())(，这种是一定要写一下才知道的错误
        BackTracking(0, 0, 0, leftRedundant,rightRedundant, new StringBuilder());
        return new ArrayList<>(hashSet);
    }

    private void BackTracking(int index, int leftCount, int rightCount, int leftToRemove, int rightToRemove, StringBuilder curRes){
        if(index == sLen){
            if(leftToRemove == 0 && rightToRemove == 0) hashSet.add(new String(curRes));
            return;
        }
        char curCh = sChar[index];
//        下面两个可能是删除curCh的情况
        if(curCh == '(' && leftToRemove > 0){
            BackTracking(index + 1, leftCount, rightCount, leftToRemove - 1,rightToRemove, curRes);
        }else if(curCh == ')' && rightToRemove > 0){
            BackTracking(index + 1, leftCount, rightCount, leftToRemove, rightToRemove - 1, curRes);
        }
//        下面是保留curCh的情况，这时候curCh有三种可能，左右括号或者字符串
        curRes.append(curCh);
//        注意这里如果碰到'('可以直接加上去的，只有碰到'('才需要判断左括号是不是比右括号多，下面两种写法都是可以的
//        其实包括上面如果加很多筛选条件只要合理即可，主要就是最后一个else if一定要写对
//        if(curCh == '(' && leftCount >= rightCount){
        if(curCh == '('){
            BackTracking(index + 1, leftCount + 1, rightCount, leftToRemove, rightToRemove, curRes);
        }else if(curCh == ')' && leftCount > rightCount){
            BackTracking(index + 1, leftCount, rightCount + 1, leftToRemove, rightToRemove, curRes);
        }else if(curCh != '(' && curCh != ')'){
//            针对字符的情况，而且这里的else一定要加判断条件，否则上面判断没过的全会漏下来
            BackTracking(index + 1, leftCount, rightCount, leftToRemove, rightToRemove, curRes);
        }
        curRes.deleteCharAt(curRes.length() - 1);
    }

    public static void main(String[] args) {
        System.out.println(new lc301().removeInvalidParentheses("())()"));
    }
}
