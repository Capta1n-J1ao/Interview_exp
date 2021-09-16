package amazon;

/*
主要看下面的tricky case怎么解决
首先可以看下面这个题解，和我一开始的想法一模一样，一步步优化到答案，其中题解里面这句话很关键：
总结：两种索引会入栈
    1. 等待被匹配的左括号索引。
    2. 充当「参照物」的右括号索引。因为：当左括号匹配光时，栈需要留一个垫底的参照物，用于计算一段连续的有效长度。

https://leetcode-cn.com/problems/longest-valid-parentheses/solution/shou-hua-tu-jie-zhan-de-xiang-xi-si-lu-by-hyj8/

然后再看下官方题解的视频中的方法四，很巧妙，可以开拓一下思路，有空的时候看，前提是栈的方法一定要做出来：
https://leetcode-cn.com/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode-solution/
* */

import java.util.ArrayDeque;
import java.util.Deque;

public class lc32 {
    public int longestValidParentheses(String s) {
        int sLen = s.length(), res = 0, curRes = 0;
        if(s == null || sLen < 2) return 0;
        char[] sChar = s.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(-1);
        for(int i = 0; i < sLen; i++){
            if(sChar[i] == '(') stack.addFirst(i);
            else {
//                这句话很精髓，其实只要看到')'就必须出栈，后面是不是再入栈就要看stack是否为空
                stack.pollFirst();
//                如果空了的话，那就先出栈，然后再入栈
                if(stack.isEmpty()) stack.addFirst(i);
                else {
                    curRes = i - stack.peekFirst();
                    res = Math.max(res, curRes);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        这个例子有一个很tricky的地方，也就是说在i = 0的时候，
//        怎么处理0和stack里面初始存在的-1的关系，这个很关键
        System.out.println(new lc32().longestValidParentheses(")()())"));
//        System.out.println(new lc32().longestValidParentheses(""));

//        下面case开始就是一些tricky case了
        System.out.println(new lc32().longestValidParentheses("()(()"));
        System.out.println(new lc32().longestValidParentheses("(()(()"));
    }
}
