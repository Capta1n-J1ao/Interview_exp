package amazon;

/*
这道题目第一次就AC了，但是希望能够使用优化解法做出来，更加漂亮，只用一次遍历：
https://leetcode-cn.com/problems/bulls-and-cows/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--55/
* */

import java.util.HashMap;
import java.util.HashSet;

public class lc299 {
//    public String getHint(String secret, String guess) {
////        corner case 需补充
//        if(secret == null || guess == null) return "0A0B";
//        char[] sChar = secret.toCharArray();
//        char[] gChar = guess.toCharArray();
//        int len = secret.length(), bull = 0, cow = 0;
//        boolean[] visited = new boolean[len];
//        StringBuilder res = new StringBuilder();
//        for(int i = 0; i < len; i++){
//            if(sChar[i] == gChar[i]) {
//                bull++;
//                visited[i] = true;
//            }
//        }
//        res.append(bull).append('A');
//        HashMap<Character, Integer> hashMap = new HashMap<>();
//        for(int i = 0; i < len; i++){
//            if(!visited[i]) hashMap.put(sChar[i],hashMap.getOrDefault(sChar[i], 0) + 1);
//        }
//        for(int i = 0; i < len; i++){
//            if(!hashMap.containsKey(gChar[i])) continue;
//            if(!visited[i] && hashMap.get(gChar[i]) > 0){
//                cow++;
//                hashMap.put(gChar[i], hashMap.get(gChar[i]) - 1);
//            }
//        }
//        res.append(cow).append('B');
//        return res.toString();
//    }

    public String getHint(String secret, String guess) {
        int[] count = new int[10];
        int bull = 0, cow = 0;
        char[] sChar = secret.toCharArray();
        char[] gChar = guess.toCharArray();
        int len = secret.length();
        for(int i = 0; i < len; i++){
            int s = sChar[i] - '0';
            int g = gChar[i] - '0';
            if(s == g) bull++;
            else {
                /*
                将两个数组简化为一个数组。对于每一位，正数代表 secret 中有，
                负数代表 guess 中有，也就是说如果正在处理的s是从secret出来的
                然后count[s] < 0的话，说明之前guess里面有一个s，那么这时候就可以判定是一个cow
                * */
                if(count[s] < 0) cow++;
                if(count[g] > 0) cow++;
                count[s]++;
                count[g]--;
            }
        }
        StringBuilder res = new StringBuilder();
        res.append(bull).append('A').append(cow).append('B');
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(new lc299().getHint("1123", "0111"));
    }
}
