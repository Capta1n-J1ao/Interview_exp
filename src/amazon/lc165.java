package amazon;

/*
一共两种方法，
1. 第一种方法是自己想的，但是需要注意以下几点
    1. 注意由于"."在java里面有特殊含义，所以在对这个符号进行split的时候一定要使用转义！
    2. String在转为int的时候如果有前置0的话会自动去除
2. 第二种方法，用字符串直接比较的话可以更加快，但是代码会相应麻烦一点
https://leetcode-cn.com/problems/compare-version-numbers/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-40/
* */

public class lc165 {
    public int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");
        int sLen = Math.max(s1.length, s2.length);
        int m = 0, k = 0;
        for(int i = 0; i < sLen; i++){
            String tempS1 = i >= s1.length ? "0" : s1[i];
            String tempS2 = i >= s2.length ? "0" : s2[i];
            int res = compare(tempS1, tempS2);
            if(res == 0){
                m++;
                k++;
            }else return res;
        }
        return 0;
    }

    private int compare(String s1, String s2){
        s1 = removeZeros(s1);
        s2 = removeZeros(s2);
        if(s1.length() > s2.length()) return 1;
        else if(s1.length() < s2.length()) return -1;
        else {
            for(int i = 0; i < s1.length(); i++){
                if(s1.charAt(i) - s2.charAt(i) > 0)return 1;
                else if(s1.charAt(i) - s2.charAt(i) < 0) return -1;
            }
            return 0;
        }
    }

    private String removeZeros(String str){
        char[] sChar = str.toCharArray();
        int index = 0;
        for(char ch : sChar){
            if(ch == '0') index++;
            else break;
        }
        return str.substring(index);
    }
}
