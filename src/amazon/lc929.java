package amazon;

/*
最好先做lc929再做lc722，题解的话这个最符合我的思路
https://leetcode-cn.com/problems/unique-email-addresses/solution/setji-he-by-cristianoronaldo-s1jp/
* */

import java.util.HashSet;

public class lc929 {
    public int numUniqueEmails(String[] emails) {
        HashSet<String> hashSet = new HashSet<>();
        for(String email : emails){
            hashSet.add(helper(email));
        }
        return hashSet.size();
    }

    public String helper(String str){
        StringBuilder sb = new StringBuilder();
        int addIndex = str.indexOf("+");
        int atIndex = str.indexOf("@");
        if(addIndex == -1){
            for(int i = 0; i < atIndex; i++){
                if(str.charAt(i) != '.') sb.append(str.charAt(i));
            }
        }else {
            for(int i = 0; i < addIndex; i++) {
                if(str.charAt(i) != '.') sb.append(str.charAt(i));
            }
        }
        return sb.append(str.substring(atIndex)).toString();
    }

    public static void main(String[] args) {
        String[] test = {"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com"};
        System.out.println(new lc929().numUniqueEmails(test));
    }
}
