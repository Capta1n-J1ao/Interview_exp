package amazon;

/*
这道题目自己想出来的，还算简单
* */

import java.util.LinkedList;
import java.util.List;

public class lc22 {
    private List<String> res = new LinkedList<>();
    private int n;
    public List<String> generateParenthesis(int n) {
        this.n = n;
        BackTracking(0,0, new StringBuilder());
        return res;
    }

    private void BackTracking(int left, int right, StringBuilder curRes){
        if(curRes.length() == 2 * n){
            if(left == right && left == n){
                String temp = new String(curRes);
                res.add(temp);
            }
            return;
        }
        if(left > right && left <= n){
            BackTracking(left, right + 1, curRes.append(")"));
            curRes.deleteCharAt(curRes.length() - 1);
        }
        if(left < n){
            BackTracking(left + 1, right, curRes.append("("));
            curRes.deleteCharAt(curRes.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new lc22().generateParenthesis(3));
    }
}
