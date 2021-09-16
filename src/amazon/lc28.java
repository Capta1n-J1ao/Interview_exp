package amazon;

/*
没时间可以不做，比较简单
* */

public class lc28 {
    public int strStr(String haystack, String needle) {
        int nLen = needle.length();
        int hLen = haystack.length();
        if(hLen < nLen) return -1;
        if(needle == null || nLen == 0) return 0;
        char[] needleChar = needle.toCharArray();
        char[] hayChar = haystack.toCharArray();
        for(int i = 0; i < haystack.length(); i++){
            if(hayChar[i] == needleChar[0] && i + nLen <= hLen && haystack.substring(i, i + nLen).equals(needle)) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
//        System.out.println(new lc28().strStr("a", "a"));
        System.out.println(new lc28().strStr("mississippi" ,"issipi"));
    }
}
