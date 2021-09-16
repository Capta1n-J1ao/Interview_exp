package amazon;

/*
很简单，直接想下思路就行，注意分析复杂度
有些做法使用数组来做，其实时间复杂度是一样的，但是数组效率还是高一些
* */

import java.util.HashSet;

public class lc771 {
    public int numJewelsInStones(String jewels, String stones) {
//        corner case
        if(jewels == null || jewels.length() == 0 || stones == null || stones.length() == 0) return 0;
        char[] jChar = jewels.toCharArray();
        char[] sChar = stones.toCharArray();
        HashSet<Character> jRef = new HashSet<>();
        for(char ch :jChar) jRef.add(ch);
        int res = 0;
        for(char ch : sChar){
            if(jRef.contains(ch))res++;
        }
        return res;
    }
}
