package amazon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class lc349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        int n1Len = nums1.length;
        int n2Len = nums2.length;
        List<Integer> tempRes = new LinkedList<>();
        HashSet<Integer> hashSet = new HashSet<>();
        for(int num : nums1){
            hashSet.add(num);
        }
        for(int num : nums2){
            if(hashSet.contains(num)) tempRes.add(num);
        }
        int[] res = new int[tempRes.size()];
        int index = 0;
        for(int curRes : tempRes){
            res[index++] = curRes;
        }
        return res;
    }
}
