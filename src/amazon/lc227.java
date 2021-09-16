package amazon;

/*
注意看题目的提示,尤其是第四条：
1. 1 <= s.length <= 3 * 105
2. s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
3. s 表示一个 有效表达式
4. 表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
5. 题目数据保证答案是一个 32-bit 整数

lc227和lc224难度差不多，可以一起做
二刷的时候可以试一下双栈，其实双栈的题目对于224/227来说没什么太大的优化
但是如果考虑自定义或者有其他优先级的表达式计算的时候会有较大优势
* */

import java.util.ArrayDeque;
import java.util.Deque;

public class lc227 {
    public int calculate(String s) {
        char[] sChar = s.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();
        int curRes = 0, flag = 1, preNum = 1;
        boolean mul = false, divd = false;
        for(int i = 0; i < sChar.length; i++){
            if(sChar[i] == ' ') continue;
            else if(sChar[i] >= '0' && sChar[i] <= '9'){
                curRes = curRes * 10 + (sChar[i] - '0');
                continue;
            }
            if(mul){
                stack.addFirst(preNum * curRes * flag);
                mul = false;
            }else if(divd){
                stack.addFirst(preNum / curRes * flag);
                divd = false;
            }else {
                stack.addFirst(curRes * flag);
            }
            flag = 1;
            preNum = 1;
            curRes = 0;
            if(sChar[i] == '+') flag = 1;
            if(sChar[i] == '-') flag = -1;
            if(sChar[i] == '*'){
                mul = true;
                preNum = stack.pollFirst();
            }
            if(sChar[i] == '/'){
                divd = true;
                preNum = stack.pollFirst();
            }
        }
        if(mul) stack.addFirst(preNum * curRes * flag);
        else if(divd) stack.addFirst(preNum / curRes * flag);
        else stack.addFirst(curRes * flag);
        int res = 0;
        for (int num : stack){
            res += num;
        }
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(new lc227().calculate("3+2*2"));
        System.out.println(new lc227().calculate("4/3+2"));
    }
}
