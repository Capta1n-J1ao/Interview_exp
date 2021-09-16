package amazon;

/*
这道题目其实有巧方法的，千万不要死做
https://leetcode-cn.com/problems/zigzag-conversion/solution/zzi-xing-bian-huan-by-jyd/
* */

import java.util.LinkedList;
import java.util.List;

public class lc6 {
    private boolean flag = true;
    public String convert(String s, int numRows) {
        int sLen = s.length();
        if(sLen < 3 || numRows < 2) return s;
        char[] sChar = s.toCharArray();
        List<StringBuilder> rowRes = new LinkedList<>();
        for(int i = 0; i < numRows; i++) rowRes.add(new StringBuilder());
        int index = 0;
        for(int i = 0; i < sLen; i++){
            StringBuilder curRes = rowRes.get(index);
            curRes.append(sChar[i]);
            index = flag ? index + 1 : index - 1;
            if(index == numRows - 1 || index == 0) flag = !flag;
        }
        StringBuilder res = new StringBuilder();
        for(StringBuilder sb : rowRes) res.append(sb);
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(new lc6().convert("PAYPALISHIRING", 3));
    }
}
