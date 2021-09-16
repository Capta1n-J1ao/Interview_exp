package amazon;

/*
比较简单，下次可以跳过
* */

import java.util.LinkedList;
import java.util.List;

public class lc17 {
    private static final String[] board = {"", "", "abc",
            "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    private List<String> res = new LinkedList<>();
    private String digits;
    public List<String> letterCombinations(String digits) {
        this.digits = digits;
        if(digits.length() == 0 || digits == null) return res;
        BackTracking(new StringBuilder(), 0);
        return res;
    }

    private void BackTracking(StringBuilder curRes, int index){
        int sLen = curRes.length();
        int dLen = digits.length();
        if(sLen == dLen){
            res.add(new String(curRes));
            return;
        }

        String temp = board[digits.charAt(index) - '0'];
        char[] tChar = temp.toCharArray();
        for(int i = 0; i < tChar.length; i++){
            curRes.append(tChar[i]);
            BackTracking(curRes, index + 1);
            curRes.deleteCharAt(curRes.length() - 1);
        }
    }

    public static void main(String[] args){
        String test = "23";
        StringBuilder test1 = new StringBuilder();
        System.out.println(new lc17().letterCombinations(test));
        System.out.println(test1.length());
    }
}
