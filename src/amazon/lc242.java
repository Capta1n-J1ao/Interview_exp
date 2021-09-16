package amazon;

/*
注意这道题目的前提是String全是小写
* */

public class lc242 {
    public boolean isAnagram(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if(sLen != tLen) return false;
        int[] sArray = new int[26];
        int[] tArray = new int[26];
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        for(int i = 0; i < sLen; i++){
            sArray[sChar[i] - 'a']++;
            tArray[tChar[i] - 'a']++;
        }
        for(int i = 0; i < 26; i++){
            if(sArray[i] != tArray[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new lc242().isAnagram("nl","cx"));
    }
}
