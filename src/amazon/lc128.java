package amazon;

import java.util.HashMap;
import java.util.HashSet;

public class lc128 {
    private HashSet<Integer> hashSet = new HashSet<>();
    public int longestConsecutive(int[] nums) {
        int numLen = nums.length, res = 0;
        if(nums == null || numLen < 2) return numLen;
        for(int num : nums) hashSet.add(num);
        for(int num : hashSet){
            if(hashSet.contains(num - 1)) continue;
            else {
                int curRes = 0;
                while (hashSet.contains(num)){
                    curRes++;
                    num++;
                }
                res = Math.max(res, curRes);
            }
        }
        return res;
    }
}
