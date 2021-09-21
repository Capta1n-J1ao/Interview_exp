package drw;

import java.util.*;


public class oa2 {
    public int solution(String S) {
        // write your code in Java SE 8
        if(S == null) return 0;
        String[] sComponent = S.split(" ");
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for(String s : sComponent){
            if(s.equals("DUP")){
                int add = stack.peekFirst();
                stack.addFirst(add);
            }else if(s.equals("POP")){
                if(stack.isEmpty()) return -1;
                stack.pollFirst();
            }else if(s.equals("+")){
                if(stack.size() < 2) return -1;
                int firstAdd = stack.pollFirst();
                int secAdd = stack.pollFirst();
                stack.addFirst(firstAdd + secAdd);
            }else if(s.equals("-")){
                if(stack.size() < 2) return -1;
                int firstMinus = stack.pollFirst();
                int secMinus = stack.pollFirst();
                stack.addFirst(firstMinus - secMinus);
            }else{
                stack.addFirst(Integer.valueOf(s));
            }
        }
        return stack.peekFirst();
    }

    public static void main(String[] args) {
        System.out.println(new oa2().solution("4 5 6 - 7 +"));
    }
}
