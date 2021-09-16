package amazon;

/*
lc895

这道题目最大的难点就是：
在具有相同的（最大）频率的元素中，怎么判断那个元素是最新的？
我们可以使用栈来查询这一信息：靠近栈顶的元素总是相对更新一些。

还有就是复习hashmap的三个常规操作，三个都非常重要，要记住！
1. getOrDefault
2. computeIfAbsent
3. 对于题目里面的sameFreqNum,为了看里面的Stack是否有元素，应该用size方法，而不是直接null,否则会不识别！

官方题解即可：
https://leetcode-cn.com/problems/maximum-frequency-stack/solution/zui-da-pin-lu-zhan-by-leetcode/
* */

import java.util.*;

public class FreqStack {
    HashMap<Integer, Integer> freq;
    HashMap<Integer, Stack<Integer>> sameFreqNum;
    int maxFreq;
    public FreqStack() {
        freq = new HashMap<>();
        sameFreqNum = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int x) {
        int curFreq = freq.getOrDefault(x, 0) + 1;
        maxFreq = Math.max(maxFreq, curFreq);
        freq.put(x, curFreq);
        sameFreqNum.computeIfAbsent(curFreq, v -> new Stack<>()).push(x);
    }

    public int pop() {
        Stack<Integer> curSameFreq = sameFreqNum.get(maxFreq);
        int res = curSameFreq.pop();
        freq.put(res, freq.get(res) - 1);
//        这么写就是错的，不能这么写！
//        if(sameFreqNum.get(maxFreq) == null) maxFreq--;
        if(sameFreqNum.get(maxFreq).size() == 0) maxFreq--;
        return res;
    }

    public static void main(String[] args) {
        FreqStack test = new FreqStack();
        test.push(5);
        test.push(7);
        test.push(5);
        test.push(7);
        test.push(4);
        test.push(5);
        test.push(1);
        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.pop());
    }
}


/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 */
