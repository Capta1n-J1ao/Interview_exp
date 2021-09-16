package amazon;

import java.util.HashMap;
import java.util.Stack;

public class lc20 {
    private Stack<Character> stack = new Stack<>();
    private HashMap<Character, Character> hashMap = new HashMap<>();
    public boolean isValid(String s) {
        hashMap.put('(', ')');
        hashMap.put('{', '}');
        hashMap.put('[', ']');
        char[] sChar = s.toCharArray();
        stack.push('x');
        for(char ch : sChar){
            if(hashMap.containsKey(ch)){
                stack.push(hashMap.get(ch));
            }else {
                char temp = stack.pop();
                if(temp == ch) continue;
                else return false;
            }
        }
        return stack.size() == 1? true : false;
    }

    public static void main(String[] args) {
        System.out.println(new lc20().isValid("(("));
        System.out.println(new lc20().isValid("){"));
    }
}
