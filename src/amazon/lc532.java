package amazon;

import java.util.HashMap;
import java.util.HashSet;

public class lc532 {
    public int findPairs(int[] nums, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int res = 0;
        for(int num : nums){
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }
        if(k == 0){
            for(int key : hashMap.keySet()){
                if(hashMap.get(key) > 1) res++;
            }
        }else {
            for(int key : hashMap.keySet()){
                if((hashMap.containsKey(key + k))){
                    res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] test = {3,1,4,1,5};
        System.out.println(new lc532().findPairs(test, 2));
    }
}
