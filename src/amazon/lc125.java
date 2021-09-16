package amazon;

/*
有一个点很tricky，那就是s里面包含数字
考虑点：
1. s的长度,前后是否有空格？
2. 有哪些标点？直接影响我的判断
* */

import java.util.Locale;

public class lc125 {
    public boolean isPalindrome(String s) {
        String str = s.toLowerCase();
//        System.out.println(str);
        char[] sChar = str.toCharArray();
        StringBuilder curRes = new StringBuilder();
        for(char ch : sChar){
            if(!isValid(ch)) continue;
            curRes.append(ch);
        }
        char[] res = curRes.toString().toCharArray();
        int left = 0, right = res.length - 1;
        while (left < right){
            if(res[left] != res[right]) return false;
            left++;
            right--;
        }
        return true;
    }

    private boolean isValid(char ch){
        return (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9');
    }

    public static void main(String[] args) {
        System.out.println(new lc125().isPalindrome("SFSDF, GSDGFS"));
        System.out.println(new lc125().isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(new lc125().isPalindrome("0P"));
    }
}
