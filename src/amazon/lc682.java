package amazon;

/*
整数 x - 表示本回合新获得分数 x
"+" - 表示本回合新获得的得分是前两次得分的总和。题目数据保证记录此操作时前面总是存在两个有效的分数。
"D" - 表示本回合新获得的得分是前一次得分的两倍。题目数据保证记录此操作时前面总是存在一个有效的分数。
"C" - 表示前一次得分无效，将其从记录中移除。题目数据保证记录此操作时前面总是存在一个有效的分数。

* */

import java.util.ArrayDeque;
import java.util.Deque;

public class lc682 {
    public int calPoints(String[] ops) {
        Deque<Integer> stack = new ArrayDeque<>();
        for(String str : ops){
            if(str.equals("+")){
                int pollTemp = stack.pollFirst();
                int topTemp = pollTemp + stack.peek();
                stack.addFirst(pollTemp);
                stack.addFirst(topTemp);
            }else if(str.equals("D")){
                int doubleTemp = stack.peekFirst();
                stack.addFirst(doubleTemp * 2);
            }else if(str.equals("C")) stack.pollFirst();
            else {
                int inputTemp = Integer.valueOf(str);
                stack.addFirst(inputTemp);
            }
        }
        int res = 0;
        for(int num : stack){
            res += num;
        }
        return res;
    }
}
