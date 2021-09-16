package amazon;

//import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class lc438 {
    private List<Integer> res = new LinkedList<>();
    public List<Integer> findAnagrams(String s, String p) {
        if(s == null || s.length() == 0 || s.length() < p.length()) return res;
        int pLen = p.length();
        int sLen = s.length();
        char[] pChar = p.toCharArray();
        char[] sChar = s.toCharArray();
        int[] goal = new int[26];
        int[] sGot = new int[26];
        for(int i = 0; i < pLen; i++){
            int curGoal = pChar[i] - 'a';
            goal[curGoal]++;
        }
        int left = 0, right = 0;
        while (right < sLen){
            int curCh = sChar[right] - 'a';
            sGot[curCh]++;
            right++;
            while (sGot[curCh] > goal[curCh]){
                int curLeftChar = sChar[left] - 'a';
                sGot[curLeftChar]--;
                left++;
            }
            if(right - left == pLen) res.add(left);
        }
        return res;
    }
}
