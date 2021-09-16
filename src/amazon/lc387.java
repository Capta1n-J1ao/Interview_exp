package amazon;


import java.util.HashMap;

public class lc387 {
    private HashMap<Character, Integer> hashMap = new HashMap<>();
    public int firstUniqChar(String s) {
        int sLen = s.length();
        int res = -1;
        if(s == null || sLen == 0) return res;
        char[] sChar = s.toCharArray();
        for(char ch : sChar){
            hashMap.put(ch, hashMap.getOrDefault(ch, 0) + 1);
        }
        for(int i = 0; i < sLen; i++){
            if(hashMap.get(sChar[i]) == 1){
                res = i;
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new lc387().firstUniqChar("loveleetcode"));
    }
}
