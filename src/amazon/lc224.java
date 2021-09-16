package amazon;

/*
这题思路其实挺容易想，主要就是实现方法，下面这个题解写的挺清楚：
https://leetcode-cn.com/problems/basic-calculator/solution/ji-ben-ji-suan-qi-zhan-fu-he-zhan-by-boo-p0en/

lc227和lc224难度差不多，可以一起做
二刷的时候可以试一下双栈，其实双栈的题目对于224/227来说没什么太大的优化
但是如果考虑自定义或者有其他优先级的表达式计算的时候会有较大优势
* */

import java.util.ArrayDeque;
import java.util.Deque;

public class lc224 {
    public int calculate(String s) {
//        要注意这个栈里面只包含'+', '-'两个运算符的信息，所以‘+’用1代表，‘-’用-1代表
//        然后这里会涉及一个怎么用()来让元素出栈的问题，
//        其实这个原理就是遇到'('的时候，让flag为入栈，然后这个在遇到'）'前的所有数字都用这一个入栈的flag来表示
//        那么遇到')'的时候，就是把这个flag给Pop掉的时候，因为一个flag其实就代表一个括号对
//        所以这个时候可能会发现用双栈似乎会更清晰，这个就留给二刷了
        Deque<Integer> stack = new ArrayDeque<>();
        char[] sChar = s.toCharArray();
        int flag = 1, curRes = 0, res = 0;
        stack.addFirst(flag);
        for(int i = 0; i <sChar.length; i++){
            if(sChar[i] == ' ') continue;
            else if(sChar[i] >= '0'){
                curRes = 10 * curRes + (sChar[i] - '0');
                continue;
            }
            res += flag * curRes;
            curRes = 0;
            if(sChar[i] == '+') flag = stack.peekFirst();
            else if(sChar[i] == '-') flag = stack.peekFirst() * -1;
            else if(sChar[i] == '(') stack.addFirst(flag);
            else if(sChar[i] == ')') stack.pollFirst();
        }
        res += flag * curRes;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new lc224().calculate("2147483647"));
    }
}
