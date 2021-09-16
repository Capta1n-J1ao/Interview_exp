package amazon;

import java.util.ArrayDeque;
import java.util.Deque;

public class lc150 {
    public int evalRPN(String[] tokens) {
        int tLen = tokens.length, res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for(String token : tokens){
            if(isNumber(token)) stack.addFirst(Integer.valueOf(token));
            else {
//                注意这个细节！num1和num2的顺序问题
                int num2 = stack.pollFirst();
                int num1 = stack.pollFirst();
                if(token.equals("+")){
                    stack.addFirst(num1 + num2);
                }else if(token.equals("-")){
                    stack.addFirst(num1 - num2);
                }else if(token.equals("*")){
                    stack.addFirst(num1 * num2);
                }else if(token.equals("/")){
                    stack.addFirst(num1 / num2);
                }
            }
        }
        return stack.pollFirst();
    }

    private boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }

    public static void main(String[] args) {
//        String[] test = {"2","1","+","3","*"};
//        System.out.println(new lc150().evalRPN(test));

        String[] test = {"4","13","5","/","*"};
        System.out.println(new lc150().evalRPN(test));
    }
}
